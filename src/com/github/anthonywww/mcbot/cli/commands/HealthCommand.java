package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Logger;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class HealthCommand implements ICLICommand {
	
	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		final int healthPercent = (int) ((MCBot.getInstance().getPlayer().getHealth()/20.0f) * 100);
		final int hungerPercent = (int) ((MCBot.getInstance().getPlayer().getFood()/20.0f) * 100);
		logger.info(String.format("Health: %.2f/20 (%d%%)", MCBot.getInstance().getPlayer().getHealth(), healthPercent));
		logger.info(String.format("Food: %d/20 (%d%%)", MCBot.getInstance().getPlayer().getFood(), hungerPercent));
	}
	
	@Override
	public String commandName() {
		return "health";
	}
	
	@Override
	public String commandDescription() {
		return "Show player health status";
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
