package net.exodiusmc.example_jetpack_jordy;

import javafx.scene.image.Image;
import net.exodiusmc.engine.util.FileUtils;

public class Scene {
	private Image texture;
	private float x;
	
	public Scene(int id) {
		this.texture = FileUtils.LoadImage("/scenes/" + id + ".png");
	}
	
	public Image getTexture() {
		return texture;
	}
	
	public void update(double modif) {
		this.x += modif;
	}
	
}
