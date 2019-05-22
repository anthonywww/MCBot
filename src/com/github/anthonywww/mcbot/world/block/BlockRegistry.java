package com.github.anthonywww.mcbot.world.block;

import java.util.HashMap;
import java.util.logging.Logger;

public class BlockRegistry {
	
	private static final Logger logger = Logger.getLogger("");
	private HashMap<Integer, BlockType> registry;
	
	public BlockRegistry() {
		registry = new HashMap<Integer, BlockType>();
	}
	
	/**
	 * Add BlockState to the block registry
	 * @param name
	 * @param defaultInternalId
	 * @param internalIds
	 */
	public void add(String name, int defaultInternalId, int[] internalIds) {
		// Remove minecraft: from names
		BlockType type = BlockType.getByName(name.substring(10));

		if (type == BlockType.UNDEFINED) {
			logger.warning(String.format("[Block Registry] Block '%s' could not be matched with a BlockType.", name));
		}
		
		for (int i : internalIds) {
			registry.put(i, type);
		}
	}

	/**
	 * Get a BlockType from the Internal ID (State ID)
	 */
	public BlockType getBlockTypeFromInternalId(int internalId) {
		return registry.get(internalId);
	}
	
	/**
	 * Get a Internal Id from BlockType
	 * @param blockType
	 * @return
	 */
	public int getDefaultInternalIdFromBlockType(BlockType blockType) {
		for (int i : registry.keySet()) {
			BlockType b = registry.get(i);
			if (blockType == b) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Get the size of the registry
	 * @return
	 */
	public int getSize() {
		return registry.size();
	}
	
}
