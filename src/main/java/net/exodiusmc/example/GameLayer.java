package net.exodiusmc.example;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import net.exodiusmc.engine.layers.Layer;

public class GameLayer implements Layer {

	@Override
	public void update(GraphicsContext gfx) {
		gfx.setFill(Color.GREEN);
        gfx.fillRect(20, 20, 100, 100);
        gfx.getCanvas().setLayoutX(100);
	}

	@Override
	public boolean renderOnCover() {
		return true;
	}

}
