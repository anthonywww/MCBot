package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Logger;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class MoveCommand implements ICLICommand {
	
	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		if (params.length != 2) {
			logger.warning("Usage: " + commandName() + " <direction> <units>");
			return;
		}
		
		try {
			int units = Integer.parseInt(params[1]);
			
			switch (params[0].toLowerCase()) {
			case "forward":
				MCBot.getInstance().getPlayer().moveForward(units);
				break;
			case "backward":
				MCBot.getInstance().getPlayer().moveBackward(units);
				break;
			case "left":
				MCBot.getInstance().getPlayer().moveLeft(units);
				break;
			case "right":
				MCBot.getInstance().getPlayer().moveRight(units);
				break;
			default:
				logger.warning("Usage: " + commandName() + "<direction> <units>. Direction must be FORWARD, BACKWARD, LEFT, or RIGHT");
				break;
			}
			
			logger.info(String.format("Moved bot %s %s units.", params[0], params[1]));
		} catch (NumberFormatException e) {
			logger.warning("Usage: " + commandName() + " <direction> <units>. Units must be an integer");
		}
	}
	
	@Override
	public String commandName() {
		return "move";
	}
	
	@Override
	public String commandDescription() {
		return "Move the bot in a direction for n units";
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