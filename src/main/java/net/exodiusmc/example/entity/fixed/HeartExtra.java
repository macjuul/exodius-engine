package net.exodiusmc.example.entity.fixed;

import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.example.entity.EntityType;
import net.exodiusmc.example.entity.living.Hero;

public class HeartExtra extends Heart {

	public HeartExtra(Rectangle playField) {
		super(playField, EntityType.EXTRA_HEART);
	}

	public void pickup(Hero p) {
		p.setMaxHealth(p.getMaxHealth() + 1);
	}
	
}
