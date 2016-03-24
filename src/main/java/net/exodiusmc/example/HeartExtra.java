package net.exodiusmc.example;

import net.exodiusmc.engine.shape.Rectangle;

public class HeartExtra extends HeartEntity {

	public HeartExtra(Rectangle playField) {
		super(playField);
	}

	public void pickup(Hero p) {
		p.setMaxHealth(p.getMaxHealth() + 1);
	}
	
}
