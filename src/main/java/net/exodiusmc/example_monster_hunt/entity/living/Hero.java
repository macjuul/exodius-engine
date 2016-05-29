package net.exodiusmc.example_monster_hunt.entity.living;

import javafx.scene.input.KeyCode;
import net.exodiusmc.engine.InputManager;
import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.util.GeneralUtils;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.engine.animation.SpriteAnimation;
import net.exodiusmc.engine.enums.Direction;
import net.exodiusmc.engine.Rectangle;
import net.exodiusmc.example_monster_hunt.Main;
import net.exodiusmc.example_monster_hunt.SwordDirection;
import net.exodiusmc.example_monster_hunt.entity.EntityType;
import net.exodiusmc.example_monster_hunt.entity.HeroType;
import net.exodiusmc.example_monster_hunt.entity.LivingEntity;
import net.exodiusmc.example_monster_hunt.layers.DeathLayer;

public class Hero extends LivingEntity {
	private SpriteAnimation sprite;
	private HeroType type;
	
	public Direction facing;
	public SwordDirection swordFacing;
	public double dmgTick = 0;
	public int attackTick = 0;
	
	public Hero(Location pos) {
		super(pos, EntityType.HERO);
		
		this.facing = Direction.DOWN;
		this.swordFacing = SwordDirection.DOWN;
		
		setMaxHealth(10, true);
		setMovementSpeed(0.6);
	}
	
	public void setSprite(SpriteAnimation sprite) {
		this.sprite = sprite;
	}
	
	public SpriteAnimation getSprite() {
		return this.sprite;
	}
	
	public void death() {}
	
	public void move(Direction d, Rectangle field) {
		switch(d) {
		case DOWN:
			this.acceleration_Y += getMovementSpeed();
			this.facing = d;
			break;
		case LEFT:
			this.acceleration_X -= getMovementSpeed();
			this.facing = d;
			break;
		case RIGHT:
			this.acceleration_X += getMovementSpeed();
			this.facing = d;
			break;
		case UP:
			this.acceleration_Y -= getMovementSpeed();
			this.facing = d;
			break;
		default:
			break;
		}
		
		InputManager input = InputManager.getManager();
		
		if(input.isKeyPressed(KeyCode.UP)
		&& input.isKeyPressed(KeyCode.RIGHT)) {
			this.swordFacing = SwordDirection.UP_RIGHT;
		} else if(input.isKeyPressed(KeyCode.RIGHT)
		&& input.isKeyPressed(KeyCode.DOWN)) {
			this.swordFacing = SwordDirection.RIGHT_DOWN;
		} else if(input.isKeyPressed(KeyCode.DOWN)
		&& input.isKeyPressed(KeyCode.LEFT)) {
			this.swordFacing = SwordDirection.DOWN_LEFT;
		} else if(input.isKeyPressed(KeyCode.LEFT)
		&& input.isKeyPressed(KeyCode.UP)) {
			this.swordFacing = SwordDirection.LEFT_UP;
		} else {
			this.swordFacing = SwordDirection.valueOf(this.facing.toString());
		}
	}
	
	public boolean damageHero(int a, int score) {
		this.setHealth(this.getHealth() - a);
		this.dmgTick = 3;
		this.hpBarTick = 0;
		if(this.getHealth() <= 0) {
			this.setHealth(0);
			
			GeneralUtils.setTimeout(100L, () -> {
				Main.engine.getLayerManager().add(new DeathLayer(score));
			});
			
			return true;
		}
		return false;
	}

	public HeroType getType() {
		return type;
	}

	public void setType(HeroType type, SpriteAnimation s) {
		this.type = type;
		s.setImage(FileUtils.LoadImage(type.getFileName()));
	}

	public Direction getFacing() {
		return facing;
	}
}
