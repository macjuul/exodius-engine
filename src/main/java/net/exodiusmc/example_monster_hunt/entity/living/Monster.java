package net.exodiusmc.example_monster_hunt.entity.living;
import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;
import net.exodiusmc.example_monster_hunt.entity.EntityType;
import net.exodiusmc.example_monster_hunt.entity.LivingEntity;
import net.exodiusmc.example_monster_hunt.entity.Util;

public class Monster extends LivingEntity {
	
	public Monster(Rectangle playField) {
		super(Util.RandomSpawnLocation(playField), EntityType.MONSTER);
		
		setMaxHealth(1, true);
		setMovementSpeed(CoreUtils.randomDoubleInRange(0.2, 0.4));
	}

	@Override
	public void death() {
		
	}
	
}
