package com.github.anthonywww.mcbot.world.pathfinding;

import com.github.anthonywww.mcbot.world.block.BlockLocation;

public interface Heuristic {
	
	public float calculateCost(BlockLocation from, BlockLocation to);
	
}
