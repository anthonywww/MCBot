package com.github.anthonywww.mcbot.cli.commands;

import java.util.Arrays;
import java.util.logging.Logger;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class FriendCommand implements ICLICommand {

	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		if (params.length >= 1) {
			switch(params[0].toLowerCase()) {
			case "add":
				if (params.length == 2) {
					if (MCBot.getInstance().getConfig().getFriends().contains(params[1])) {
						logger.warning("Friend '" + params[1] + "' already exists!");
						return;
					}
					MCBot.getInstance().getConfig().addFriend(params[1]);
					logger.info("Added friend '" + params[1] + "' to friends list.");
					return;
				}
				logger.info("Usage: " + commandName() + " add <friends>");
				return;
			case "remove":
				if (params.length == 2) {
					for (String friendName : MCBot.getInstance().getConfig().getFriends()) {
						if (friendName.equalsIgnoreCase(params[1])) {
							MCBot.getInstance().getConfig().removeFriend(params[1]);
							logger.info("Removed friend '" + params[1] + "' from friends list.");
							return;
						}
					}
					logger.warning("Friend '" + params[1] + "' doesn't exist!");
					return;
				}
				logger.info("Usage: " + commandName() + " remove <friends>");
				break;
			case "list":
				logger.info("Friends: " + Arrays.toString(MCBot.getInstance().getConfig().getFriends().toArray()));
				return;
			}
		}
		
		logger.info("Usage: " + commandName() + " <add/remove/list> <friends>");
	}

	@Override
	public String commandName() {
		return "friend";
	}

	@Override
	public String commandDescription() {
		return "Friend options.";
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
