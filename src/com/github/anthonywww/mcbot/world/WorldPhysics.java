package com.github.anthonywww.mcbot.world;

import com.github.anthonywww.mcbot.world.block.BlockLocation;

public interface WorldPhysics {
	
	public BlockLocation[] findAdjacent(BlockLocation location);

	public boolean canWalk(BlockLocation from, BlockLocation to);

	public boolean canClimb(BlockLocation location);

	public World getWorld();
	
}
