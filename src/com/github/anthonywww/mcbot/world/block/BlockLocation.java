package com.github.anthonywww.mcbot.world.block;

import com.github.anthonywww.mcbot.utils.MathHelper;
import com.github.anthonywww.mcbot.world.WorldLocation;

public final class BlockLocation {
	
	private final int x;
	private final int y;
	private final int z;
	private final int hashcode;
	private final String string;
	
	public BlockLocation(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
		string = "BlockLocation[" + x + "," + y + "," + z + "]";
		hashcode = string.hashCode();
	}
	
	public BlockLocation(WorldLocation location) {
		this((int) Math.floor(location.getX()), (int) Math.floor(location.getY()), (int) Math.floor(location.getZ()));
	}
	
	public BlockLocation(ChunkLocation location) {
		this(location.getX() << 4, location.getY() << 4, location.getZ() << 4);
	}
	
	/**
	 * Get X position
	 * @return
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Get Y position
	 * @return
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Get Z position
	 * @return
	 */
	public int getZ() {
		return z;
	}
	
	/**
	 * Get the distance to the other BlockLocation (slow!)
	 * @param other
	 * @return
	 */
	public float getDistance(BlockLocation other) {
		return (float) Math.sqrt(getDistance(other));
	}
	
	/**
	 * Get the squared distance to the other BlockLocation
	 * @param other
	 * @return
	 */
	public float getDistanceSq(BlockLocation other) {
		//return (float) (Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2) + Math.pow(z - other.z, 2));
		return MathHelper.power(x - other.x, 2) + MathHelper.power(y - other.y, 2) + MathHelper.power(z - other.z, 2);
	}
	
	/**
	 * Get a new BlockLocation from a offset of a BlockLocation
	 * @param location
	 * @return
	 */
	public BlockLocation offset(BlockLocation location) {
		return offset(location.x, location.y, location.z);
	}
	
	/**
	 * Get a new BlockLocation from x, y, z positional offsets
	 * @param x
	 * @param y
	 * @param z
	 * @return
	 */
	public BlockLocation offset(int x, int y, int z) {
		return new BlockLocation(this.x+x, this.y+y, this.z+z);
	}
	
	/**
	 * Returns true if a BlockLocation occupy's the same location as this one
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof BlockLocation)) {
			return false;
		}
		
		BlockLocation location = (BlockLocation) obj;
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
