package net.exodiusmc.example.entity.fixed;

import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;
import net.exodiusmc.example.entity.EntityType;
import net.exodiusmc.example.entity.StaticEntity;
import net.exodiusmc.example.entity.living.Hero;

public abstract class Heart extends StaticEntity {

	public Heart(Rectangle playField, EntityType type) {
		super(playField.getLocationRelative(CoreUtils.randomDoubleInRange(0, 1), CoreUtils.randomDoubleInRange(0, 1)), type);
	}

	@Override
	public void pickup(Hero h) {
		
	}
}
