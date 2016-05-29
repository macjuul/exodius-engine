package net.exodiusmc.example_monster_hunt.entity.fixed;

import net.exodiusmc.engine.Rectangle;
import net.exodiusmc.example_monster_hunt.entity.EntityType;
import net.exodiusmc.example_monster_hunt.entity.living.Hero;

public class HeartPower extends Heart {

	public HeartPower(Rectangle playField) {
		super(playField, EntityType.POWER_HEART);
	}

	public void pickup(Hero p) {
		p.setHealth(p.getHealth() + 1);
	}
}
