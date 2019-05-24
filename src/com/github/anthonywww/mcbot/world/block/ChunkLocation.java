package com.github.anthonywww.mcbot.world.block;

import com.github.anthonywww.mcbot.world.WorldLocation;

public final class ChunkLocation {
	
	private final int x;
	private final int y;
	private final int z;
	private final int hashcode;
	private final String string;

	public ChunkLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		string = String.format("ChunkLocation[%d,%d,%d]", x, y, z);
		hashcode = string.hashCode();
	}

	public ChunkLocation(BlockLocation location) {
		this(location.getX() >> 4, location.getY() >> 4, location.getZ() >> 4);
	}

	public ChunkLocation(WorldLocation worldLocation) {
		this((int) (worldLocation.getX()) >> 4, (int) (worldLocation.getY()) >> 4, (int) (worldLocation.getZ()) >> 4);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getZ() {
		return z;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof ChunkLocation)) {
			return false;
		}
		ChunkLocation location = (ChunkLocation) obj;
		return location.getX() == x && location.getY() == y && location.getZ() == z;
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
