package net.exodiusmc.example;

import javafx.scene.image.Image;
import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.shape.Rectangle;
import net.exodiusmc.engine.util.CoreUtils;

public class Monster extends Entity {
	Image i;

	public Monster(Location pos) {
		super(pos);
		setMovementSpeed(0.3);
	}
	
	public static Monster SpawnMonster(Rectangle playField) {
		int pos = CoreUtils.randomIntInRange(0, 5);
		int x;
		int y;
		int spacing = 100;
		int width = (int) playField.getWidth() + 100;
		int height = (int) playField.getHeight() + 100;
		
		int X_token = CoreUtils.randomIntInRange(0, width);
		int Y_token = CoreUtils.randomIntInRange(0, height);
		
		switch(pos) {
		default: // LEFT
			x = -spacing;
			y = Y_token;
			break;
		case 1: // TOP
			x = X_token;
			y = -spacing;
			break;
		case 2: // RIGHT
			x = width + spacing;
			y = Y_token;
			break;
		case 3: // DOWN
			x = X_token;
			y = height + spacing;
			break;
		}
		
		return new Monster(new Location(x, y));
	}
	
	public void kill() {
		
	}

}
