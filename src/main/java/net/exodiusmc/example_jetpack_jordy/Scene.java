package net.exodiusmc.example_jetpack_jordy;

import javafx.scene.image.Image;
import net.exodiusmc.engine.util.FileUtils;

public class Scene {
	private Image texture;
	private float  x;
	
	public Scene(int id, double x) {
		this.texture = FileUtils.LoadImage("/scene/" + id + ".png");
		
		this.x = (float) x;
	}
	
	public Image getTexture() {
		return texture;
	}
	
	public float getPositon() {
		return this.x;
	}
	
	public void update(double modif) {
		this.x -= modif;
	}
	
}
