package com.github.anthonywww.mcbot.entity;

public class LivingEntity extends Entity {
	
	private float health;
	private boolean isDead;
	private boolean isOnGround;
	
	public LivingEntity() {
		super();
		health = 20.0f;
		isDead = false;
	}
	
	public LivingEntity(float health) {
		super();
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
	
}
