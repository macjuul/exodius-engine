package net.exodiusmc.example_jetpack_jordy;

import javafx.scene.image.Image;
import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.util.GeneralUtils;

public class Cloud {
	private Image img;
	private float age;
	private Location pos;
	private short rot;
	private double speed_mod;
	
	public Cloud(Image img, Location pos, boolean move) {
		this.img = img;
		this.age = 0;
		this.pos = pos;
		this.rot = (short) GeneralUtils.randomIntInRange(0, 360);
		this.speed_mod = GeneralUtils.randomDoubleInRange(1, 1.4);
		
		this.pos.add(GeneralUtils.randomDoubleInRange(-10, 10), GeneralUtils.randomDoubleInRange(-10, 10));
	}
	
	public Cloud(Image img, Location pos) {
		this(img, pos, true);
	}
	
	public Image getImage() {
		return this.img;
	}
	
	public Location getPosition() {
		return this.pos;
	}
	
	public short getRotation() {
		return rot;
	}
	
	public boolean tick(float speed) {
		this.age++;
		this.pos.setX(this.pos.getX() - (speed * speed_mod));
		this.pos.setY(this.pos.getY() + (0.3 * speed_mod));
		
		if(this.age >= 75) {
			return false;
		}
		return true;
	}
	
	public float getFade() {
		return 1f - (this.age / 75f);
	}
}
