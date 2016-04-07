package net.exodiusmc.example.entity.living;

import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.animation.SpriteAnimation;
import net.exodiusmc.engine.enums.Direction;
import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.example.Main;
import net.exodiusmc.example.MovementKeys;
import net.exodiusmc.example.entity.EntityType;
import net.exodiusmc.example.entity.HeroType;
import net.exodiusmc.example.entity.LivingEntity;
import net.exodiusmc.example.layers.DeathLayer;

public class Hero extends LivingEntity {
	private SpriteAnimation sprite;
	private HeroType type;
	private MovementKeys keys;
	
	public Direction facing;
	public Direction facingCache;
	public double damageTick = 0;
	public double dmgTick = 0;
	
	public Hero(Location pos) {
		super(pos, EntityType.HERO);
		
		this.facing = Direction.DOWN;
		this.facingCache = Direction.DOWN;
		this.keys = MovementKeys.ARROWS;
		
		setMaxHealth(500, true);
		setMovementSpeed(0.6);
	}
	
	public void setSprite(SpriteAnimation sprite) {
		this.sprite = sprite;
	}
	
	public SpriteAnimation getSprite() {
		return this.sprite;
	}
	
	public void death() {
		Main.engine.getLayerManager().add(new DeathLayer());
	}
	
	public void move(Direction d, Rectangle field) {
		switch(d) {
		case DOWN:
			this.acceleration_Y += getMovementSpeed();
			break;
		case LEFT:
			this.acceleration_X -= getMovementSpeed();
			break;
		case RIGHT:
			this.acceleration_X += getMovementSpeed();
			break;
		case UP:
			this.acceleration_Y -= getMovementSpeed();
			break;
		default:
			break;
		}
	}
	
	public void damageHero(int a) {
		this.setHealth(this.getHealth() - a);
		this.dmgTick = 1;
		if(this.getHealth() <= 0) {
			this.setHealth(0);
			death();
		}
	}

	public HeroType getType() {
		return type;
	}

	public void setType(HeroType type, SpriteAnimation s) {
		this.type = type;
		s.setImage(FileUtils.LoadImage(type.getFileName()));
	}
}
