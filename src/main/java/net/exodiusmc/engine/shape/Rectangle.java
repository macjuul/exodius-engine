package net.exodiusmc.engine.shape;

import javafx.scene.canvas.GraphicsContext;
import net.exodiusmc.engine.Location;

public class Rectangle {
	private Location min;
	private Location max;
	private double width;
	private double height;
	
	public Rectangle(Location min, Location max) {
		this.min = min;
		this.max = max;
		this.width = max.getX() - min.getX();
		this.height = max.getY() - min.getY();
	}
	
	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	public Location getTopLeft() {
		return min;
	}
	
	public Location getTopRight() {
		Location loc = min.clone();
		loc.setX(this.max.getX());
		return loc;
	}
	
	public Location getBottomLeft() {
		Location loc = max.clone();
		loc.setX(this.min.getX());
		return loc;
	}
	
	public Location getBottomRight() {
		return min;
	}
	
	public boolean contains(Location loc) {
		return loc.getX() >= this.min.getX()
			&& loc.getX() <= this.max.getX()
			&& loc.getY() >= this.min.getY()
			&& loc.getY() <= this.max.getY();
	}
	
	public void expand(double a) {
		this.min.setX(this.min.getX() - a);
		this.min.setY(this.min.getY() - a);
		this.max.setX(this.max.getX() + a);
		this.max.setY(this.max.getY() + a);
	}
	
	public void shrink(double a) {
		this.min.setX(this.min.getX() + a);
		this.min.setY(this.min.getY() + a);
		this.max.setX(this.max.getX() - a);
		this.max.setY(this.max.getY() - a);
	}
	
	public void rotate() {
		double height = this.height;
		double width = this.width;
		this.max.setX(this.min.getX() + height);
		this.max.setY(this.min.getY() + width);
	}
	
	public void drawFill(GraphicsContext gfx) {
		gfx.fillRect(this.min.getX(), this.min.getY(), this.width, this.height);
	}
	
	public void drawFill(GraphicsContext gfx, int curve) {
		gfx.fillRoundRect(this.min.getX(), this.min.getY(), this.width, this.height, curve, curve);
	}
	
	public void strokeFill(GraphicsContext gfx) {
		gfx.strokeRect(this.min.getX(), this.min.getY(), this.width, this.height);
	}
}
