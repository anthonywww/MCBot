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
		bot.set("rotate", new rotate());
		bot.set("rotatePitch", new rotate_pitch());
		bot.set("rotateYaw", new rotate_yaw());
		bot.set("moveForward", new move_forward());
		bot.set("moveBackward", new move_backward());
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
				return LuaValue.error("Sleep Interrupted!");
			}

			return LuaValue.NIL;
		}
	}
	
	static class rotate extends TwoArgFunction {
		@Override
		public LuaValue call(LuaValue yaw, LuaValue pitch) {
			MCBot.getInstance().getPlayer().sendRotate((float) yaw.checkdouble(), (float) pitch.checkdouble());
			return LuaValue.NIL;
		}
	}

	static class rotate_pitch extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue angle) {
			MCBot.getInstance().getPlayer().sendRotate(0.0f, (float)angle.checkdouble());
			return LuaValue.NIL;
		}
	}

	static class rotate_yaw extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue angle) {
			MCBot.getInstance().getPlayer().sendRotate((float)angle.checkdouble(), 0.0f);
			return LuaValue.NIL;
		}
	}
	
	static class move_forward extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue distance) {
			MCBot.getInstance().getPlayer().moveForward(distance.checkdouble());
			return LuaValue.NIL;
		}
	}
	
	static class move_backward extends OneArgFunction {
		@Override
		public LuaValue call(LuaValue distance) {
			MCBot.getInstance().getPlayer().moveForward(distance.checkdouble());
			return LuaValue.NIL;
		}
	}
	
	
	
	
	
}
