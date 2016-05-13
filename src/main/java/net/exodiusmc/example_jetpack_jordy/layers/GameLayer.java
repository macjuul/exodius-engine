package net.exodiusmc.example_jetpack_jordy.layers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import net.exodiusmc.engine.animation.SpriteAnimation;
import net.exodiusmc.engine.layers.Layer;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.example_jetpack_jordy.Main;
import net.exodiusmc.example_jetpack_jordy.Scene;

public class GameLayer implements Layer {
	private HashMap<Integer, Image> scene_sprites = new HashMap<Integer, Image>();
	private HashMap<Integer, Image> cloud_sprites = new HashMap<Integer, Image>();
	private ArrayList<Scene> scenes = new ArrayList<Scene>();
	private double scene_width;
	private int scene_count;
	private float speed = 6;
	private boolean last_scene_special;
	private SpriteAnimation fire = new SpriteAnimation(FileUtils.LoadImage("fire.png"), 5, true);
	
	private byte wink = 0;
	
	private Image player;
	private int player_pos = 200;
	private double player_motion = 0;
	private boolean player_on_ground = false;
	private Image wink1;
	private Image wink2;
	
	private int[] weights = new int[] {
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
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
		
		for(int i = 1; i < 7; i++) {
			this.scene_sprites.put(i, FileUtils.LoadImage("/cloud/" + i + ".png"));
		}
		
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
		speed += 0.003f;
		
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
		} else if(!player_on_ground){
			player_motion += 0.7;
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
		
	}

	@Override
	public void render(GraphicsContext gfx) {
		gfx.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		
		for(Scene s : scenes) {
			Image img = s.getTexture();
			double ratio = img.getWidth() / img.getHeight();
			gfx.drawImage(img, s.getPositon(), 0, Main.HEIGHT * ratio, Main.HEIGHT);
		}
		
		gfx.drawImage(fire.nextFrame(), 107, player_pos - (player.getHeight() / 2) + 83);
		
		gfx.drawImage(player, 100, player_pos - (player.getHeight() / 2));
		
		if(wink == 1 || wink == 2 || wink == 5 || wink == 6) {
			gfx.drawImage(wink1, 100, player_pos - (player.getHeight() / 2));
		} else if(wink == 3 || wink == 4) {
			gfx.drawImage(wink2, 100, player_pos - (player.getHeight() / 2));
		}
		
		gfx.drawImage(cloud_sprites.get(0), 200, 200);
	}

	@Override
	public void dispose() {
		
	}

}
