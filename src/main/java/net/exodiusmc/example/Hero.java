package net.exodiusmc.example;

import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.animation.SpriteAnimation;
import net.exodiusmc.engine.enums.Direction;

public class Hero extends Entity {
	private int maxHealth = 10;
	private int health = maxHealth;
	private SpriteAnimation sprite;
	
	public Direction facingCache;
	public double damageTick = 0;
	public double dmgTick = 0;
	
	public Hero(Location pos) {
		super(pos);
		
		setMovementSpeed(0.6);
	}
	
	public void setSprite(SpriteAnimation sprite) {
		this.sprite = sprite;
	}
	
	public SpriteAnimation getSprite() {
		return this.sprite;
	}
	
	public void damage() {
		this.damageTick = 1;
		this.dmgTick = 1;
		this.health--;
		
		if(this.health <= 0) {
			death();
			this.health = 0;
		}
	}
	
	public void death() {
		
	}

	public int getHealth() {
		return health;
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	public void setHealth(int health) {
		this.health = health;
		if(this.health >= maxHealth) this.health = this.maxHealth;
	}
}
