package net.exodiusmc.example;

import javafx.stage.Stage;
import net.exodiusmc.engine.ExodiusEngine;
import net.exodiusmc.engine.Runtime;

public class Main extends ExodiusEngine {

	@Override
	public void init(Stage window, Runtime run) {
		window.setHeight(300);
		window.setWidth(400);
		window.setTitle("Sample game");
		window.show();
		
		GameLayer game = new GameLayer();
		
		getLayerManager().add(game);
	}

	@Override
	public void update(double delta) {}
	
}
