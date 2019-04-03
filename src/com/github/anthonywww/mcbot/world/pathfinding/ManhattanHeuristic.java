package com.github.anthonywww.mcbot.world.pathfinding;

import com.github.anthonywww.mcbot.world.block.BlockLocation;

public class ManhattanHeuristic implements Heuristic {
	
	@Override
	public float calculateCost(BlockLocation from, BlockLocation to) {
		// Manhattan Distance: |x1-x2| + |y1-y2| + |z1-z2|
		return (float) Math.abs(from.getX() - to.getX()) + Math.abs(from.getY() - to.getY()) + Math.abs(from.getZ() - to.getZ());
	}
	
}
