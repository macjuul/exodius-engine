package net.exodiusmc.example;

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
import net.exodiusmc.engine.util.FileUtils;

public class GameLayer implements Layer {
	private Image groundImg;
    private Image heroImg;
    private Image monsterHead;
    private Hero hero;
    private SpriteAnimation heroSprite;
    private Rectangle playField;
    private InputManager input;
    private boolean updateSprite;
    private Font scoreFont;
    private int score;
    private Location center;
    
    public GameLayer() {
    	this.groundImg = FileUtils.LoadImage("ground.png");
        this.heroImg = FileUtils.LoadImage("hero_anim.png");
        this.playField = new Rectangle(new Location(50, 125), new Location(564, 590));
        this.input = Main.getInputMngr();
        this.heroSprite = new SpriteAnimation(heroImg, 5);
        this.updateSprite = false;
        this.monsterHead = FileUtils.LoadImage("head.png");
        this.scoreFont = (new Font("Arial", 25));
        
        Location l = playField.getLocationRelative(0.5, 0.65);
		l.setX(l.getX() + 50);
		l.setY(l.getY() + 50);
        
        this.hero = new Hero(l);
        
        this.center = l.clone();
        
        this.heroSprite.setSpriteOrder(new int[]{0, 1, 2, 3, 4, 3, 2, 1});
    }
    
    @Override
    public void update(double delta, long frame) {
    	boolean r1 = handleMovement(new KeyCode[]{KeyCode.UP, KeyCode.W}, Direction.UP, delta);
    	boolean r2 = handleMovement(new KeyCode[]{KeyCode.RIGHT, KeyCode.D}, Direction.RIGHT, delta);
    	boolean r3 = handleMovement(new KeyCode[]{KeyCode.DOWN, KeyCode.S}, Direction.DOWN, delta);
    	boolean r4 = handleMovement(new KeyCode[]{KeyCode.LEFT, KeyCode.A}, Direction.LEFT, delta);
    	
    	this.hero.handleMovement(delta, this.playField);
    	
    	if(frame % 7 == 0 && (r1 || r2 || r3 || r4)) {
    		this.updateSprite = true;
    	} else {
    		this.updateSprite = false;
    	}
    }

	@Override
	public void render(GraphicsContext gfx) {
		Image sprite = heroSprite.nextFrame(this.updateSprite);
		sprite = FileUtils.colorizeImage(sprite);
		gfx.drawImage(groundImg, 0, 0, groundImg.getWidth() * 0.3, groundImg.getHeight()  * 0.3);
		gfx.drawImage(sprite, this.hero.getLocation().getX() - (heroImg.getWidth() / 2), this.hero.getLocation().getY() - (heroImg.getHeight() / 2), 30, 30);
		gfx.drawImage(this.monsterHead, 38, 38, 128 / 3, 128 / 3);
		gfx.setFill(Color.YELLOW);
		gfx.setFont(this.scoreFont);
		gfx.fillText(String.valueOf(score), 88, 69);
		gfx.setStroke(Color.YELLOW);
		gfx.strokeText(String.valueOf(score), 88, 69);
	}

	@Override
	public boolean renderOnCover() {
		return true;
	}
	
	private boolean handleMovement(KeyCode[] kl, Direction d, double dt) {
		for(KeyCode k : kl) {
			if(input.isKeyPressed(k)) {
	    		this.hero.move(d, dt);
	    		if(!this.playField.contains(this.hero.getLocation())) {
	        		hero.undoMovement();
	        	}
	    		return true;
	    	}
		}
		return false;
	}
}
