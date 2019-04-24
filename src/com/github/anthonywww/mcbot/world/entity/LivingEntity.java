package com.github.anthonywww.mcbot.world.entity;

public class LivingEntity extends Entity {
	
	private float health;
	private boolean isDead;
	private boolean isOnGround;
	
	public LivingEntity(int eid) {
		super(eid);
		health = 20.0f;
		isDead = false;
	}
	
	public LivingEntity(int eid, String name) {
		super(eid, name);
		health = 20.0f;
		isDead = false;
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public boolean isDead() {
		return isDead;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public boolean isOnGround() {
		return isOnGround;
	}

	public void setOnGround(boolean isOnGround) {
		this.isOnGround = isOnGround;
	}

	@Override
	public String toString() {
		return "[LivingEntity: " + name + " " + super.toString() + "]";
	}
	
}
