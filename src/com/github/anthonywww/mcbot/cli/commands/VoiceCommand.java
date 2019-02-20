package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Level;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.STTManager;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class VoiceCommand implements ICLICommand {
	
	@Override
	public void invoke(String[] params) {
		if (params.length == 1) {
			if (params[0].equalsIgnoreCase("true") || params[0].equalsIgnoreCase("false")) {
				boolean status = Boolean.parseBoolean(params[0]);
				STTManager.setEnabled(status);
				return;
			}
		}
		
		MCBot.getInstance().log(Level.INFO, "Usage: " + commandName() + " <true/false>");
	}
	
	@Override
	public String commandName() {
		return "voice";
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
