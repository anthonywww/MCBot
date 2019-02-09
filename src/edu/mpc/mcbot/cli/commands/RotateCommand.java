package edu.mpc.mcbot.cli.commands;

import edu.mpc.mcbot.cli.ICLICommand;

public class RotateCommand implements ICLICommand {
	
	public RotateCommand() {
		
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