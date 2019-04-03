package com.github.anthonywww.mcbot.world.pathfinding;

import com.github.anthonywww.mcbot.world.World;
import com.github.anthonywww.mcbot.world.block.BlockLocation;

public interface PathSearchProvider {
	
	public PathSearch provideSearch(BlockLocation start, BlockLocation end);

	public Heuristic getHeuristic();

	public WorldPhysics getWorldPhysics();

	public World getWorld();
	
}
