package net.exodiusmc.example_monster_hunt;

import net.exodiusmc.engine.Location;
import net.exodiusmc.engine.util.GeneralUtils;
import net.exodiusmc.engine.Rectangle;

public class Util {
	public static Location RandomSpawnLocation(Rectangle playField) {
		int pos = GeneralUtils.randomIntInRange(0, 5);
		int x;
		int y;
		int spacing = 100;
		int width = (int) playField.getWidth() + 100;
		int height = (int) playField.getHeight() + 100;
		
		int X_token = GeneralUtils.randomIntInRange(0, width);
		int Y_token = GeneralUtils.randomIntInRange(0, height);
		
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
		
		return new Location(x, y);
	}
}
