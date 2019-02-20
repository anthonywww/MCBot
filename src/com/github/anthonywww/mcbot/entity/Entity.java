package com.github.anthonywww.mcbot.entity;

import org.joml.Vector3d;

public class Entity {
	
	private int entityId;
	private String name;
	private Vector3d position;
	private float yaw;
	private float pitch;
	
	public Entity() {
		this(0);
	}
	
	public Entity(int entityId) {
		this(entityId, "");
	}
	
	public Entity(int entityId, String name) {
		this(entityId, name, new Vector3d());
	}
	
	public Entity(int entityId, String name, Vector3d position) {
		this.entityId = entityId;
		this.name = name;
		this.position = position;
		yaw = 0.0f;
		pitch = 0.0f;
	}


	public String getName() {
		return name;
	}
	
	public int getEntityId() {
		return entityId;
	}

	public void setEntityId(int entityId) {
		this.entityId = entityId;
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
	
	@Override
	public String toString() {
		return "[Entity: " + name + " " + super.toString() + "]";
	}
	
}
