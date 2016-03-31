package net.exodiusmc.example.entity;

import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;

public class Heart {
	private Location pos;
	
	public Heart(Rectangle playField) {
		Location loc = playField.getLocationRelative(CoreUtils.randomDoubleInRange(0, 1), CoreUtils.randomDoubleInRange(0, 1));
		
		loc.setX(loc.getX() + 50);
		loc.setY(loc.getY() + 50);
		
		this.pos = loc;
	}

	public Location getLocation() {
		return pos;
	}
}
