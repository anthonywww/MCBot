package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Logger;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class PositionCommand implements ICLICommand {

	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		logger.info(String.format("Current position: X=%.2f Y=%.2f Z=%.2f",
			MCBot.getInstance().getPlayer().getX(),
			MCBot.getInstance().getPlayer().getY(),
			MCBot.getInstance().getPlayer().getZ()
		));
	}
	
	@Override
	public String commandName() {
		return "position";
	}
	
	@Override
	public String commandDescription() {
		return "Position information";
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
