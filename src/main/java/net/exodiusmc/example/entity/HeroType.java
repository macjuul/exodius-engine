package net.exodiusmc.example.entity;

public enum HeroType {
	BLUE("hero_anim.png"),
	RED("hero_red_anim.png"),
	GREEN("hero_green_anim.png"),
	PURPLE("hero_purple_anim.png");
	
	private String fileName;
	
	private HeroType(String name) {
		this.fileName= name;
	}
	
	public String getFileName() {
		return fileName;
	}
}

