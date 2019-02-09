package edu.mpc.mcbot.cli.commands;

import java.util.logging.Logger;

import edu.mpc.mcbot.cli.ICLICommand;

public class MoveForwardCommand implements ICLICommand {
	
	private static final Logger logger = Logger.getLogger("");
	
	public MoveForwardCommand() {
		
	}
	
	@Override
	public void invoke(String[] params) {
		if (params.length != 1) {
			logger.info("forward: Requires a single positive integer between 0 and 360 as an argument.");
			return;
		}
		try {
			int deg = Integer.parseInt(params[0]);
			if (deg < 0 || deg > 360) {
				logger.info("forward: Requires a single positive integer between 0 and 360 as an argument.");
				return;
			}
		} catch (NumberFormatException e) {
			logger.info("forward: Requires a single positive integer between 0 and 360 as an argument.");
			return;
		}
		
		
		// TODO: Move bot forward
	}
	
	@Override
	public String commandName() {
		return "forward";
	}
	
	@Override
	public String commandDescription() {
		return "Send a message to the chat.";
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