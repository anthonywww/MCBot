package edu.mpc.mcbot.cli.commands;

import java.util.logging.Logger;

import edu.mpc.mcbot.MCBot;
import edu.mpc.mcbot.cli.ICLICommand;

public class SayCommand implements ICLICommand {
	
	private static final Logger logger = Logger.getLogger("");
	
	public SayCommand() {
		
	}
	
	@Override
	public void invoke(String[] params) {
		if (params.length > 0) {
			MCBot.getInstance().getClient().sendMessage(String.join(" ", params).trim());
		}
	}
	
	@Override
	public String commandName() {
		return "say";
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
