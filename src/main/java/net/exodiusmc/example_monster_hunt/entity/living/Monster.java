package net.exodiusmc.example_monster_hunt.entity.living;
import net.exodiusmc.engine.util.GeneralUtils;
import net.exodiusmc.engine.Rectangle;
import net.exodiusmc.example_monster_hunt.Util;
import net.exodiusmc.example_monster_hunt.entity.EntityType;
import net.exodiusmc.example_monster_hunt.entity.LivingEntity;

public class Monster extends LivingEntity {
	
	public Monster(Rectangle playField) {
		super(Util.RandomSpawnLocation(playField), EntityType.MONSTER);
		
		setMaxHealth(1, true);
		setMovementSpeed(GeneralUtils.randomDoubleInRange(0.2, 0.4));
	}

	@Override
	public void death() {
		
	}
	
}
