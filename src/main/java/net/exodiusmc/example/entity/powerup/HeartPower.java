package net.exodiusmc.example.entity.powerup;

import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.example.entity.living.Hero;

public class HeartPower extends Heart {
	
	public HeartPower(Rectangle playField) {
		super(playField);
	}

	public void pickup(Hero p) {
		p.setHealth(p.getHealth() + 1);
	}
}
