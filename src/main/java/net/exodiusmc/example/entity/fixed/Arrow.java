package net.exodiusmc.example.entity.fixed;

import net.exodiusmc.engine.Location;
import net.exodiusmc.example.entity.EntityType;
import net.exodiusmc.example.entity.StaticEntity;
import net.exodiusmc.example.entity.living.Hero;

public class Arrow extends StaticEntity {
	private double angle;
	
	public Arrow(Location pos, EntityType type, Location target) {
		super(pos, type);
		
		this.angle = pos.getAngle(target);
	}

	@Override
	public void pickup(Hero h) {
		h.damageHero(1);
	}
	
	public void move() {
		double x = this.getLocation().getX();
		double y = this.getLocation().getY();
		double rad = ((this.angle - 90) / 180) * Math.PI;
		
	    x += Math.cos(rad) * 4;
	    y += Math.sin(rad) * 4;
	    
	    this.setLocation(new Location(x, y));
	}
	
	public double getAngle() {
		return this.angle;
	}

}
