package com.github.anthonywww.mcbot.world.block;

import com.github.anthonywww.mcbot.world.World;

public class Block {
	
	private final World world;
	private final Chunk chunk;
	private final BlockLocation location;
	private final BlockType type;
	private final int metadata;
	private final String string;
	private final int hashcode;

	public Block(World world, Chunk chunk, BlockLocation location, BlockType type, int metadata) {
		this.world = world;
		this.chunk = chunk;
		this.location = location;
		this.type = type;
		this.metadata = metadata;
		string = String.format("Block[%s,%s,%s,%s]", world.toString(), chunk.toString(), location.toString(), metadata);
		hashcode = string.hashCode();
	}
	
	public Block(World world, Chunk chunk, BlockLocation location, BlockType type) {
		this(world, chunk, location, type, 0);
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
	
	public int getMetadata() {
		return metadata;
	}
	
	@Override
	public String toString() {
		return string;
	}
	
	@Override
	public int hashCode() {
		return hashcode;
	}
}