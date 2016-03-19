package net.exodiusmc.engine;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.exodiusmc.engine.layers.LayerManager;

public abstract class ExodiusEngine extends Application {
	private int width = 700;
	private int height = 550;
	private GraphicsContext gfx;
	private Runtime run;
	private Stage window;
	private LayerManager layerManager;
	private InputManager inputManager;

	public abstract void init(Stage window, Runtime run);
	
	public abstract void update(double delta);

	@Override
	public void start(Stage stage) throws Exception {
		this.window = stage;
		this.layerManager = new LayerManager();
		
		StackPane p = new StackPane();
		Canvas cvs = new Canvas(this.width, this.height);
		p.getChildren().add(cvs);
		Scene s = new Scene(p, this.width, this.height);
		
		this.gfx = cvs.getGraphicsContext2D();
		
		this.window.setScene(s);
		this.window.setWidth(this.width);
		this.window.setHeight(this.height);
		
		window.widthProperty().addListener(new ChangeListener<Number>() {
		    @Override
			public void changed(ObservableValue<? extends Number> ov, Number widthOld, Number widthNew) {
				window.setWidth(widthNew.doubleValue());
				getGraphics().getCanvas().setWidth(widthNew.doubleValue());
			}
		});
		
		window.heightProperty().addListener(new ChangeListener<Number>() {
		    @Override
			public void changed(ObservableValue<? extends Number> ov, Number heightOld, Number heightNew) {
				window.setHeight(heightNew.doubleValue());
				getGraphics().getCanvas().setHeight(heightNew.doubleValue());
			}
		});
		
		this.run = new Runtime(this);
		run.start();
		init(this.window, this.run);
		
		if(window.isResizable()) {
		    p.setPadding(new Insets(43, 0, 0, 16));
		} else {
		    p.setPadding(new Insets(33, 0, 0, 6));
		}
		
		window.show();
	}
	
	public GraphicsContext getGraphics() {
		return this.gfx;
	}
	
	public Runtime getRuntime() {
		return this.run;
	}
	
	public LayerManager getLayerManager() {
	    return this.layerManager;
	}
	
	public InputManager getInputManager() {
		return this.inputManager;
	}
}
