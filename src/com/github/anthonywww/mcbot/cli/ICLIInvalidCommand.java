package com.github.anthonywww.mcbot.cli;

public interface ICLIInvalidCommand {

	/**
	 * The command that was invoked but not found
	 */
	public void invalidInvoke(String command, String[] params);

}
