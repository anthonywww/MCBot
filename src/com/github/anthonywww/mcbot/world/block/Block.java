package com.github.anthonywww.mcbot.world.block;

import com.github.anthonywww.mcbot.world.World;

public class Block {
	
	private final World world;
	private final Chunk chunk;
	private final BlockLocation location;
	private final BlockType type;

	public Block(World world, Chunk chunk, BlockLocation location, BlockType type) {
		this.world = world;
		this.chunk = chunk;
		this.location = location;
		this.type = type;
	}

	public World getWorld() {
		return world;
	}

	public Chunk getChunk() {
		return chunk;
	}

	public BlockLocation getLocation() {
		return location;
	}
	
	public BlockType getType() {
		return type;
	}
}