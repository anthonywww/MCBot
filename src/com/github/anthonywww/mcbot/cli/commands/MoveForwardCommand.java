package com.github.anthonywww.mcbot.cli.commands;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class MoveForwardCommand implements ICLICommand {
	
	@Override
	public void invoke(String[] params) {
		
		MCBot.getInstance().getPlayer().moveForward(Double.parseDouble(params[0]));
	}
	
	@Override
	public String commandName() {
		return "forward";
	}
	
	@Override
	public String commandDescription() {
		return "Move the bot forward n units";
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