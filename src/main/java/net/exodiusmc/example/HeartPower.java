package net.exodiusmc.example;

import net.exodiusmc.engine.shape.Rectangle;

public class HeartPower extends HeartEntity {
	
	public HeartPower(Rectangle playField) {
		super(playField);
	}

	public void pickup(Hero p) {
		p.setHealth(p.getHealth() + 1);
	}
}
