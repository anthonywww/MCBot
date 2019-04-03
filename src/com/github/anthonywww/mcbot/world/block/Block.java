package com.github.anthonywww.mcbot.world.block;

import com.github.anthonywww.mcbot.world.World;

public class Block {
	
	private final World world;
	private final Chunk chunk;
	private final BlockLocation location;
	//private final String name;
	private final int id, metadata;

	public Block(World world, Chunk chunk, BlockLocation location, int id, int metadata) {
		this.world = world;
		this.chunk = chunk;
		this.location = location;
		this.id = id;
		this.metadata = metadata;
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
	
	public int getId() {
		return id;
	}

	public int getMetadata() {
		return metadata;
	}
}