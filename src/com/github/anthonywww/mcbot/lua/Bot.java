package com.github.anthonywww.mcbot.lua;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;

import com.github.anthonywww.mcbot.MCBot;

public class Bot extends TwoArgFunction {

	@Override
	public LuaValue call(LuaValue modname, LuaValue env) {
		LuaTable bot = new LuaTable();

		bot.set("sin", new sin());
		bot.set("cos", new cos());
		bot.set("sleep", new sleep());
		bot.set("sendMessage", new send_message());
		bot.set("rotatePitch", new rotate_pitch());
		bot.set("rotateYaw", new rotate_yaw());
		bot.set("goto", new walk_to());
//		bot.set("moveForward", new move_forward());
//		bot.set("moveBackward", new move_backward());
//		bot.set("moveLeft", new move_left());
//		bot.set("moveRight", new move_right());

		LuaTable mt = LuaValue.tableOf(new LuaValue[] { INDEX, bot });
		env.set("bot", bot);
		env.get("package").get("loaded").set("bot", bot);

		if (LuaString.s_metatable == null) {
			LuaString.s_metatable = mt;
		}

		return bot;
	}

	static class sin extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue x) {
			// x = angle in degrees
			// x * (pi/180)
			return LuaValue.valueOf(Math.sin(x.checkdouble()));
		}
	}

	static class cos extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue x) {
			// x = angle in degrees
			// x * (pi/180)
			return LuaValue.valueOf(Math.cos(x.checkdouble()));
		}
	}

	static class send_message extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue msg) {
			MCBot.getInstance().getPlayer().sendMessage(msg.tojstring());
			return LuaValue.NIL;
		}
	}

	static class sleep extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue x) {
			int ms = x.checkint();

			if (ms < 0) {
				throw new LuaError("bot.sleep() only accepts positive integers.");
			}

			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
			}

			return LuaValue.NIL;
		}
	}

	static class rotate_pitch extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue x) {
			MCBot.getInstance().getPlayer().sendRotate(0.0f, x.tofloat());
			return LuaValue.NIL;
		}
	}

	static class rotate_yaw extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue x) {
			MCBot.getInstance().getPlayer().sendRotate(x.tofloat(), 0.0f);
			return LuaValue.NIL;
		}
	}
	
	static class walk_to extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue vector) {
			
			if (vector.istable()) {
				if (vector.arg(1).type() == LuaValue.TNUMBER && vector.arg(2).type() == LuaValue.TNUMBER && vector.arg(3).type() == LuaValue.TNUMBER) {
					double dx = vector.arg(1).todouble();
					double dy = vector.arg(2).todouble();
					double dz = vector.arg(3).todouble();
					MCBot.getInstance().getPlayer().move(dx, dy, dz);
					return LuaValue.TRUE;
				}
			}
			
			return LuaValue.error("Expected a table with 3 numbers; {x=Number, y=Number, z=Number}");
		}
	}

}
