package net.exodiusmc.example.entity.living;

import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;
import net.exodiusmc.example.entity.EntityType;
import net.exodiusmc.example.entity.LivingEntity;
import net.exodiusmc.example.entity.Util;

public class Mummy extends LivingEntity {

	public Mummy(Rectangle playField) {
		super(Util.RandomSpawnLocation(playField), EntityType.MUMMY);
		
		setMaxHealth(1, true);
		setMovementSpeed(CoreUtils.randomDoubleInRange(0.5, 0.85));
	}

	@Override
	public void death() {
		
	}

}
