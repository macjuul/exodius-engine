package net.exodiusmc.example.entity;

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

	public void setLocation(Location pos) {
		this.prevPos = this.pos.clone();
		this.pos = pos;
	}
	
	public void moveTo(Rectangle field, Location l) {
		moveTo(field, l, false);
	}
	
	public void moveTo(Rectangle field, Location l, boolean checkField) {
		double toLocX = pos.getX() - l.getX();
		double toLocY = pos.getY() - l.getY();
		
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
		
		this.pos.setX(this.pos.getX() + this.acceleration_X);
		if(!field.contains(this.pos) && checkRect) {
			restoreLocation();
    	}
		
    	this.acceleration_X *= 0.8;
		
    	saveLocation();
    	
		this.pos.setY(this.pos.getY() + this.acceleration_Y);
		if(!field.contains(this.pos) && checkRect) {
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
	
	public void saveLocation() {
		this.prevPos = this.pos.clone();
	}
	
	public void restoreLocation() {
		this.pos = this.prevPos.clone();
	}
}
