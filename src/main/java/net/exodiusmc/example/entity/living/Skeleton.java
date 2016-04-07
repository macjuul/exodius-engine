package net.exodiusmc.example.entity.living;

import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;
import net.exodiusmc.example.entity.EntityType;
import net.exodiusmc.example.entity.LivingEntity;
import net.exodiusmc.example.entity.Util;

public class Skeleton extends LivingEntity {

	public Skeleton(Rectangle playField) {
		super(Util.RandomSpawnLocation(playField), EntityType.SKELETON);
		
		setMaxHealth(1, true);
		setMovementSpeed(CoreUtils.randomDoubleInRange(0.2, 0.4));
	}

	@Override
	public void death() {
		
	}

}
