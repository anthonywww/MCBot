package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Level;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class GotoCommand implements ICLICommand {
	
	@Override
	public void invoke(String[] params) {
		
		if (params.length == 3) {
			try {
				double dx = Double.parseDouble(params[0]);
				double dy = Double.parseDouble(params[1]);
				double dz = Double.parseDouble(params[2]);
				
				MCBot.getInstance().log(Level.INFO, "Walking to [" + dx + ", " + dy + ", " + dz + "] ...");
				
				// TODO: Use path finding instead
				
				return;
			} catch (NumberFormatException e) {}
		}
		
		MCBot.getInstance().log(Level.INFO, "Usage: " + commandName() + " <x> <y> <z>");
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
