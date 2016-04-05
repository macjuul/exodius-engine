package net.exodiusmc.example.layers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import net.exodiusmc.example.entity.HeroType;
import net.exodiusmc.example.entity.LivingEntity;
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
    private SpriteAnimation monsterSprite;
    private Rectangle playField;
    private InputManager input;
    private boolean updateSprite;
    private boolean updateMonsterSprite;
    private Font scoreFont;
    private int score;
    private int heartSize = 20;
    private float fade;
    
    public ArrayList<Entity> entities;
    
    public GameLayer() {
    	this.groundImg = FileUtils.LoadImage("ground.png");
    	this.heroImg = FileUtils.LoadImage("hero_anim.png");
        this.monsterImg = FileUtils.LoadImage("monster_anim.png");
        this.heartEmpty = FileUtils.LoadImage("heart_empty.png");
        this.heartFull = FileUtils.LoadImage("heart_full.png");
        this.heartExtra = FileUtils.LoadImage("heart_plus.png");
        this.trees = FileUtils.LoadImage("trees.png");
        this.playField = new Rectangle(new Location(50, 50), new Location(564, 525));
        this.input = Main.getInputMngr();
        this.heroSprite = new SpriteAnimation(heroImg, 5);
        this.monsterSprite = new SpriteAnimation(monsterImg, 5);
        this.updateSprite = false;
        this.monsterHead = FileUtils.LoadImage("head.png");
        this.scoreFont = (new Font("Arial", 25));
        this.entities = new ArrayList<Entity>();
        this.fade = 0;
        
        Location l = playField.getLocationRelative(0.5, 0.5);
        
        this.hero = new Hero(l);
        this.hero.setType(HeroType.RED, this.heroSprite);
        this.entities.add(this.hero);
        
        this.heroSprite.setSpriteOrder(new int[]{0, 1, 2, 3, 4, 3, 2, 1});
        this.monsterSprite.setSpriteOrder(new int[]{0, 1, 2, 3, 4, 3, 2, 1});
    }
    
    @Override
    public void update(double delta, long frame) {
    	if(this.hero.getHealth() > 0) {
	    	boolean r1 = handleMovement(new KeyCode[]{KeyCode.UP, KeyCode.W}, Direction.UP);
	    	boolean r2 = handleMovement(new KeyCode[]{KeyCode.RIGHT, KeyCode.D}, Direction.RIGHT);
	    	boolean r3 = handleMovement(new KeyCode[]{KeyCode.DOWN, KeyCode.S}, Direction.DOWN);
	    	boolean r4 = handleMovement(new KeyCode[]{KeyCode.LEFT, KeyCode.A}, Direction.LEFT);
	    	
	    	if(frame % 5 == 0 && (r1 || r2 || r3 || r4)) {
	    		this.updateSprite = true;
	    	} else {
	    		this.updateSprite = false;
	    	}
	    	
	    	if(frame % 5 == 0) {
	    		this.updateMonsterSprite = true;
	    	} else {
	    		this.updateMonsterSprite = false;
	    	}
	    	
	    	if(this.hero.facingCache != this.hero.facing) {
	    		this.hero.facingCache = this.hero.facing;
	    	}
	    	
	    	if(frame % 100 == 0) {
	    		this.entities.add(new Monster(this.playField));
	    	}

	    	if(frame % 90 == 0) {
	    		if(CoreUtils.randomIntInRange(0, 8) == 0) this.entities.add(new HeartPower(this.playField));
	    	}
	    	
	    	if(frame % 120 == 0) {
	    		if(CoreUtils.randomIntInRange(0, 20) == 0) this.entities.add(new HeartExtra(this.playField));
	    	}
	    	
    		sortEntities();
	    	
	    	Location heroLoc = this.hero.getLocation();
	    	
	    	Iterator<Entity> i = this.entities.iterator();
	    	
	    	while(i.hasNext()) {
	    		Entity m = i.next();
	    		
	    		if(m instanceof LivingEntity) {
	    			((LivingEntity) m).handleMovement(this.playField, this.entities);
	    		}
	    		
	    		switch(m.getEntityType()) {
				case EXTRA_HEART:
					if(m.getLocation().distance(heroLoc) < 30) {
	    				((HeartExtra) m).pickup(this.hero);
						i.remove();
	    			}
					break;
				case MONSTER:
					if(m.getLocation().distance(heroLoc) < 30 && this.hero.dmgTick == 0) {
						this.hero.damageHero(1);
		    			((LivingEntity) m).damage(1);
		    			i.remove();
					} else {
						((LivingEntity) m).moveTo(this.playField, this.hero.getLocation(), this.entities);
		    		}
					break;
				case MUMMY:
					break;
				case POWER_HEART:
					if(m.getLocation().distance(heroLoc) < 30 && this.hero.getMaxHealth() != this.hero.getHealth()) {
	    				((HeartPower) m).pickup(this.hero);
						i.remove();
	    			}
					break;
				case SKELETON:
					break;
				default:
					break;
	    		}
	    	}
	    	
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
		Image monSprite = monsterSprite.nextFrame(this.updateMonsterSprite);
		sprite = FileUtils.colorizeImage(sprite, Color.RED, this.hero.dmgTick);
		/* Gameplay */
		gfx.drawImage(groundImg, 0, 0, groundImg.getWidth() * 0.3, groundImg.getHeight()  * 0.3);
		
		for(Entity e : this.entities) {
			switch(e.getEntityType()) {
			case EXTRA_HEART:
				gfx.drawImage(this.heartExtra, e.getLocation().getX() - (this.heartFull.getWidth() / 2), e.getLocation().getY() - (this.heartFull.getHeight() / 2), this.heartExtra.getWidth() * 0.75, this.heartExtra.getHeight() * 0.75);
				break;
			case MONSTER:
				gfx.drawImage(monSprite, e.getLocation().getX() - (monSprite.getWidth() / 2), e.getLocation().getY() - (monSprite.getHeight() / 2), 30, 30);
				break;
			case MUMMY:
				break;
			case POWER_HEART:
				gfx.drawImage(this.heartFull, e.getLocation().getX() - (this.heartFull.getWidth() / 2), e.getLocation().getY() - (this.heartFull.getHeight() / 2), this.heartFull.getWidth() * 0.75, this.heartFull.getHeight() * 0.75);
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
	
	private boolean handleMovement(KeyCode[] kl, Direction d) {
		for(KeyCode k : kl) {
			if(input.isKeyPressed(k)) {
				this.hero.saveLocation();
	    		this.hero.move(d, this.playField);
	    		if(!this.playField.contains(this.hero.getLocation())) {
	        		hero.restoreLocation();
	        	}
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
	
	private void sortEntities() {
		Collections.sort(this.entities, new Comparator<Entity>() {
		    public int compare(Entity e1, Entity e2) {
		        return e1.getLocation().getY() > e2.getLocation().getY() ? 1 : -1;
		    }
		});
	}
}
