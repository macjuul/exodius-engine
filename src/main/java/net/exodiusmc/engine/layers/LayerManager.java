package net.exodiusmc.engine.layers;

import java.util.ArrayList;

public class LayerManager {
	private ArrayList<Layer> layerStack;
	
	/*
	 * Construct a new Layer Manager.
	 */
	public LayerManager() {
		layerStack = new ArrayList<Layer>();
	}
	
	/*
	 * Get the size of the layer stack
	 * 
	 * @return int Size
	 */
	public int size() {
		return layerStack.size();
	}
	
	/*
	 * Add a new layer to the layer stack. This layer will be added "on top" of the other layers
	 * 
	 * @param l Layer
	 * @return Layer
	 */
	public Layer add(Layer l) {
		layerStack.add(l);
		return l;
	}
	
	public Layer get(int index) {
		return layerStack.get(index);
	}
	
	/*
	 * Pop the top most layer from the layer stack. Returns the popped layer
	 * 
	 * @return Layer
	 */
	public Layer pop() {
		Layer popped = this.layerStack.get(this.layerStack.size() - 1);
		this.layerStack.remove(this.layerStack.size() - 1);
		return popped;
	}
}
