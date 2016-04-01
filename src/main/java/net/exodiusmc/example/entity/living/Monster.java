package net.exodiusmc.example.entity.living;
import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;
import net.exodiusmc.example.entity.LivingEntity;
import net.exodiusmc.example.entity.Util;

public class Monster extends LivingEntity {
	
	public Monster(Rectangle playField) {
		super(Util.RandomSpawnLocation(playField));
		
		setMaxHealth(1);
		setMovementSpeed(CoreUtils.randomDoubleInRange(0.5, 0.85));
	}

	@Override
	public void death() {
		
	}

}
