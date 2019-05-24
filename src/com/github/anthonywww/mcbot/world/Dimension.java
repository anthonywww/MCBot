package com.github.anthonywww.mcbot.world;

public enum Dimension {
	
	NETHER(-1),
	NORMAL(0),
	END(1);

	private final int id;

	private Dimension(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
	public static Dimension parseDimension(String name) {
		for (Dimension dimension : values()) {
			if (dimension.name().equalsIgnoreCase(name)) {
				return dimension;
			}
		}
		return null;
	}

	public static Dimension getDimensionById(int id) {
		for (Dimension dimension : values()) {
			if (dimension.getId() == id) {
				return dimension;
			}
		}
		return null;
	}
}
