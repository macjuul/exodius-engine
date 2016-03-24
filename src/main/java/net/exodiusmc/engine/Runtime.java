package net.exodiusmc.engine;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.engine.layers.Layer;

public class Runtime extends AnimationTimer {
	private long lastTime = System.nanoTime();
	private double ticks = 60D;  
	private double ns = 1000000000 / ticks;    
	private double delta = 0;
	private ExodiusEngine engine;
	private GraphicsContext gfx;
	private long frame;
	
	public Runtime(ExodiusEngine engine) {
		this.engine = engine;
		this.gfx = engine.getGraphics();
	}

	@Override
	public void handle(long now) {
	    delta += (now - lastTime) / ns;
	    lastTime = now;
	    if(delta >= 1){
	        frame++;
	        
	        engine.update(delta);
	        int stackSize = this.engine.getLayerManager().size();
	        
	        for(int c = 0; c < stackSize; c++) {
	        	Layer l = this.engine.getLayerManager().get(c);
	        	if(stackSize - 1 != c && !l.renderOnCover()) {
	        		continue;
	        	}
	        	l.update(this.delta, this.frame);
	        	l.render(this.gfx);
	        }
	        
	        delta--;
	    }
	}
	
	public long getCurrentFrame() {
		return this.frame;
	}
	
	public void setTargetFPS(double fps) {
		this.ns = 1000000000 / fps;  
		this.delta = 0;
	}
}
