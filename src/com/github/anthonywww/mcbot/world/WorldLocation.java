package com.github.anthonywww.mcbot.world;

import com.github.anthonywww.mcbot.utils.MathHelper;
import com.github.anthonywww.mcbot.world.block.BlockLocation;
import com.github.anthonywww.mcbot.world.block.ChunkLocation;

public class WorldLocation {

	private final float x, y, z;
	private final int hashcode;
	private final String string;

	public WorldLocation(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		string = String.format("WorldLocation[%.2f,%.2f,%.2f]", x, y, z);
		hashcode = string.hashCode();
	}

	public WorldLocation(BlockLocation location) {
		this(location.getX() + 0.5f, location.getY(), location.getZ() + 0.5f);
	}

	public WorldLocation(ChunkLocation location) {
		this(location.getX() * 16, location.getY() * 16, location.getZ() * 16);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public float getZ() {
		return z;
	}

	public double getDistanceTo(WorldLocation other) {
		return Math.sqrt(getDistanceToSquared(other));
	}

	public double getDistanceToSquared(WorldLocation other) {
		return MathHelper.power(x - other.x, 2) + MathHelper.power(y - other.y, 2) + MathHelper.power(z - other.z, 2);
	}

	public WorldLocation offset(WorldLocation location) {
		return offset(location.x, location.y, location.z);
	}

	public WorldLocation offset(float x, float y, float z) {
		return new WorldLocation(this.x + x, this.y + y, this.z + z);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof WorldLocation)) {
			return false;
		}
		WorldLocation location = (WorldLocation) obj;
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
