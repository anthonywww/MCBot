package com.github.anthonywww.mcbot.lua;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.luaj.vm2.LuaError;
import org.luaj.vm2.LuaFunction;
import org.luaj.vm2.LuaString;
import org.luaj.vm2.LuaTable;
import org.luaj.vm2.LuaValue;
import org.luaj.vm2.lib.OneArgFunction;
import org.luaj.vm2.lib.TwoArgFunction;
import org.luaj.vm2.lib.ZeroArgFunction;

import com.github.anthonywww.mcbot.MCBot;

public class LuaBot extends TwoArgFunction {
	
	private Executor luaExecutor = Executors.newSingleThreadExecutor();
	private LuaFunction rawChatCallback;
	private LuaFunction chatCallback;
	private LuaFunction healthCallback;

	@Override
	public LuaValue call(LuaValue modname, LuaValue env) {
		LuaTable bot = new LuaTable();
		
		// Setters
		bot.set("sleep", new sleep());
		bot.set("sendMessage", new send_message());
		bot.set("rotate", new rotate());
		bot.set("rotatePitch", new rotate_pitch());
		bot.set("rotateYaw", new rotate_yaw());
		bot.set("moveForward", new move_forward());
		bot.set("moveBackward", new move_backward());
		bot.set("moveLeft", new move_left());
		bot.set("moveRight", new move_right());
		
		// Callbacks
		bot.set("onMessage", new chat_callback());
		bot.set("onRawMessage", new raw_chat_callback());
		bot.set("onHealthChange", new health_update_callback());
		
		// Getters
		bot.set("getHealth", new get_health());
		bot.set("getHunger", new get_hunger());
		bot.set("isDead", new is_dead());
		bot.set("getPosition", new get_position());
		bot.set("getX", new get_position_x());
		bot.set("getY", new get_position_y());
		bot.set("getZ", new get_position_z());
		bot.set("getRotation", new get_rotation());
		bot.set("getYaw", new get_rotation_yaw());
		bot.set("getPitch", new get_rotation_pitch());
		bot.set("getUsername", new get_username());
		// TODO: canMoveForward()
		// TODO: canMoveBackward()
		// TODO: canMoveLeft()
		// TODO: canMoveRight()
		// TODO: canJump()
		// TODO: isFalling()
		// TODO: isInLiquid()
		// TODO: getBlockAt(x, y, z)
		
		LuaTable mt = LuaValue.tableOf(new LuaValue[] { INDEX, bot });
		env.set("bot", bot);
		env.get("package").get("loaded").set("bot", bot);

		if (LuaString.s_metatable == null) {
			LuaString.s_metatable = mt;
		}

		return bot;
	}
	
