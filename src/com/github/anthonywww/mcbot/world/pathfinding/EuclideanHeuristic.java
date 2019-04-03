package com.github.anthonywww.mcbot.world.pathfinding;

import com.github.anthonywww.mcbot.world.block.BlockLocation;

public class EuclideanHeuristic implements Heuristic {

	@Override
	public float calculateCost(BlockLocation from, BlockLocation to) {
		// Euclidean Distance: sqrt((x1-x2)^2 + (y1-y2)^2 + (z1-z2)^2)
		//return MathHelper.getDistance(from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ());
		return from.getDistance(to);
	}
	
}
