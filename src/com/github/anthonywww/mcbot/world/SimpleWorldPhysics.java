package com.github.anthonywww.mcbot.world;

import com.github.anthonywww.mcbot.world.block.BlockLocation;

public class SimpleWorldPhysics implements WorldPhysics {
	
	private final World world;
	private boolean[] emptyBlocks;
	private static final BlockLocation[] SURROUNDING = new BlockLocation[] {
		// middle y + 0
		new BlockLocation(-1, 0, 1), new BlockLocation(0, 0, 1), new BlockLocation(1, 0, 1), new BlockLocation(-1, 0, 0), new BlockLocation(1, 0, 0),
		new BlockLocation(-1, 0, -1), new BlockLocation(0, 0, -1),
		new BlockLocation(1, 0, -1),
		// bottom y - 1
		new BlockLocation(-1, -1, 1), new BlockLocation(0, -1, 1), new BlockLocation(1, -1, 1), new BlockLocation(-1, -1, 0), new BlockLocation(0, -1, 0),
		new BlockLocation(1, -1, 0), new BlockLocation(-1, -1, -1), new BlockLocation(0, -1, -1), new BlockLocation(1, -1, -1),
		// top y + 1
		new BlockLocation(-1, 1, 1), new BlockLocation(0, 1, 1), new BlockLocation(1, 1, 1), new BlockLocation(-1, 1, 0), new BlockLocation(0, 1, 0),
		new BlockLocation(1, 1, 0), new BlockLocation(-1, 1, -1), new BlockLocation(0, 1, -1), new BlockLocation(1, 1, -1)
	};
	
	public SimpleWorldPhysics(World world) {
		this.world = world;
		emptyBlocks = new boolean[world.getMaxHeight()];
//		for (BlockType type : BlockType.values()) {
//			
//		}
	}
	
	@Override
	public BlockLocation[] findAdjacent(BlockLocation location) {
		return null;
	}

	@Override
	public boolean canWalk(BlockLocation from, BlockLocation to) {
		
		return false;
	}

	@Override
	public boolean canClimb(BlockLocation location) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public World getWorld() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