	/**
	 * Have the current thread sleep (delay) for a specified amount of time in milliseconds.
	 */
	class sleep extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue x) {
			int ms = x.checkint();

			if (ms < 0) {
				throw new LuaError("bot.sleep() only accepts positive integers.");
			}

			try {
				Thread.sleep(ms);
			} catch (InterruptedException e) {
				return LuaValue.error("Sleep Interrupted!");
			}

			return LuaValue.NIL;
		}
	}
	
	class get_username extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			return LuaValue.valueOf(MCBot.getInstance().getConfig().getUsername());
		}
	}
	
	/**
	 * Send a chat message
	 */
	class send_message extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue msg) {
			MCBot.getInstance().getPlayer().sendMessage(msg.tojstring());
			return LuaValue.NIL;
		}
	}
	
	/**
	 * Rotate on yaw and pitch in degrees
	 */
	class rotate extends TwoArgFunction {
		@Override
		public LuaValue call(LuaValue yaw, LuaValue pitch) {
			MCBot.getInstance().getPlayer().sendRotate((float) yaw.checkdouble(), (float) pitch.checkdouble());
			return LuaValue.NIL;
		}
	}

	/**
	 * Rotate on pitch in degrees
	 */
	class rotate_pitch extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue angle) {
			MCBot.getInstance().getPlayer().sendRotate(0.0f, (float)angle.checkdouble());
			return LuaValue.NIL;
		}
	}

	/**
	 * Rotate on yaw in degrees
	 */
	class rotate_yaw extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue angle) {
			MCBot.getInstance().getPlayer().sendRotate((float)angle.checkdouble(), 0.0f);
			return LuaValue.NIL;
		}
	}
	
	/**
	 * Move position relatively "forward" by a certain distance
	 */
	class move_forward extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue distance) {
			MCBot.getInstance().getPlayer().moveForward(distance.checkdouble());
			return LuaValue.NIL;
		}
	}
	
	/**
	 * Move position relatively "backward" by a certain distance
	 */
	class move_backward extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue distance) {
			MCBot.getInstance().getPlayer().moveBackward(distance.checkdouble());
			return LuaValue.NIL;
		}
	}
	
	/**
	 * Move position relatively "left" by a certain distance
	 */
	class move_left extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue distance) {
			MCBot.getInstance().getPlayer().moveLeft(distance.checkdouble());
			return LuaValue.NIL;
		}
	}
	
	/**
	 * Move position relatively "right" by a certain distance
	 */
	class move_right extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue distance) {
			MCBot.getInstance().getPlayer().moveRight(distance.checkdouble());
			return LuaValue.NIL;
		}
	}
	
	class get_health extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			return LuaValue.valueOf(MCBot.getInstance().getPlayer().getHealth());
		}
	}
	
	class get_hunger extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			return LuaValue.valueOf(MCBot.getInstance().getPlayer().getFood());
		}
	}
	
	class is_dead extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			return LuaValue.valueOf(MCBot.getInstance().getPlayer().getHealth() <= 0);
		}
	}
	
	class get_position extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			LuaValue[] tables = new LuaValue[6];
			tables[0] = LuaValue.valueOf("x");
			tables[1] = LuaValue.valueOf(MCBot.getInstance().getPlayer().getX());
			tables[2] = LuaValue.valueOf("y");
			tables[3] = LuaValue.valueOf(MCBot.getInstance().getPlayer().getY());
			tables[4] = LuaValue.valueOf("z");
			tables[5] = LuaValue.valueOf(MCBot.getInstance().getPlayer().getZ());
			return LuaValue.tableOf(tables);
		}
	}
	
	class get_position_x extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			return LuaValue.valueOf(MCBot.getInstance().getPlayer().getX());
		}
	}
	
	class get_position_y extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			return LuaValue.valueOf(MCBot.getInstance().getPlayer().getY());
		}
	}
	
	class get_position_z extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			return LuaValue.valueOf(MCBot.getInstance().getPlayer().getZ());
		}
	}
	
	class get_rotation extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			LuaValue[] tables = new LuaValue[4];
			tables[0] = LuaValue.valueOf("yaw");
			tables[1] = LuaValue.valueOf(MCBot.getInstance().getPlayer().getYaw());
			tables[2] = LuaValue.valueOf("pitch");
			tables[3] = LuaValue.valueOf(MCBot.getInstance().getPlayer().getPitch());
			return LuaValue.tableOf(tables);
		}
	}
	
	static class get_rotation_yaw extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			return LuaValue.valueOf(MCBot.getInstance().getPlayer().getYaw());
		}
	}
	
	class get_rotation_pitch extends ZeroArgFunction {
		@Override
		public LuaValue call() {
			return LuaValue.valueOf(MCBot.getInstance().getPlayer().getPitch());
		}
	}
	
	class raw_chat_callback extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue func) {
			if (func.isnil()) {
				rawChatCallback = null;
				return LuaValue.NIL;
			}
			LuaFunction f = func.checkfunction();
			rawChatCallback = f;
			return LuaValue.NIL;
		}
	}
	
	class chat_callback extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue func) {
			if (func.isnil()) {
				chatCallback = null;
				return LuaValue.NIL;
			}
			LuaFunction f = func.checkfunction();
			chatCallback = f;
			return LuaValue.NIL;
		}
	}
	
	class health_update_callback extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue func) {
			LuaFunction f = func.checkfunction();
			healthCallback = f;
			return LuaValue.NIL;
		}
	}
	
	
	
	/**
	 * Callback to lua for raw chat messages
	 * @param text
	 */
	public void rawChatCallback(String text) {
		if (rawChatCallback != null && !rawChatCallback.isnil()) {
			luaExecutor.execute(() -> {
				rawChatCallback.call(text);
			});
		}
	}
	
	/**
	 * Callback to lua for player chat messages
	 * @param username
	 * @param msg
	 */
	public synchronized void chatCallback(String username, String msg) {
		if (chatCallback != null && !chatCallback.isnil()) {
			luaExecutor.execute(() -> {
				chatCallback.call(LuaValue.valueOf(username), LuaValue.valueOf(msg));
			});
		}
	}
	
	/**
	 * Callback to lua for player chat messages
	 * @param username
	 * @param msg
	 */
	public synchronized void healthCallback(float health, int food) {
		if (healthCallback != null && !healthCallback.isnil()) {
			luaExecutor.execute(() -> {
				healthCallback.call(LuaValue.valueOf(health), LuaValue.valueOf(food));
			});
		}
	}
}
