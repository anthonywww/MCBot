package com.github.anthonywww.mcbot.world.pathfinding;

import com.github.anthonywww.mcbot.world.block.BlockLocation;

public interface PathSearch {
	
	public void step();

	public boolean isDone();

	public BlockLocation getStart();

	public BlockLocation getEnd();

	public PathNode getPath();

	public PathSearchProvider getSource();
	
}
