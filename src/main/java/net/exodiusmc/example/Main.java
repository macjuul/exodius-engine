package net.exodiusmc.example;

import javafx.stage.Stage;
import net.exodiusmc.engine.ExodiusEngine;
import net.exodiusmc.engine.Runtime;
import net.exodiusmc.engine.util.FileUtils;

public class Main extends ExodiusEngine {
    public static Stage window;
    
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void init(Stage window, Runtime run) {
	    FileUtils.setResourceDirectory("net/exodiusmc/example");
	    this.window = window;
	    
		window.setHeight(523);
		window.setWidth(528);
		window.setTitle("Monster hunt");
		window.show();
		
		
		GameLayer game = new GameLayer();
		
		getLayerManager().add(game);
	}

	@Override
	public void update(double delta) {
	    
	}
	
}
