package net.exodiusmc.example.entity;

public enum EntityType {
	HERO(false),
	MONSTER(true),
	MUMMY(true),
	SKELETON(true),
	EXTRA_HEART(false),
	POWER_HEART(false),
	DEMON(true);
	
	private boolean hostile;
	
	private EntityType(boolean hostile) {
		this.hostile = hostile;
	}
	
	public boolean isHostile() {
		return hostile;
	}
}
