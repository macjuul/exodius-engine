package net.exodiusmc.example.entity;

import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.shape.Rectangle;

public abstract class LivingEntity extends Entity {
	private int health;
	private int maxHealth;
	private double movementSpeed = 1;
	private double maxAcceleration = 5;
	private double acceleration_X = 0;
	private double acceleration_Y = 0;

	public LivingEntity(Location pos, EntityType type) {
		super(pos, type);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
	}

	public void damage(int a) {
		this.health -= a;
		if (this.health <= 0) {
			this.health = 0;
			death();
		}
	}

	public void heal(int a) {
		this.health += a;
		if (this.health >= this.maxHealth) {
			this.health = this.maxHealth;
		}
	}

	public void moveTo(Rectangle field, Location l) {
		moveTo(field, l, false);
	}

	public void moveTo(Rectangle field, Location l, boolean checkField) {
		double toLocX = getLocation().getX() - l.getX();
		double toLocY = getLocation().getY() - l.getY();

		double toPlayerLength = Math.sqrt(toLocX * toLocX + toLocY * toLocY);
		toLocX = toLocX / toPlayerLength;
		toLocY = toLocY / toPlayerLength;

		acceleration_X -= toLocX * movementSpeed;
		acceleration_Y -= toLocY * movementSpeed;

		if(this.acceleration_X > this.maxAcceleration) {
			this.acceleration_X = this.maxAcceleration;
		} else if(this.acceleration_X < -this.maxAcceleration) {
			this.acceleration_X = -this.maxAcceleration;
		}

		if(this.acceleration_Y > this.maxAcceleration) {
			this.acceleration_Y = this.maxAcceleration;
		} else if(this.acceleration_Y < -this.maxAcceleration) {
			this.acceleration_Y = -this.maxAcceleration;
		}

		handleMovement(field, checkField);
	}

	private void handleMovement(Rectangle field, boolean checkRect) {
		saveLocation();

		getLocation().setX(getLocation().getX() + this.acceleration_X);
		if(!field.contains(getLocation()) && checkRect) {
			restoreLocation();
		}

		this.acceleration_X *= 0.8;

		saveLocation();

		getLocation().setY(getLocation().getY() + this.acceleration_Y);
		if(!field.contains(getLocation()) && checkRect) {
			restoreLocation();
		}

		this.acceleration_Y *= 0.8;
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public abstract void death();
}
