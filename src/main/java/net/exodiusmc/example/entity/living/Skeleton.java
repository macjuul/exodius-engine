package net.exodiusmc.example.entity.living;

import java.util.List;

import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;
import net.exodiusmc.example.entity.Entity;
import net.exodiusmc.example.entity.EntityType;
import net.exodiusmc.example.entity.LivingEntity;
import net.exodiusmc.example.entity.Util;
import net.exodiusmc.example.entity.fixed.Arrow;

public class Skeleton extends LivingEntity {
	public int fireTick;

	public Skeleton(Rectangle playField) {
		super(Util.RandomSpawnLocation(playField), EntityType.SKELETON);
		
		setMaxHealth(1, true);
		setMovementSpeed(CoreUtils.randomDoubleInRange(0.35, 0.45));
		setMaxAcceleration(0.7);
		setFriction(0.93);
	}

	@Override
	public void death() {
		
	}
	
	public void shoot(List<Entity> entities, Location target) {
		entities.add(new Arrow(this.getLocation(), EntityType.ARROW, target));
	}

}
