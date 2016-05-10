package net.exodiusmc.example_monster_hunt.entity.living;

import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;
import net.exodiusmc.example_monster_hunt.entity.EntityType;
import net.exodiusmc.example_monster_hunt.entity.LivingEntity;
import net.exodiusmc.example_monster_hunt.entity.Util;

public class Demon extends LivingEntity {

	public Demon(Rectangle playField) {
		super(Util.RandomSpawnLocation(playField), EntityType.DEMON);

		setMaxHealth(2, true);
		setMovementSpeed(CoreUtils.randomDoubleInRange(0.1, 0.2));
	}

	@Override
	public void death() {
		
	}

}
