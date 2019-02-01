package edu.mpc.mcbot.cli.commands;

import java.util.logging.Logger;

import edu.mpc.mcbot.MCBot;
import edu.mpc.mcbot.cli.ICLICommand;

public class Rotate implements ICLICommand {
	
	private static Logger logger = Logger.getLogger("");
	
	public Rotate() {
		
	}
	
	@Override
	public void invoke(String[] params) {
		
		// !rotate <yawDeg> <pitchDeg>
		// !rotate yaw <deg>
		// !rotate pitch <deg>
		
		
		
	}
	
	@Override
	public String commandName() {
		return "rotate";
	}
	
	@Override
	public String commandDescription() {
		return "Rotate the bot";
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