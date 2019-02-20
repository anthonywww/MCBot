package com.github.anthonywww.mcbot.cli;

public interface ICLICommand {
	
	/**
	 * The command name that identifies this command that will be invoked
	 */
	public String commandName();

	/**
	 * The description of this command, use NULL for no description
	 */
	public String commandDescription();

	/**
	 * Set if the command is case sensitive
	 */
	public boolean caseSensitive();

	/**
	 * Add the command to the terminal completer
	 * 
	 * @return
	 */
	public boolean addToCompleter();

	/**
	 * This method is a callback from when the command name gets invoked
	 * 
	 * @param command parameters
	 */
	public void invoke(String[] params);
}
