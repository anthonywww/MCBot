package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Logger;

import com.github.anthonywww.mcbot.cli.ICLICommand;

public class InventoryCommand implements ICLICommand {
	
	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		
		
		logger.warning("Usage: " + commandName() + "");
	}
	
	@Override
	public String commandName() {
		return "inventory";
	}
	
	@Override
	public String commandDescription() {
		return "Inventory options";
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
