package net.exodiusmc.engine.layers;

import javafx.scene.canvas.GraphicsContext;

public interface Layer {
	public boolean renderOnCover();
	
	public void update(GraphicsContext gfx);
}
