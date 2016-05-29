package net.exodiusmc.example_monster_hunt.entity.living;

import net.exodiusmc.engine.util.GeneralUtils;
import net.exodiusmc.engine.Rectangle;
import net.exodiusmc.example_monster_hunt.Util;
import net.exodiusmc.example_monster_hunt.entity.EntityType;
import net.exodiusmc.example_monster_hunt.entity.LivingEntity;

public class Demon extends LivingEntity {

	public Demon(Rectangle playField) {
		super(Util.RandomSpawnLocation(playField), EntityType.DEMON);

		setMaxHealth(2, true);
		setMovementSpeed(GeneralUtils.randomDoubleInRange(0.1, 0.2));
	}

	@Override
	public void death() {
		
	}

}
