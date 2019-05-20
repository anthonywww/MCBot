package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class RotateCommand implements ICLICommand {
	
	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		
		if (params.length == 2) {
			try {
				if (params[0].equalsIgnoreCase("yaw")) {
					float yaw = Float.parseFloat(params[1]);
					float pitch = MCBot.getInstance().getPlayer().getPitch();
					
					MCBot.getInstance().getPlayer().sendRotate(yaw, pitch);
					return;
				} else if (params[0].equalsIgnoreCase("pitch")) {
					float yaw = MCBot.getInstance().getPlayer().getYaw();
					float pitch = Float.parseFloat(params[1]);
					
					MCBot.getInstance().getPlayer().sendRotate(yaw, pitch);
					return;
				} else {
					float yaw = Float.parseFloat(params[0]);
					float pitch = Float.parseFloat(params[1]);
					
					MCBot.getInstance().getPlayer().sendRotate(yaw, pitch);
					return;
				}
			} catch (NumberFormatException e) {}
		}
		
		logger.warning("Usage: " + commandName() + " <yawInDegrees> <pitchInDegrees>");
		logger.warning("Usage: " + commandName() + " yaw <yawInDegrees>");
		logger.warning("Usage: " + commandName() + " pitch <pitchInDegrees>");
	}
	
	@Override
	public String commandName() {
		return "rotate";
	}
	
	@Override
	public String commandDescription() {
		return "Rotate the bot";
	}
	
	@Override
	public boolean caseSensitive() {
		return false;
	}
	
	@Override
	public boolean addToCompleter() {
		return true;
	}
	
}