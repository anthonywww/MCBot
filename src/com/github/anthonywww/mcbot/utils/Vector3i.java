package com.github.anthonywww.mcbot.utils;

public class Vector3i {
	
	private int x;
	private int y;
	private int z;
	
	public Vector3i() {
		this(0, 0, 0);
	}
	
	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Get the X component of the vector
	 * @return
	 */
	public int getX() {
		return x;
	}

	/**
	 * Set the X component of the vector
	 * @return
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Get the Y component of the vector
	 * @return
	 */
	public int getY() {
		return y;
	}

	/**
	 * Set the Y component of the vector
	 * @return
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Get the Z component of the vector
	 * @return
	 */
	public int getZ() {
		return z;
	}

	/**
	 * Set the Z component of the vector
	 * @return
	 */
	public void setZ(int z) {
		this.z = z;
	}
	
	/**
	 * Get the length (magnitude) of this vector squared
	 * @return
	 */
	public double lengthSquared() {
		return MathHelper.get3DVectorLengthSquared(x, y, z);
	}
	
	/**
	 * Get the length (magnitude) of this vector
	 * @return
	 */
	public double length() {
		return MathHelper.get3DVectorLength(x, y, z);
	}
	
}
