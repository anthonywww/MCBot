package com.github.anthonywww.mcbot.world.entity;

import org.joml.Vector3d;

import com.github.anthonywww.mcbot.world.World;
import com.github.anthonywww.mcbot.world.WorldLocation;
import com.github.anthonywww.mcbot.world.block.BlockLocation;

public class Entity {

	protected int id;
	protected String name;
	protected World world;
	protected Vector3d position;
	protected Vector3d velocity;
	protected Vector3d size;
	protected float yaw;
	protected float pitch;
	protected Entity rider;
	protected Entity riding;
	protected boolean dead;

	public Entity(int entityId) {
		this(entityId, "");
	}

	public Entity(int entityId, String name) {
		this(entityId, name, new Vector3d(), new Vector3d());
	}

	public Entity(int entityId, String name, Vector3d position, Vector3d velocity) {
		this.id = entityId;
		this.name = name;
		this.position = position;
		this.velocity = velocity;
		this.size = new Vector3d(1, 1, 1);
		this.dead = false;
		this.yaw = 0.0f;
		this.pitch = 0.0f;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public Vector3d getPosition() {
		return position;
	}

	public void setPosition(Vector3d position) {
		this.position = position;
	}

	public double getX() {
		return position.x;
	}

	public void setX(double x) {
		this.position.x = x;
	}

	public double getY() {
		return position.y;
	}

	public void setY(double y) {
		this.position.y = y;
	}

	public double getZ() {
		return position.z;
	}

	public void setZ(double z) {
		this.position.z = z;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public double getVelocity() {
		return velocity.length();
	}
	
	public void setVelocity(Vector3d velocity) {
		this.velocity = velocity;
	}
	
	public void setRider(Entity rider) {
		this.rider = rider;
	}

	public void setRiding(Entity riding) {
		this.riding = riding;
	}
	
	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public double getVelocityHorizontalAngle() {
		return Math.atan2(velocity.z, velocity.x);
	}

	public double getVelocityVerticalAngle() {
		return Math.atan2(velocity.y, Math.hypot(velocity.x, velocity.z));
	}

	public void accelerate(double horizAngle, double vertAngle, double accel) {
		velocity.x += accel * Math.cos(horizAngle) * Math.cos(vertAngle);
		velocity.z += accel * Math.sin(horizAngle) * Math.cos(vertAngle);
		velocity.y += accel * Math.sin(vertAngle);
	}

	public void accelerate(double horizAngle, double vertAngle, double accel, double velocityBound) {
		double ax = Math.abs(accel * Math.cos(horizAngle) * Math.cos(vertAngle));
		double az = Math.abs(accel * Math.sin(horizAngle) * Math.cos(vertAngle));
		double ay = Math.abs(accel * Math.sin(vertAngle));

		double vxb = velocityBound * Math.cos(horizAngle) * Math.cos(vertAngle);
		double vzb = velocityBound * Math.sin(horizAngle) * Math.cos(vertAngle);
		double vyb = velocityBound * Math.sin(vertAngle);

		if (vxb < 0 && velocity.x > vxb) {
			velocity.x = Math.max(vxb, velocity.x - ax);
		} else if (vxb > 0 && velocity.x < vxb) {
			velocity.x = Math.min(vxb, velocity.x + ax);
		}

		if (vzb < 0 && velocity.z > vzb) {
			velocity.z = Math.max(vzb, velocity.z - az);
		} else if (vzb > 0 && velocity.z < vzb) {
			velocity.z = Math.min(vzb, velocity.z + az);
		}
		
		if (vyb < 0 && velocity.y > vyb) {
			velocity.y = Math.max(vyb, velocity.y - ay);
		} else if (vyb > 0 && velocity.y < vyb) {
			velocity.y = Math.min(vyb, velocity.y + ay);
		}
	}

	public double getDistanceTo(double x, double y, double z) {
		return Math.sqrt(Math.pow(this.position.x - x, 2) + Math.pow(this.position.y - y, 2) + Math.pow(this.position.z - z, 2));
	}

	public int getDistanceToSquared(double x, double y, double z) {
		return (int) (Math.pow(this.position.x - x, 2) + Math.pow(this.position.y - y, 2) + Math.pow(this.position.z - z, 2));
	}

	public double getDistanceTo(Entity other) {
		return getDistanceTo(other.getX(), other.getY(), other.getZ());
	}

	public int getDistanceToSquared(Entity other) {
		return getDistanceToSquared(other.getX(), other.getY(), other.getZ());
	}

	public double getDistanceTo(WorldLocation location) {
		return getDistanceTo(location.getX(), location.getY(), location.getZ());
	}

	public double getDistanceToSquared(WorldLocation location) {
		return getDistanceToSquared(location.getX(), location.getY(), location.getZ());
	}

	public double getDistanceTo(BlockLocation location) {
		return getDistanceTo(location.getX() + 0.5, location.getY(), location.getZ() + 0.5);
	}

	public double getDistanceToSquared(BlockLocation location) {
		return getDistanceToSquared(location.getX() + 0.5, location.getY(), location.getZ() + 0.5);
	}

	@Override
	public String toString() {
		return "[Entity: " + name + " " + super.toString() + "]";
	}

}
