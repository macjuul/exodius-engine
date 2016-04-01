package net.exodiusmc.example.entity;

import net.exodiusmc.engine.Location;
import net.exodiusmc.example.entity.living.Hero;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Location pos) {
		super(pos);
	}
	
	public abstract void pickup(Hero h);

}
