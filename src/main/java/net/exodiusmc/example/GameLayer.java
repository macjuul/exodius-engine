package net.exodiusmc.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import net.exodiusmc.engine.layers.Layer;
import net.exodiusmc.engine.util.FileUtils;

public class GameLayer implements Layer {
    private Image ground;
    
    public GameLayer() {
        this.ground = FileUtils.LoadImage("ground.png");
    }
    
    @Override
    public void update(double delta, long frame) {
        
    }

	@Override
	public void render(GraphicsContext gfx) {
		gfx.drawImage(ground, 0, 0);
	}

	@Override
	public boolean renderOnCover() {
		return true;
	}

}
