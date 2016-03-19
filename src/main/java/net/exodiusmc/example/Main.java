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
	    Main.window = window;
	    
	    window.setResizable(false);
	    
		window.setHeight(610);
		window.setWidth(620);
		window.setTitle("Monster hunt");
		window.getIcons().add(FileUtils.LoadImage("head_128.png"));
		window.getIcons().add(FileUtils.LoadImage("head_32.png"));
		window.getIcons().add(FileUtils.LoadImage("head_16.png"));
		window.show();
		
		
		GameLayer game = new GameLayer();
		
		getLayerManager().add(game);
	}

	@Override
	public void update(double delta) {
	    
	}
	
}
