package net.exodiusmc.example.layers;

import java.util.ArrayList;
import java.util.Iterator;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import net.exodiusmc.engine.InputManager;
import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.animation.SpriteAnimation;
import net.exodiusmc.engine.enums.Direction;
import net.exodiusmc.engine.layers.Layer;
import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.example.Main;
import net.exodiusmc.example.entity.Entity;
import net.exodiusmc.example.entity.living.Hero;
import net.exodiusmc.example.entity.living.Monster;
import net.exodiusmc.example.entity.powerup.HeartExtra;
import net.exodiusmc.example.entity.powerup.HeartPower;

public class GameLayer implements Layer {
	private Image groundImg;
    private Image heroImg;
    private Image monsterHead;
    private Image monsterImg;
    private Image heartFull;
    private Image heartEmpty;
    private Image heartExtra;
    private Image trees;
    private Hero hero;
    private SpriteAnimation heroSprite;
    private Rectangle playField;
    private InputManager input;
    private boolean updateSprite;
    private Font scoreFont;
    private int score;
    private int heartSize = 20;
    private float fade;
    
    public ArrayList<Entity> entities;
    
    public GameLayer() {
    	this.groundImg = FileUtils.LoadImage("ground.png");
    	this.heroImg = FileUtils.LoadImage("hero_anim.png");
        this.monsterImg = FileUtils.LoadImage("monster.png");
        this.heartEmpty = FileUtils.LoadImage("heart_empty.png");
        this.heartFull = FileUtils.LoadImage("heart_full.png");
        this.heartExtra = FileUtils.LoadImage("heart_plus.png");
        this.trees = FileUtils.LoadImage("trees.png");
        this.playField = new Rectangle(new Location(50, 50), new Location(564, 525));
        this.input = Main.getInputMngr();
        this.heroSprite = new SpriteAnimation(heroImg, 5);
        this.updateSprite = false;
        this.monsterHead = FileUtils.LoadImage("head.png");
        this.scoreFont = (new Font("Arial", 25));
        this.entities = new ArrayList<Entity>();
        this.fade = 0;
        
        Location l = playField.getLocationRelative(0.59, 0.59);
        
        this.hero = new Hero(l);
        this.heroSprite.setSpriteOrder(new int[]{0, 1, 2, 3, 4, 3, 2, 1});
    }
    
    @Override
    public void update(double delta, long frame) {
    	if(this.hero.getHealth() > 0) {
	    	boolean r1 = handleMovement(new KeyCode[]{KeyCode.UP, KeyCode.W}, Direction.UP, delta);
	    	boolean r2 = handleMovement(new KeyCode[]{KeyCode.RIGHT, KeyCode.D}, Direction.RIGHT, delta);
	    	boolean r3 = handleMovement(new KeyCode[]{KeyCode.DOWN, KeyCode.S}, Direction.DOWN, delta);
	    	boolean r4 = handleMovement(new KeyCode[]{KeyCode.LEFT, KeyCode.A}, Direction.LEFT, delta);
	    	
	    	if(frame % 5 == 0 && (r1 || r2 || r3 || r4)) {
	    		this.updateSprite = true;
	    	} else {
	    		this.updateSprite = false;
	    	}
	    	
	    	if(this.hero.facingCache != this.hero.facing) {
	    		this.hero.facingCache = this.hero.facing;
	    	}
	    	
	    	if(frame % 50 == 0) {
	    		this.entities.add(new Monster(this.playField));
	    	}
	    	
	    	if(frame % 90 == 0) {
	    		if(CoreUtils.randomIntInRange(0, 8) == 0) this.entities.add(new HeartPower(this.playField));
	    	}
	    	
	    	if(frame % 120 == 0) {
	    		if(CoreUtils.randomIntInRange(0, 15) == 0) this.entities.add(new HeartExtra(this.playField));
	    	}
	    	
	    	Location heroLoc = this.hero.getLocation();
	    	
	    	Iterator<Entity> i = this.entities.iterator();
	    	
	    	while(i.hasNext()) {
	    		Entity m = i.next();
//	    		if(m.getLocation().distance(heroLoc) < 30 && this.hero.dmgTick == 0) {
//	    			this.hero.damage();
//	    			m.kill();
//	    			i.remove();
//	    		} else {
//	    			m.move(Direction.CUSTOM, 0.4, this.hero.getLocation());
//	    			m.handleMovement(delta, this.playField, false);
//	    		}
	    		switch(m.getEntityType()) {
				case EXTRA_HEART:
					break;
				case HERO:
					break;
				case MONSTER:
					break;
				case MUMMY:
					break;
				case POWER_HEART:
					break;
				case SKELETON:
					break;
				default:
					break;
	    		}
	    	}
	    	
//	    	Iterator<Heart> i2 = hearts.iterator();
//	    	
//	    	while(i2.hasNext()) {
//	    		Heart h = i2.next();
//	    		if(h.getLocation().distance(heroLoc) < 30) {
//	    			if(h instanceof HeartPower && this.hero.getMaxHealth() != this.hero.getHealth()) {
//	    				HeartPower heart1 = (HeartPower) h;
//	    				heart1.pickup(this.hero);
//						i2.remove();
//	    			} else if(h instanceof HeartExtra) {
//	    				HeartExtra heart2 = (HeartExtra) h;
//						heart2.pickup(this.hero);
//						i2.remove();
//	    			}
//	    		}
//	    	}
	    	
	    	if(this.hero.damageTick > 0) {
	    		this.hero.damageTick -= 0.1;
	    	} else {
	    		this.hero.damageTick = 0;
	    	}
	    	
	    	if(this.hero.dmgTick > 0) {
	    		this.hero.dmgTick -= 0.1;
	    	} else {
	    		this.hero.dmgTick = 0;
	    	};
    	} else if(this.fade <= 0.4) {
    		this.fade += 0.03;
    	}
    }

