package net.exodiusmc.example;

import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.enums.Direction;
import net.exodiusmc.engine.shape.Rectangle;

public class Entity {
	private Location pos;
	private Location prevPos;
	private double movementSpeed = 1;
	private double maxAcceleration = 5;
	private double acceleration_X;
	private double acceleration_Y;

	public Direction facing;
	
	public Entity(Location pos) {
		this.pos = pos;
		this.acceleration_X = 0;
		this.acceleration_Y = 0;
	}
	
	public Location getLocation() {
		return this.pos;
	}

	public void setPos(Location pos) {
		this.prevPos = this.pos.clone();
		this.pos = pos;
	}
	public void move(Direction d, double dt) {
		move(d, dt, null);
	}
	
	public void move(Direction d, double dt, Location l) {
		switch(d) {
		case UP:
			this.acceleration_Y = Math.max(acceleration_Y - movementSpeed, -this.maxAcceleration);
			this.facing = Direction.UP;
			break;
		case RIGHT:
			this.acceleration_X = Math.min(acceleration_X + movementSpeed, this.maxAcceleration);
			this.facing = Direction.RIGHT;
			break;
		case DOWN:
			this.acceleration_Y = Math.min(acceleration_Y + movementSpeed, this.maxAcceleration);
			this.facing = Direction.DOWN;
			break;
		case LEFT:
			this.acceleration_X = Math.max(acceleration_X - movementSpeed, -this.maxAcceleration);
			this.facing = Direction.LEFT;
			break;
		case CUSTOM:
			double toPlayerX = pos.getX() - l.getX();
			double toPlayerY = pos.getY() - l.getY();
			
			double toPlayerLength = Math.sqrt(toPlayerX * toPlayerX + toPlayerY * toPlayerY);
			toPlayerX = toPlayerX / toPlayerLength;
			toPlayerY = toPlayerY / toPlayerLength;
			
			acceleration_X -= toPlayerX * dt * movementSpeed;
			acceleration_Y -= toPlayerY * dt * movementSpeed;
			
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
		}
	}
	
	public void handleMovement(double dt, Rectangle field) {
		handleMovement(dt, field, true);
	}
	
	public void handleMovement(double dt, Rectangle field, boolean checkRect) {
		saveLoc();
		this.pos.setX(this.pos.getX() + this.acceleration_X);
		if(!field.contains(this.pos) && checkRect) {
    		undoMovement();
    	}
    	this.acceleration_X *= 0.8;
		
    	saveLoc();
		this.pos.setY(this.pos.getY() + this.acceleration_Y);
		if(!field.contains(this.pos) && checkRect) {
    		undoMovement();
    	}
    	this.acceleration_Y *= 0.8;
	}

	public double getMovementSpeed() {
		return movementSpeed;
	}

	public void setMovementSpeed(double movementSpeed) {
		this.movementSpeed = movementSpeed;
	}
	
	public void saveLoc() {
		this.prevPos = this.pos.clone();
	}
	
	public void undoMovement() {
		this.pos = this.prevPos.clone();
	}
}
