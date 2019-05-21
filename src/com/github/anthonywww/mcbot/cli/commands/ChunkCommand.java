package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Logger;

import com.github.anthonywww.mcbot.cli.ICLICommand;

public class ChunkCommand implements ICLICommand {

	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		
		logger.warning("Usage: " + commandName() + " [x,z]");
	}
	
	@Override
	public String commandName() {
		return "chunk";
	}
	
	@Override
	public String commandDescription() {
		return "Chunk information";
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
