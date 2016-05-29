package net.exodiusmc.example_monster_hunt.entity;

import java.util.ArrayList;

import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.Rectangle;
import net.exodiusmc.example_monster_hunt.entity.living.Hero;

public abstract class LivingEntity extends Entity {
	private int health;
	private int maxHealth;
	private double movementSpeed = 1;
	private double maxAcceleration = 5;
	private double friction = 0.8;
	protected double acceleration_X = 0;
	protected double acceleration_Y = 0;
	
	public Integer hpBarTick = 0;
	public int damageId = 0;

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
		setMaxHealth(maxHealth, false);
	}

	public void setMaxHealth(int maxHealth, boolean update) {
		this.maxHealth = maxHealth;
		if(update) {
			this.health = maxHealth;
		}
	}

	public boolean damage(int a) {
		this.health -= a;
		this.hpBarTick = 0;
		if(this.health <= 0) {
			this.health = 0;
			death();
			return true;
		}
		return false;
	}

	public void heal(int a) {
		this.health += a;
		if (this.health >= this.maxHealth) {
			this.health = this.maxHealth;
		}
	}
	
	public void moveTo(Rectangle field, Location l, ArrayList<Entity> entities) {
		moveTo(field, l, entities, false);
	}

	public void moveTo(Rectangle field, Location l, ArrayList<Entity> entities, boolean flip) {
		double toLocX = getLocation().getX() - l.getX();
		double toLocY = getLocation().getY() - l.getY();

		double toPlayerLength = Math.sqrt(toLocX * toLocX + toLocY * toLocY);
		toLocX = toLocX / toPlayerLength;
		toLocY = toLocY / toPlayerLength;

		if(flip) {
			acceleration_X += toLocX * movementSpeed;
			acceleration_Y += toLocY * movementSpeed;
		} else {
			acceleration_X -= toLocX * movementSpeed;
			acceleration_Y -= toLocY * movementSpeed;
		}

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

		handleMovement(field, entities);
	}

	public void handleMovement(Rectangle field, ArrayList<Entity> entities) {
		boolean checkRect = this.getEntityType() == EntityType.HERO;
		saveLocation();

		getLocation().setX(getLocation().getX() + this.acceleration_X);
		if(!field.contains(getLocation()) && checkRect) {
			restoreLocation();
		}
		
		for(Entity e : entities) {
			if(e instanceof LivingEntity && !(e instanceof Hero) && e != this) {
				if(e.getLocation().distance(this.getLocation()) < 20) {
					restoreLocation();
				}
			}
		}

		this.acceleration_X *= this.friction;

		saveLocation();

		getLocation().setY(getLocation().getY() + this.acceleration_Y);
		if(!field.contains(getLocation()) && checkRect) {
			restoreLocation();
		}
		
		for(Entity e : entities) {
			if(e instanceof LivingEntity && !(e instanceof Hero) && e != this) {
				if(e.getLocation().distance(this.getLocation()) < 20) {
					restoreLocation();
				}
			}
		}

		this.acceleration_Y *= this.friction;
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}

	public double getMaxAcceleration() {
		return maxAcceleration;
	}

	public void setMaxAcceleration(double maxAcceleration) {
		this.maxAcceleration = maxAcceleration;
	}

	public double getFriction() {
		return friction;
	}

	public void setFriction(double friction) {
		this.friction = friction;
	}

	public abstract void death();
}
