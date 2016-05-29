package net.exodiusmc.example_jetpack_jordy;

public enum Version {
	V1_0_0(1, 0, 0),
	V1_0_1(1, 0, 1),
	V1_0_2(1, 0, 2),
	V1_1_0(1, 1, 0),
	V1_1_1(1, 1, 1),
	V2_0_0(2, 0, 0);
	
	private int major;
	private int minor;
	private int patch;
	
	private Version(int major, int minor, int patch) {
		this.major = major;
		this.minor = minor;
		this.patch = patch;
	}
	
	private Version() {
		int largest = 0;
		int largest_major = 0;
		int largest_minor = 0;
		int largest_patch = 0;
		
		for(Version v : Version.values()) {
			if(Combine(v) > largest) {
				largest = Combine(v);
				
				largest_major = v.getMajor();
				largest_minor = v.getMinor();
				largest_patch = v.getPatch();
			}
		}
		
		this.major = largest_major;
		this.minor = largest_minor;
		this.patch = largest_patch;
	}

	public int getMajor() {
		return major;
	}

	public int getMinor() {
		return minor;
	}

	public int getPatch() {
		return patch;
	}

	public boolean lt(Version other) {
		return Combine(this) < Combine(other);
	}

	public boolean lte(Version other) {
		return Combine(this) <= Combine(other);
	}

	public boolean gt(Version other) {
		return Combine(this) > Combine(other);
	}

	public boolean gte(Version other) {
		return Combine(this) >= Combine(other);
	}
	
	public boolean equals(Version other) {
		return Combine(this) == Combine(other);
	}
	
	public String toString() {
		return this.major + "." + this.minor + "." + this.patch;
	}
	
	public boolean isLatest() {
		int largest = 0;
		
		for(Version v : Version.values()) {
			if(Combine(v) > largest) {
				largest = Combine(v);
			}
		}
		
		return Combine(this) == largest;
	}
	
	private static int Combine(Version v) {
		return v.getMajor() + v.getMinor() + v.getPatch();
	}

}
