package net.exodiusmc.example_jetpack_jordy.layers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.animation.SpriteAnimation;
import net.exodiusmc.engine.layers.Layer;
import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.engine.util.RenderUtils;
import net.exodiusmc.example_jetpack_jordy.Cloud;
import net.exodiusmc.example_jetpack_jordy.Main;
import net.exodiusmc.example_jetpack_jordy.Rocket;
import net.exodiusmc.example_jetpack_jordy.Scene;

public class GameLayer implements Layer {
	private HashMap<Integer, Image> scene_sprites = new HashMap<Integer, Image>();
	private ArrayList<Scene> scenes = new ArrayList<Scene>();
	private ArrayList<Cloud> clouds = new ArrayList<Cloud>();
	private ArrayList<Rocket> rockets = new ArrayList<Rocket>();
	private double scene_width;
	private int scene_count;
	private float speed = 6;
	private boolean last_scene_special;
	private SpriteAnimation fire = new SpriteAnimation(FileUtils.LoadImage("fire.png"), 5, true);
	private Image rocket = FileUtils.LoadImage("rocket.png");
	private Image warning = FileUtils.LoadImage("warning.png");
	private Image[] cloud_images = new Image[] {
			FileUtils.LoadImage("/cloud/1.png"),
			FileUtils.LoadImage("/cloud/2.png"),
			FileUtils.LoadImage("/cloud/3.png"),
			FileUtils.LoadImage("/cloud/4.png"),
			FileUtils.LoadImage("/cloud/5.png"),
			FileUtils.LoadImage("/cloud/6.png")
	};
	
	private boolean render = true;
	
	private Rectangle rec1 = new Rectangle(new Location(100, 100), 100, 100);
	private Rectangle rec2 = new Rectangle(new Location(150, 150), 100, 100);
	
	private byte wink = 0;
	
	private Image player;
	private int player_pos = 200;
	private double player_motion = 0;
	private boolean player_on_ground = false;
	private Rectangle player_collision = new Rectangle(Location.constructZero(), 88, 150);
	private Image wink1;
	private Image wink2;
	private boolean updateFire = false;
	private boolean rising = false;
	private float fireFade = 0;
	
	private int[] weights = new int[] {
			1, 1, 1, 1,
			2, 2, 2, 2,
			3, 3, 3,
			4, 4, 4,
			5, 5, 5, 5,
			6, 6, 6, 6
	};
	
	public GameLayer() {
		for(int i = 1; i < 7; i++) {
			this.scene_sprites.put(i, FileUtils.LoadImage("/scene/" + i + ".png"));
		}
		
		this.scene_sprites.put(1, FileUtils.LoadImage("/cloud/1.png"));
		this.scene_sprites.put(2, FileUtils.LoadImage("/cloud/2.png"));
		this.scene_sprites.put(3, FileUtils.LoadImage("/cloud/3.png"));
		this.scene_sprites.put(4, FileUtils.LoadImage("/cloud/4.png"));
		this.scene_sprites.put(5, FileUtils.LoadImage("/cloud/5.png"));
		this.scene_sprites.put(6, FileUtils.LoadImage("/cloud/6.png"));
		
		player = FileUtils.LoadImage("jordy/regular.png");
		wink1 = FileUtils.LoadImage("jordy/head_wink_1.png");
		wink2 = FileUtils.LoadImage("jordy/head_wink_2.png");
		
		this.scene_width = 482;
		this.scene_count = (int) Math.ceil(Main.WIDTH / scene_width);
		
		for(int i = 0; i < scene_count; i++) {
			scenes.add(new Scene(1, scene_width * i));
		}
	}

	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		speed += 0.001f;
		
		if(frame % 4 == 0) {
			this.updateFire = true;
		} else {
			this.updateFire = false;
		}
		
		if(frame % 4 == 0 && wink > 0) wink++;
		
		if(wink > 6) wink = 0;
		
		if(frame % 300 == 0) wink = 1;
		
		Iterator<Scene> si = scenes.iterator();
		
		while(si.hasNext()) {
			Scene s = si.next();
			
			s.update(speed);
			
			if(s.getPositon() + scene_width < 0) {
				si.remove();
			}
		}
		
		Scene last = scenes.get(scenes.size() - 1);
		
		if(last.getPositon() + scene_width <= Main.WIDTH) {
			int type;
			if(last_scene_special) {
				type = 1;
				last_scene_special = false;
			} else {
				int idx = new Random().nextInt(weights.length);
				
				type = weights[idx];
				
				if(weights[idx] > 1) {
					this.last_scene_special = true;
				}
			}
			scenes.add(new Scene(type, last.getPositon() + scene_width));
		}
		
