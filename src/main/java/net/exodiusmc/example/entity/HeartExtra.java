package net.exodiusmc.example.entity;

import net.exodiusmc.engine.shape.Rectangle;

public class HeartExtra extends Heart {

	public HeartExtra(Rectangle playField) {
		super(playField);
	}

	public void pickup(Hero p) {
		p.setMaxHealth(p.getMaxHealth() + 1);
	}
	
}
