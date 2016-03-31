package net.exodiusmc.example.entity;

import net.exodiusmc.engine.shape.Rectangle;

public class HeartPower extends Heart {
	
	public HeartPower(Rectangle playField) {
		super(playField);
	}

	public void pickup(Hero p) {
		p.setHealth(p.getHealth() + 1);
	}
}
