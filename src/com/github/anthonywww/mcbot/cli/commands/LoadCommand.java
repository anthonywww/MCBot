package com.github.anthonywww.mcbot.cli.commands;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;

public class LoadCommand implements ICLICommand {

	private static final Logger logger = Logger.getLogger("");

	@Override
	public void invoke(String[] params) {
		if (params.length == 0) {
			logger.warning("load: Requires the path to the Lua script.");
			return;
		}

		File f = new File(params[0]);

		if (f.exists() && f.isFile() && f.getName().endsWith(".lua")) {
			try {
				String src = readFileAsString(f);
				MCBot.getInstance().getLua().runScriptInSandbox(f.getPath(), src);
			} catch (IOException e) {
				MCBot.getInstance().getTerminal().handleException(e);
			}
		} else {
			logger.warning("load: " + params[0] + " is not a lua script.");
		}
	}

	@Override
	public String commandName() {
		return "load";
	}

	@Override
	public String commandDescription() {
		return "Load a lua script.";
	}

	@Override
	public boolean caseSensitive() {
		return false;
	}

	@Override
	public boolean addToCompleter() {
		return true;
	}

	private static final String readFileAsString(File f) throws IOException {
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(f));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}

}
