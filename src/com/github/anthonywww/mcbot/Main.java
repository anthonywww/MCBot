package com.github.anthonywww.mcbot;

import picocli.CommandLine;

public class Main {

	public static void main(String[] cliArgs) {
		BotConfig config = new BotConfig();
		CommandLine.run(config, cliArgs);
	}

}
