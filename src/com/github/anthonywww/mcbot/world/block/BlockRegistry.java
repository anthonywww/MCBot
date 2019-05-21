package com.github.anthonywww.mcbot.world.block;

import java.util.Arrays;
import java.util.logging.Logger;

public class BlockRegistry {
	
	private static final Logger logger = Logger.getLogger("");
	
	
	public BlockRegistry() {
		
	}
	
	/**
	 * Add BlockState to the block registry
	 * @param name
	 * @param defaultState
	 * @param blockStates
	 */
	public void add(String name, int defaultState, int[] blockStates) {
		
		 BlockType type = BlockType.getByName(name.substring(10));
		 
		 if (type == BlockType.UNDEFINED) {
			 logger.warning(String.format("[Block Registry] BlockState '%s' could not be matched with a BlockType.", name));
		 }
		 
		 logger.info(type.getName() + ": " + name + " " + defaultState + " " + Arrays.toString(blockStates));
	}
	
	public void getBlockIdFromBlockState() {
		
	}
	
}
