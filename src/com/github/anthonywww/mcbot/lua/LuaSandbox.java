package com.github.anthonywww.mcbot.lua;

import java.util.logging.Logger;

import org.luaj.vm2.Globals;
import org.luaj.vm2.LoadState;
import org.luaj.vm2.LuaBoolean;
import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaThread;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.Varargs;
import org.luaj.vm2.compiler.LuaC;
import org.luaj.vm2.lib.Bit32Lib;
import org.luaj.vm2.lib.DebugLib;
import org.luaj.vm2.lib.PackageLib;
import org.luaj.vm2.lib.StringLib;
import org.luaj.vm2.lib.TableLib;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;
import org.luaj.vm2.lib.jse.JseBaseLib;
import org.luaj.vm2.lib.jse.JseIoLib;
import org.luaj.vm2.lib.jse.JseMathLib;
import org.luaj.vm2.lib.jse.JseOsLib;

import com.github.anthonywww.mcbot.MCBot;

public class LuaSandbox {

	private static Logger logger = Logger.getLogger("");
	private Globals globals;

	public LuaSandbox() {
		logger.fine("Loading Lua environment ...");
		// Create server globals with just enough library support to compile user
		// scripts.
		globals = new Globals();
		globals.load(new JseBaseLib());
		globals.load(new PackageLib());
		globals.load(new StringLib());
		globals.load(new Bot());

		// To load scripts, we occasionally need a math library in addition to compiler
		// support.
		// To limit scripts using the debug library, they must be closures, so we only
		// install LuaC.
		globals.load(new JseMathLib());
		LoadState.install(globals);
		LuaC.install(globals);

		// Set up the LuaString metatable to be read-only since it is shared across all
		// scripts.
		LuaString.s_metatable = new ReadOnlyLuaTable(LuaString.s_metatable);

		// Example normal scripts that behave as expected.
		//runScriptInSandbox("return 'foo'");
		//runScriptInSandbox("return ('abc'):len()");
		//runScriptInSandbox("return getmetatable('abc')");
		//runScriptInSandbox("return getmetatable('abc').len");
		//runScriptInSandbox("return getmetatable('abc').__index");

		// Example user scripts that attempt rogue operations, and will fail.
		//runScriptInSandbox("return setmetatable('abc', {})");
		//runScriptInSandbox("getmetatable('abc').len = function() end");
		//runScriptInSandbox("getmetatable('abc').__index = {}");
		//runScriptInSandbox("getmetatable('abc').__index.x = 1");
		//runScriptInSandbox("while true do print('loop') end");

		// Example use of other shared metatables, which should also be made read-only.
		// This example allows booleans to be added to numbers.
		//runScriptInSandbox("return 5 + 6, 5 + true, false + 6");

		LuaBoolean.s_metatable = new ReadOnlyLuaTable(
			LuaValue.tableOf(new LuaValue[] {
				LuaValue.ADD, new TwoArgFunction() {
					public LuaValue call(LuaValue x, LuaValue y) {
						return LuaValue.valueOf((x == TRUE ? 1.0 : x.todouble()) + (y == TRUE ? 1.0 : y.todouble()));
					}
				},
			})
		);
		
		logger.fine("Lua environment is ready!");
	}
	
	public void runScriptInSandbox(String path, String script) {
		
		logger.fine("Loading Lua script: " + path + " ...");
		// Each script will have it's own set of globals, which should
		// prevent leakage between scripts running on the same server.
		Globals user_globals = new Globals();
		user_globals.load(new JseBaseLib());
		user_globals.load(new PackageLib());
		user_globals.load(new Bit32Lib());
		user_globals.load(new TableLib());
		user_globals.load(new StringLib());
		user_globals.load(new JseMathLib());
		user_globals.load(new Bot());

		// This library is dangerous as it gives unfettered access to the
		// entire Java VM, so it's not suitable within this lightweight sandbox.
		// user_globals.load(new LuajavaLib());

		// Starting coroutines in scripts will result in threads that are
		// not under the server control, so this libary should probably remain out.
		// user_globals.load(new CoroutineLib());

		// These are probably unwise and unnecessary for scripts on servers,
		// although some date and time functions may be useful.
		user_globals.load(new JseIoLib());
		user_globals.load(new JseOsLib());

		// Loading and compiling scripts from within scripts may also be
		// prohibited, though in theory it should be fairly safe.
		LoadState.install(user_globals);
		// LuaC.install(user_globals);

		// The debug library must be loaded for hook functions to work, which
		// allow us to limit scripts to run a certain number of instructions at a time.
		// However we don't wish to expose the library in the user globals,
		// so it is immediately removed from the user globals once created.
		user_globals.load(new DebugLib());
		LuaValue sethook = user_globals.get("debug").get("sethook");
		user_globals.set("debug", LuaValue.NIL);

		// Set up the script to run in its own lua thread, which allows us
		// to set a hook function that limits the script to a specific number of cycles.
		// Note that the environment is set to the user globals, even though the
		// compiling is done with the server globals.
		try {
			logger.fine("Executing Lua script: " + path + " ...");
			LuaValue chunk = globals.load(script, path, user_globals);
			LuaThread thread = new LuaThread(user_globals, chunk);
	
			// Set the hook function to immediately throw an Error, which will not be
			// handled by any Lua code other than the coroutine.
			LuaValue hookfunc = new ZeroArgFunction() {
				public LuaValue call() {
					logger.warning("[lua] Script '" + path + "' is going over the resource constraint.");
					// A simple lua error may be caught by the script, but a
					// Java Error will pass through to top and stop the script.
					try {
						throw new LuaError("Went over resource constraint.");
					} catch (Exception e) {
						MCBot.getInstance().getTerminal().handleException(e);
					}
					
					// FIXME: What to return on resource overload?
					return LuaValue.error("Went over resource constraint.");
				}
			};
			
			final int instructionCount = 9000;
			sethook.invoke(LuaValue.varargsOf(new LuaValue[] { thread, hookfunc, LuaValue.EMPTYSTRING, LuaValue.valueOf(instructionCount) }));
	
			// When we resume the thread, it will run up to 'instruction_count' instructions
			// then call the hook function which will error out and stop the script.
			Varargs result = thread.resume(LuaValue.NIL);
			logger.fine("[lua] [" + path + "]: [[" + script + "]] -> " + result);
			
			if (!result.tojstring().equals("true")) {
				logger.warning("[lua] " + result.tojstring());
			}
		} catch (LuaError e) {
			logger.warning("[lua] " + e.getMessage());
		}
	}

	/**
	 * Simple read-only table whose contents are initialized from another table.
	 */
	private class ReadOnlyLuaTable extends LuaTable {
		public ReadOnlyLuaTable(LuaValue table) {
			presize(table.length(), 0);
			for (Varargs n = table.next(LuaValue.NIL); !n.arg1().isnil(); n = table.next(n.arg1())) {
				LuaValue key = n.arg1();
				LuaValue value = n.arg(2);
				super.rawset(key, value.istable() ? new ReadOnlyLuaTable(value) : value);
			}
		}

		@Override
		public LuaValue setmetatable(LuaValue metatable) {
			return error("table is read-only");
		}

		@Override
		public void set(int key, LuaValue value) {
			error("table is read-only");
		}
		
		@Override
		public void rawset(int key, LuaValue value) {
			error("table is read-only");
		}

		@Override
		public void rawset(LuaValue key, LuaValue value) {
			error("table is read-only");
		}

		@Override
		public LuaValue remove(int pos) {
			return error("table is read-only");
		}
	}

}
