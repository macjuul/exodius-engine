package net.exodiusmc.example_monster_hunt.layers;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import net.exodiusmc.engine.layers.Layer;
import net.exodiusmc.engine.util.FileUtils;
import net.exodiusmc.example_monster_hunt.Main;

@Deprecated
public class MainMenuLayer implements Layer {
	private double opacity;
	private Image logo;
	private int height = 0;
	private boolean heightState = true;
	private byte hoverState = 0;
	
	private Image orangeBtn;
	private Image blueBtn;
	private Image orangeBtnL;
	private Image blueBtnL;
	
	private EventHandler<MouseEvent> mouseMoveEvent;
	private EventHandler<MouseEvent> mouseClickEvent;

	public MainMenuLayer() {
		this.opacity = 0.675;
		this.logo = FileUtils.LoadImage("logo.png");
		
		Image buttons = FileUtils.LoadImage("buttons.png");
		Image buttonsL = FileUtils.LoadImage("buttons_light.png");
		
		this.blueBtn = FileUtils.getSubImage(buttons, 0, 53 * 2, 88 * 2, 43 * 2);
		this.orangeBtn = FileUtils.getSubImage(buttons, 0, 106 * 2, 88 * 2, 43 * 2);
		this.blueBtnL = FileUtils.getSubImage(buttonsL, 0, 53 * 2, 88 * 2, 43 * 2);
		this.orangeBtnL = FileUtils.getSubImage(buttonsL, 0, 106 * 2, 88 * 2, 43 * 2);
		
		/*
		 *  108.0 307.0
		 *	266.0 378.0
		 *  352.0 307.0
		 *  510.0 378.0
		 */
		
		this.mouseMoveEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent evt) {
				double x = evt.getSceneX();
				double y = evt.getSceneY();
				
				if((108 < x && 307 + 50 < y) && (x < 266 && y < 378 + 50)) {
					hoverState = 1;
				} else if((352 < x && 307 + 50 < y) && (x < 510 && y < 378 + 50)) {
					hoverState = 2;
				} else {
					hoverState = 0;
				}
			}
        };
		
		this.mouseClickEvent = new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent evt) {
				double x = evt.getSceneX();
				double y = evt.getSceneY();
				
				if((108 < x && 307 + 50 < y) && (x < 266 && y < 378 + 50)) {
					// Start
					Main.engine.getLayerManager().pop();
				} else if((352 < x && 307 + 50 < y) && (x < 510 && y < 378 + 50)) {
					// Settings
				}
			}
		};
		
		Main.window.addEventHandler(MouseEvent.MOUSE_MOVED, this.mouseMoveEvent);
		Main.window.addEventHandler(MouseEvent.MOUSE_PRESSED, this.mouseClickEvent);
	}

	@Override
	public boolean updateOnCover() {
		return false;
	}

	@Override
	public void update(double delta, long frame) {
		if(frame % 2 == 0) {
			if(this.heightState) {
				this.height++;
				
				if(this.height > 20) {
					this.heightState = false;
				}
			} else {
				this.height--;
				
				if(this.height < 0) {
					this.heightState = true;
				}
			}
		}
	}

	@Override
	public void render(GraphicsContext gfx) {
		gfx.setGlobalAlpha(this.opacity);
		gfx.setFill(Color.BLACK);
		gfx.fillRect(0, 0, Main.window.getWidth(), Main.window.getHeight());
		gfx.setGlobalAlpha(1);
		
		gfx.drawImage(this.logo, (Main.window.getWidth() / 2) - (this.logo.getWidth() / 2), 30 + this.height);
		
		gfx.drawImage(this.hoverState == 1 ? this.orangeBtnL : this.orangeBtn, 100, 350);
		gfx.drawImage(this.hoverState == 2 ? this.blueBtnL : this.blueBtn, Main.window.getWidth() - 100 - this.blueBtn.getWidth(), 350);
	}

	@Override
	public void dispose() {
		Main.window.removeEventHandler(MouseEvent.MOUSE_MOVED, this.mouseMoveEvent);
		Main.window.removeEventHandler(MouseEvent.MOUSE_PRESSED, this.mouseClickEvent);
		
		Main.window.getScene().setCursor(Cursor.DEFAULT);
	}

}
