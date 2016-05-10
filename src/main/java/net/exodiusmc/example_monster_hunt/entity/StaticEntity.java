package net.exodiusmc.example_monster_hunt.entity;

import net.exodiusmc.engine.Location;
import net.exodiusmc.example_monster_hunt.entity.living.Hero;

public abstract class StaticEntity extends Entity {

	public StaticEntity(Location pos, EntityType type) {
		super(pos, type);
	}
	
	public abstract void pickup(Hero h);

}
