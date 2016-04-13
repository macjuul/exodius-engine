package net.exodiusmc.example.entity.living;

import javafx.scene.input.KeyCode;
import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.animation.SpriteAnimation;
import net.exodiusmc.engine.enums.Direction;
import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.example.Main;
import net.exodiusmc.example.MovementKeys;
import net.exodiusmc.example.SwordDirection;
import net.exodiusmc.example.entity.EntityType;
import net.exodiusmc.example.entity.HeroType;
import net.exodiusmc.example.entity.LivingEntity;
import net.exodiusmc.example.layers.DeathLayer;

public class Hero extends LivingEntity {
	private SpriteAnimation sprite;
	private HeroType type;
	private MovementKeys keys;
	
	public Direction facing;
	public SwordDirection swordFacing;
	public double dmgTick = 0;
	public int attackTick = 0;
	
	public Hero(Location pos) {
		super(pos, EntityType.HERO);
		
		this.facing = Direction.DOWN;
		this.swordFacing = SwordDirection.DOWN;
		this.keys = MovementKeys.ARROWS;
		
		setMaxHealth(1000, true);
		setMovementSpeed(0.6);
	}
	
	public void setSprite(SpriteAnimation sprite) {
		this.sprite = sprite;
	}
	
	public SpriteAnimation getSprite() {
		return this.sprite;
	}
	
	public void death() {
		Main.engine.getLayerManager().add(new DeathLayer(Main.getMain().score));
	}
	
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
		
		if(Main.getInputMngr().isKeyPressed(KeyCode.UP)
		&& Main.getInputMngr().isKeyPressed(KeyCode.RIGHT)) {
			this.swordFacing = SwordDirection.UP_RIGHT;
		} else if(Main.getInputMngr().isKeyPressed(KeyCode.RIGHT)
		&& Main.getInputMngr().isKeyPressed(KeyCode.DOWN)) {
			this.swordFacing = SwordDirection.RIGHT_DOWN;
		} else if(Main.getInputMngr().isKeyPressed(KeyCode.DOWN)
		&& Main.getInputMngr().isKeyPressed(KeyCode.LEFT)) {
			this.swordFacing = SwordDirection.DOWN_LEFT;
		} else if(Main.getInputMngr().isKeyPressed(KeyCode.LEFT)
		&& Main.getInputMngr().isKeyPressed(KeyCode.UP)) {
			this.swordFacing = SwordDirection.LEFT_UP;
		} else {
			this.swordFacing = SwordDirection.valueOf(this.facing.toString());
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

	public Direction getFacing() {
		return facing;
	}
}
