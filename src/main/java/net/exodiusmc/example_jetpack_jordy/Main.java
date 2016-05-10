package net.exodiusmc.example_jetpack_jordy;

import javafx.stage.Stage;
import net.exodiusmc.engine.ExodiusEngine;
import net.exodiusmc.engine.Runtime;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.example_jetpack_jordy.layers.GameLayer;

public class Main extends ExodiusEngine {
	public static int WIDTH = 1000;
	public static int HEIGHT = 650;

	@Override
	public void init(Stage window, Runtime run) {
		FileUtils.setResourceDirectory("net/exodiusmc/example_jetpack_jordy");
		
		window.setTitle("Jetpack Jordy");
		window.setResizable(false);
		window.setHeight(HEIGHT);
		window.setWidth(WIDTH);
		
		getLayerManager().add(new GameLayer());
	}

	@Override
	public void update(double delta) {
		
	}

}
