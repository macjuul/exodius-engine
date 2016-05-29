package net.exodiusmc.example_monster_hunt.entity.fixed;

import net.exodiusmc.engine.util.GeneralUtils;
import net.exodiusmc.engine.Rectangle;
import net.exodiusmc.example_monster_hunt.entity.EntityType;
import net.exodiusmc.example_monster_hunt.entity.StaticEntity;
import net.exodiusmc.example_monster_hunt.entity.living.Hero;

public abstract class Heart extends StaticEntity {

	public Heart(Rectangle playField, EntityType type) {
		super(playField.getLocationRelative(GeneralUtils.randomDoubleInRange(0, 1), GeneralUtils.randomDoubleInRange(0, 1)), type);
	}

	@Override
	public void pickup(Hero h) {
		
	}
}
