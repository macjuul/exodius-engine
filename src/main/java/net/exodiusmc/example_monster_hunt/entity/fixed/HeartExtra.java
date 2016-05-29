package net.exodiusmc.example_monster_hunt.entity.fixed;

import net.exodiusmc.engine.Rectangle;
import net.exodiusmc.example_monster_hunt.entity.EntityType;
import net.exodiusmc.example_monster_hunt.entity.living.Hero;

public class HeartExtra extends Heart {

	public HeartExtra(Rectangle playField) {
		super(playField, EntityType.EXTRA_HEART);
	}

	public void pickup(Hero p) {
		p.setMaxHealth(p.getMaxHealth() + 1);
	}
	
}
