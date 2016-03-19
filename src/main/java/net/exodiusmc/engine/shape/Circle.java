package net.exodiusmc.engine.shape;

import net.exodiusmc.engine.Location;

public class Circle {
	private Location center;
	private double radius;
	
	public Circle() {
	    this(new Location());
	}
	
	public Circle(Location center) {
	    this.center = center;
	}

    public Location getCenter() {
        return center;
    }

    public void setCenter(Location center) {
        this.center = center;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}