		if(Main.input.isKeyPressed(KeyCode.SPACE)) {
			if(player_on_ground) {
				player_motion = 0;
			}
			
			player_motion -= 0.7;
			rising = true;
			
			fireFade += 0.125;
			
			if(fireFade > 1) {
				fireFade = 1;
			}
		} else {
			if(!player_on_ground) {
				player_motion += 0.7;
				rising = false;
			}
			
			fireFade -= 0.125;
			
			if(fireFade < 0) {
				fireFade = 0;
			}
		}
		
		int old_pos = player_pos;
		
		player_pos += player_motion;
		
		if(player_pos > Main.HEIGHT - 150) {
			player_pos = old_pos;
			player_on_ground = true;
		} else {
			player_on_ground = false;
		}
		
		if(player_pos - (player.getHeight() / 2) < 0) {
			player_pos = old_pos;
			player_motion = 0;
		}
		
		player_collision.moveWithTopLeft(new Location(110, player_pos - 77));
		
		if(frame % 4 == 0 && rising) {
			Image img = (Image) CoreUtils.arrayRand(cloud_images);
			clouds.add(new Cloud(img, new Location(70, this.player_pos)));
		}
		
		if(frame % 10 == 0) { // 96 
			rockets.add(new Rocket(new Location(Main.WIDTH + 2000, player_pos)));
		}
		
		Iterator<Cloud> ci = clouds.iterator();
		
		while(ci.hasNext()) {
			Cloud c = ci.next();
			
			if(!c.tick(this.speed)) {
				ci.remove();
			}
			
			if(c.getPosition().getX() < -80) {
				try {
					ci.remove();
				} catch(IllegalStateException e) {}
			}
		}
		
		Iterator<Rocket> ri = rockets.iterator();
		
		while(ri.hasNext()) {
			Rocket r = ri.next();
			
			Rectangle hitbox = r.getHitbox();
			
			r.tick(this.speed);
			
			hitbox.moveWithTopLeft(r.getPosition().clone().add(-25, -23));
			
			if(hitbox.intersects(this.player_collision)) {
				System.exit(0);
			}
			
			if(r.getPosition().getX() < -80) {
				ri.remove();
			} else if(frame % 4 == 0) {
				Location p = r.getPosition();
				
				Image img = (Image) CoreUtils.arrayRand(cloud_images);
				clouds.add(new Cloud(img, p.clone().add(30, -34)));
			}
		}
	}

	@Override
	public void render(GraphicsContext gfx) {
		gfx.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		for(Scene s : scenes) {
			Image img = s.getTexture();
			double ratio = img.getWidth() / img.getHeight();
			gfx.drawImage(img, s.getPositon(), 0, Main.HEIGHT * ratio, Main.HEIGHT);
		}
		
		gfx.setGlobalAlpha(fireFade);
		gfx.drawImage(fire.nextFrame(this.updateFire), 107, player_pos - (player.getHeight() / 2) + 83);
		gfx.setGlobalAlpha(1);
		
		for(Cloud c : clouds) {
			Location l = c.getPosition();
			float fade = c.getFade();
			Image i = c.getImage();
			short r = c.getRotation();
			
			gfx.setGlobalAlpha(fade);
			RenderUtils.drawRotatedImage(gfx, i, r, l.getX(), l.getY(), i.getWidth() * 0.6, i.getHeight() * 0.6);
			gfx.setGlobalAlpha(1);
		}
		
		gfx.drawImage(player, 100, player_pos - (player.getHeight() / 2));
		
		if(wink == 1 || wink == 2 || wink == 5 || wink == 6) {
			gfx.drawImage(wink1, 100, player_pos - (player.getHeight() / 2));
		} else if(wink == 3 || wink == 4) {
			gfx.drawImage(wink2, 100, player_pos - (player.getHeight() / 2));
		}
		
		gfx.setStroke(Color.RED);
		gfx.setLineWidth(2);
		
		player_collision.drawStroke(gfx);
		
		gfx.setStroke(Color.GREEN);
		
		rec1.drawStroke(gfx);
		
		gfx.setStroke(Color.BLUE);
		rec2.drawStroke(gfx);
		
		for(Rocket r : rockets) {
			Location l = r.getPosition();
			boolean warn = r.shouldWarn();
			
	        r.getHitbox().drawStroke(gfx);
			
			if(warn) {
				gfx.drawImage(warning, Main.WIDTH - 60, l.getY() - (rocket.getHeight() / 2) + 10);
			} else {
				RenderUtils.drawRotatedImage(gfx, fire.nextFrame(false), -90, l.getX() - (rocket.getWidth() / 2) + 100, l.getY() - (rocket.getHeight() / 2) + 5);
				gfx.drawImage(rocket, l.getX() - (rocket.getWidth() / 2), l.getY() - (rocket.getHeight() / 2), rocket.getWidth() * 0.85, rocket.getHeight() * 0.85);
			}
		}
	}

	@Override
	public void dispose() {
		
	}

}
