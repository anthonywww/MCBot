package com.github.anthonywww.mcbot.world.entity;

import java.util.UUID;

import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;

public class Player extends LivingEntity {

	private String username;
	private UUID uuid;
	private boolean invincible;
	private boolean canFly;
	private GameMode gamemode;
	private float flySpeed;
	private float walkSpeed;
	private int food;

	public Player(String username) {
		super(0, username);
		this.username = username;
		this.invincible = false;
		this.canFly = false;
		this.gamemode = GameMode.SURVIVAL;
		this.uuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
	}
	
	
	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}

	public boolean isInvincible() {
		return invincible;
	}

	public void setInvincible(boolean invincible) {
		this.invincible = invincible;
	}

	public boolean getCanFly() {
		return canFly;
	}

	public void setCanFly(boolean canFly) {
		this.canFly = canFly;
	}

	public GameMode getGamemode() {
		return gamemode;
	}

	public void setGamemode(GameMode gamemode) {
		this.gamemode = gamemode;
	}

	public float getFlySpeed() {
		return flySpeed;
	}

	public void setFlySpeed(float flySpeed) {
		this.flySpeed = flySpeed;
	}

	public float getWalkSpeed() {
		return walkSpeed;
	}

	public void setWalkSpeed(float walkSpeed) {
		this.walkSpeed = walkSpeed;
	}

	public int getFood() {
		return food;
	}

	public void setFood(int food) {
		this.food = food;
	}
	
}
