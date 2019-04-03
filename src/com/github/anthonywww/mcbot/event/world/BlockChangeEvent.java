package com.github.anthonywww.mcbot.event.world;

import com.github.anthonywww.mcbot.event.AbstractEvent;
import com.github.anthonywww.mcbot.world.World;
import com.github.anthonywww.mcbot.world.block.Block;
import com.github.anthonywww.mcbot.world.block.BlockLocation;

public class BlockChangeEvent extends AbstractEvent {
	
	private final World world;
	private final BlockLocation location;
	private final Block oldBlock, newBlock;

	public BlockChangeEvent(World world, BlockLocation location, Block oldBlock, Block newBlock) {
		this.world = world;
		this.location = location;
		this.oldBlock = oldBlock;
		this.newBlock = newBlock;
	}

	public World getWorld() {
		return world;
	}

	public BlockLocation getLocation() {
		return location;
	}

	public Block getOldBlock() {
		return oldBlock;
	}

	public Block getNewBlock() {
		return newBlock;
	}
	
}