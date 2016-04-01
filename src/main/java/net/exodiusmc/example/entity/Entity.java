package net.exodiusmc.example.entity;

import net.exodiusmc.engine.Location;

public abstract class Entity {
	private Location pos;
	private Location prevPos;
	
	public Entity(Location pos) {
		this.pos = pos;
	}
	
	public Location getLocation() {
		return this.pos;
	}

	public void setLocation(Location pos) {
		this.prevPos = this.pos.clone();
		this.pos = pos;
	}
	
	public void saveLocation() {
		this.prevPos = this.pos.clone();
	}
	
	public void restoreLocation() {
		this.pos = this.prevPos.clone();
	}
}
