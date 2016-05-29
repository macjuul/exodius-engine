package net.exodiusmc.example_jetpack_jordy;

import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.Rectangle;
import net.exodiusmc.engine.util.GeneralUtils;

public class Rocket {
	private Location pos;
	private double speed_mod;
	private boolean warn = false;
	private Rectangle hitbox = new Rectangle(Location.constructZero(), 100, 38);
	
	public Rocket(Location pos) {
		this.pos = pos;
		this.speed_mod = GeneralUtils.randomDoubleInRange(1, 1.4);
	}
	
	public Location getPosition() {
		return this.pos;
	}
	
	public void tick(float speed) {
		this.pos.setX(this.pos.getX() - (15 * speed_mod));
		
		if(this.pos.getX() > Main.WIDTH + 50) {
			this.warn = true;
		} else {
			this.warn = false;
		}
	}
	
	public boolean shouldWarn() {
		return this.warn;
	}
	
	public Rectangle getHitbox() {
		return this.hitbox;
	}
	
	public void explode() {}
}
