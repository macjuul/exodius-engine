package net.exodiusmc.example;

import net.exodiusmc.engine.Location;

public class Hero extends Entity {
	private int maxHealth = 20;
	private int health = maxHealth;
	
	public Hero(Location pos) {
		super(pos);
		
		setMovementSpeed(0.6);
	}
	
	public void damage() {
		this.health--;
		
		if(this.health <= 0) {
			death();
		}
	}
	
	public void death() {
		
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
}
