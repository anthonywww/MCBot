package com.github.anthonywww.mcbot.cli.commands;

import java.util.logging.Logger;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.Terminal;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class HelpCommand implements ICLICommand {

	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		if (params.length == 1) {
			for (ICLICommand c : MCBot.getInstance().getTerminal().getRegisteredCommands()) {
				if (c.commandName().equalsIgnoreCase(params[1])) {
					logger.info("---- Help for '" + c.commandName() + "' ----");
					logger.info(String.format(Terminal.colorize("§a%s§r  -  §6%s§r"), c.commandName(), c.commandDescription()));	
				}
			}
			return;
		}
		logger.info("---- Help Commands ----");
		for (ICLICommand c : MCBot.getInstance().getTerminal().getRegisteredCommands()) {
			logger.info(String.format(Terminal.colorize("§a%s§r  -  §6%s§r"), c.commandName(), c.commandDescription()));
		}
	}

	@Override
	public String commandName() {
		return "help";
	}

	@Override
	public String commandDescription() {
		return "Show a list of commands.";
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
