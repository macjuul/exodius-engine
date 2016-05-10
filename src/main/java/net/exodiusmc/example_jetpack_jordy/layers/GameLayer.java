package net.exodiusmc.example_jetpack_jordy.layers;

import java.util.HashMap;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import net.exodiusmc.engine.layers.Layer;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.example_jetpack_jordy.Main;

public class GameLayer implements Layer {
	private HashMap<Integer, Image> scene_sprites = new HashMap<Integer, Image>();
	private int[] weights = new int[] {
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
			2, 2, 2, 2,
			3, 3, 3,
			4, 4, 4,
			5, 5, 5, 5,
			6, 6, 6, 6
	};
	
	public GameLayer() {
		for(int i = 1; i < 7; i++) {
			scene_sprites.put(i, FileUtils.LoadImage("/scene/" + i + ".png"));
		}
	}

	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		
	}

	@Override
	public void render(GraphicsContext gfx) {
		gfx.drawImage(getSceneImg(1), 0, 0, 400, Main.HEIGHT);
	}

	@Override
	public void dispose() {
		
	}
	
	private Image getSceneImg(int id) {
		return this.scene_sprites.get(id);
	}

}
