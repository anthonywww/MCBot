package com.github.anthonywww.mcbot.world.pathfinding.astar;

import com.github.anthonywww.mcbot.world.World;
import com.github.anthonywww.mcbot.world.WorldPhysics;
import com.github.anthonywww.mcbot.world.block.BlockLocation;
import com.github.anthonywww.mcbot.world.pathfinding.Heuristic;
import com.github.anthonywww.mcbot.world.pathfinding.PathSearchProvider;

public class AStarPathSearchProvider implements PathSearchProvider {
	
	private final Heuristic heuristic;
	private final WorldPhysics worldPhysics;
	private final World world;

	public AStarPathSearchProvider(Heuristic heuristic, WorldPhysics worldPhysics) {
		this.heuristic = heuristic;
		this.worldPhysics = worldPhysics;

		world = worldPhysics.getWorld();
	}

	@Override
	public AStarPathSearch provideSearch(BlockLocation start, BlockLocation end) {
		return new AStarPathSearch(this, start, end);
	}

	@Override
	public Heuristic getHeuristic() {
		return heuristic;
	}

	@Override
	public WorldPhysics getWorldPhysics() {
		return worldPhysics;
	}

	@Override
	public World getWorld() {
		return world;
	}
}