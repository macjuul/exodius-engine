package net.exodiusmc.example_monster_hunt.entity;

public enum EntityType {
	HERO(false),
	MONSTER(true),
	MUMMY(true),
	SKELETON(true),
	EXTRA_HEART(false),
	POWER_HEART(false),
	ARROW(false),
	DEMON(true);
	
	private boolean hostile;
	
	private EntityType(boolean hostile) {
		this.hostile = hostile;
	}
	
	public boolean isHostile() {
		return hostile;
	}
}
