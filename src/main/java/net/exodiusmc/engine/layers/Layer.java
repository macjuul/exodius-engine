package net.exodiusmc.engine.layers;

import javafx.scene.canvas.GraphicsContext;

public interface Layer {
	public boolean updateOnCover();
	
	public void update(double delta, long frame);
	
	public void render(GraphicsContext gfx);
}
