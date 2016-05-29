package net.exodiusmc.example_monster_hunt;

public enum SwordDirection {
	UP(0),
	UP_RIGHT(45),
	RIGHT(90),
	RIGHT_DOWN(135),
	DOWN(180),
	DOWN_LEFT(225),
	LEFT(270),
	LEFT_UP(315);
	
	private int base;
	
	private SwordDirection(int base) {
		this.base = base;
	}
	
	public int getBaseRotation() {
		return this.base;
	}
}
