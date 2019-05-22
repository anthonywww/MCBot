package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Logger;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;
import com.github.anthonywww.mcbot.utils.Vector3d;

public class GotoCommand implements ICLICommand {
	
	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		
		// TODO: add player support
		
		if (params.length == 3) {
			try {
				final Vector3d position = MCBot.getInstance().getPlayer().getPosition();
				double x = Double.parseDouble(params[0]) + 0.5;
				double y = Double.parseDouble(params[1]);
				double z = Double.parseDouble(params[2]) + 0.5;
				
				logger.info("Walking to X=" + x + ", Y=" + y + ", Z=" + z + " ...");
				
				MCBot.getInstance().getPlayer().face(x, y, z);
				
				// Get delta's
				x -= position.getX();
				y -= position.getY();
				z -= position.getZ();
				
				// TODO: Use path finding
				MCBot.getInstance().getPlayer().move(x, y, z);
				
				return;
			} catch (NumberFormatException e) {}
		}
		
		logger.info("Usage: " + commandName() + " <x> <y> <z>");
	}
	
	@Override
	public String commandName() {
		return "goto";
	}
	
	@Override
	public String commandDescription() {
		return "Have the bot walk to specific x, y, z coordinates";
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
