package net.exodiusmc.example.layers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import net.exodiusmc.engine.layers.Layer;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.example.Main;

public class DeathLayer implements Layer {
	private double fade;
	private Image pane;
	private Image paneGreen;
	private Image paneRed;
	
	private byte paneType;
	
	public DeathLayer() {
		/*
		 * 159.0 319.0
	     * 240.0 357.0
		 * 255.0 319.0
		 * 293.0 357.0
		 */
		this.fade = 0;
		this.pane = FileUtils.LoadImage("menu.png");
		this.paneGreen = FileUtils.LoadImage("menu_green.png");
		this.paneRed = FileUtils.LoadImage("menu_red.png");
		
		this.paneType = 0;
		
		Main.window.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent evt) {
				double x = evt.getSceneX();
				double y = evt.getSceneY();
				
				if((159 < x && 319 < y) && (x < 240 && y < 357)) {
					paneType = 1;
				} else if((255 < x && 319 < y) && (x < 393 && y < 357)) {
					paneType = 2;
				} else {
					paneType = 0;
				}
			}
        });
		
		Main.window.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent evt) {
				double x = evt.getSceneX();
				double y = evt.getSceneY();
				
				if((159 < x && 319 < y) && (x < 240 && y < 357)) {
					// Retry
					Main.engine.getLayerManager().clear();
					Main.engine.getLayerManager().add(new GameLayer());
				} else if((255 < x && 319 < y) && (x < 393 && y < 357)) {
					// Quit
					System.exit(0);
				}
			}
        });
	}

	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		if(fade <= 1) {
			fade += 0.05;
		}
	}

	@Override
	public void render(GraphicsContext gfx) {
		gfx.setGlobalAlpha(this.fade);
		
		switch(this.paneType) {
		case 0:
			gfx.drawImage(this.pane, (Main.window.getWidth() - this.pane.getWidth()) / 2, (Main.window.getHeight() - this.pane.getHeight()) / 2);
			Main.window.getScene().setCursor(Cursor.DEFAULT);
			break;
		case 1:
			gfx.drawImage(this.paneGreen, (Main.window.getWidth() - this.pane.getWidth()) / 2, (Main.window.getHeight() - this.pane.getHeight()) / 2);
			Main.window.getScene().setCursor(Cursor.HAND);
			break;
		case 2:
			gfx.drawImage(this.paneRed, (Main.window.getWidth() - this.pane.getWidth()) / 2, (Main.window.getHeight() - this.pane.getHeight()) / 2);
			Main.window.getScene().setCursor(Cursor.HAND);
			break;
		}
		
		gfx.setGlobalAlpha(1);
	}

}