	@Override
	public void render(GraphicsContext gfx) {
		Image sprite = heroSprite.nextFrame(this.updateSprite);
		sprite = FileUtils.colorizeImage(sprite, Color.RED, this.hero.damageTick);
		/* Gameplay */
		gfx.drawImage(groundImg, 0, 0, groundImg.getWidth() * 0.3, groundImg.getHeight()  * 0.3);
		
//		for(Heart h : hearts) {
//			if(h instanceof HeartPower && this.hero.getMaxHealth() != this.hero.getHealth()) {
//				gfx.drawImage(this.heartFull, h.getLocation().getX() - (this.heartFull.getWidth() / 2), h.getLocation().getY() - (this.heartFull.getHeight() / 2), this.heartFull.getWidth() * 0.75, this.heartFull.getHeight() * 0.75);
//			} else if(h instanceof HeartExtra) {
//				gfx.drawImage(this.heartExtra, h.getLocation().getX() - (this.heartFull.getWidth() / 2), h.getLocation().getY() - (this.heartFull.getHeight() / 2), this.heartExtra.getWidth() * 0.75, this.heartExtra.getHeight() * 0.75);
//			}
//		}
//		
//		for(Monster m : monsters) {
//			gfx.drawImage(this.monsterImg, m.getLocation().getX() - (monsterImg.getWidth() / 2), m.getLocation().getY() - (monsterImg.getHeight() / 2), 30, 30);
//		}
		
		for(Entity e : this.entities) {
			switch(e.getEntityType()) {
			case EXTRA_HEART:
				break;
			case HERO:
				break;
			case MONSTER:
				break;
			case MUMMY:
				break;
			case POWER_HEART:
				break;
			case SKELETON:
				break;
			default:
				break;
			}
		}
		
		gfx.drawImage(sprite, this.hero.getLocation().getX() - (heroImg.getWidth() / 2), this.hero.getLocation().getY() - (sprite.getHeight() / 2), 30, 30);

		/* HUD */
		gfx.drawImage(this.monsterHead, 38, 68, 38, 38);
		gfx.setFill(Color.YELLOW);
		gfx.setFont(this.scoreFont);
		gfx.fillText(String.valueOf(score), 83, 95);
		gfx.setStroke(Color.YELLOW);
		gfx.strokeText(String.valueOf(score), 83, 95);
		
		for(int h = 0; h < this.hero.getHealth(); h++) {
			gfx.drawImage(this.heartFull, 42 + ((this.heartSize + 2) * h), 45, this.heartSize, this.heartSize);
		}
		
		for(int h = this.hero.getHealth(); h < this.hero.getMaxHealth(); h++) {
			gfx.drawImage(this.heartEmpty, 42 + ((this.heartSize + 2) * h), 45, this.heartSize, this.heartSize);
		}
		
		/* Final */
		gfx.drawImage(trees, 0, 0, groundImg.getWidth() * 0.3, groundImg.getHeight()  * 0.3);
		
		if(this.fade > 0) {
			gfx.setGlobalAlpha(this.fade);
			gfx.setFill(Color.BLACK);
			gfx.fillRect(0, 0, Main.window.getWidth(), Main.window.getHeight());
			gfx.setGlobalAlpha(1);
		}
	}

	@Override
	public boolean updateOnCover() {
		return true;
	}
	
	private boolean handleMovement(KeyCode[] kl, Direction d, double dt) {
		for(KeyCode k : kl) {
			if(input.isKeyPressed(k)) {
//	    		this.hero.move(d, dt);
//	    		if(!this.playField.contains(this.hero.getLocation())) {
//	        		hero.undoMovement();
//	        	}
	    		return true;
	    	}
		}
		return false;
	}
	
	public void score() {
		this.score++;
	}

	@Override
	public void dispose() {}
}
