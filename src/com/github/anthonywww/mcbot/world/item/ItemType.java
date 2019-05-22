package com.github.anthonywww.mcbot.world.item;

import static com.github.anthonywww.mcbot.world.item.ItemType.Flags.*;

public enum ItemType {

	UNDEFINED(-1),
	// Name,    ID,           Dura/DV, Stack, Tool Group, Flags
	IRON_SHOVEL(256,            251,   1,    ItemGroup.SHOVEL, REPAIRABLE | ENCHANTABLE),
	IRON_PICKAXE(257,           251,   1,    ItemGroup.PICKAXE, REPAIRABLE | ENCHANTABLE),
	IRON_AXE(258,               251,   1,    ItemGroup.AXE, REPAIRABLE | ENCHANTABLE),
	FLINT_AND_STEEL(259,        64,    1,    ItemGroup.FLINT_AND_STEEL, REPAIRABLE | ENCHANTABLE),
	APPLE(260,                  0,     64,   null, UNBREAKABLE | EDIBLE),
	BOW(261,                    384,   1,    ItemGroup.BOW, REPAIRABLE | ENCHANTABLE),
	ARROW(262,                  0,     64,   null, UNBREAKABLE),
	COAL(263,                   0,     64,   null, UNBREAKABLE),
	CHARCOAL(263,               1,     64,   null, UNBREAKABLE),
	DIAMOND(264,                0,     64,   null, UNBREAKABLE),
	IRON_INGOT(265,             0,     64,   null, UNBREAKABLE),
	GOLD_INGOT(266,             0,     64,   null, UNBREAKABLE),
	IRON_SWORD(267,             250,   1,    ItemGroup.SWORD, REPAIRABLE | ENCHANTABLE),
	WOODEN_SWORD(268,           59,    1,    ItemGroup.SWORD, REPAIRABLE | ENCHANTABLE),
	WOODEN_SHOVEL(269,          60,    1,    ItemGroup.SHOVEL, REPAIRABLE | ENCHANTABLE),
	WOODEN_PICKAXE(270,         60,    1,    ItemGroup.PICKAXE, REPAIRABLE | ENCHANTABLE),
	WOODEN_AXE(271,             60,    1,    ItemGroup.AXE, REPAIRABLE | ENCHANTABLE),
	STONE_SWORD(272,            131,   1,    ItemGroup.SWORD, REPAIRABLE | ENCHANTABLE),
	STONE_SHOVEL(273,           132,   1,    ItemGroup.SHOVEL, REPAIRABLE | ENCHANTABLE),
	STONE_PICKAXE(274,          132,   1,    ItemGroup.PICKAXE, REPAIRABLE | ENCHANTABLE),
	STONE_AXE(275,              132,   1,    ItemGroup.AXE, REPAIRABLE | ENCHANTABLE),
	DIAMOND_SWORD(276,          1561,  1,    ItemGroup.SWORD, REPAIRABLE | ENCHANTABLE),
	DIAMOND_SHOVEL(277,         1562,  1,    ItemGroup.SHOVEL, REPAIRABLE | ENCHANTABLE),
	DIAMOND_PICKAXE(278,        1562,  1,    ItemGroup.PICKAXE, REPAIRABLE | ENCHANTABLE),
	DIAMOND_AXE(279,            1562,  1,    ItemGroup.AXE, REPAIRABLE | ENCHANTABLE),
	STICK(280,                  0,     64,   null, UNBREAKABLE),
	BOWL(281,                   0,     64,   null, UNBREAKABLE),
	MUSHROOM_STEW(282,          0,     1,    null, UNBREAKABLE | EDIBLE),
	GOLDEN_SWORD(283,           32,    1,    ItemGroup.SWORD, REPAIRABLE | ENCHANTABLE),
	GOLDEN_SHOVEL(284,          33,    1,    ItemGroup.SHOVEL, REPAIRABLE | ENCHANTABLE),
	GOLDEN_PICKAXE(285,         33,    1,    ItemGroup.PICKAXE, REPAIRABLE | ENCHANTABLE),
	GOLDEN_AXE(286,             33,    1,    ItemGroup.AXE, REPAIRABLE | ENCHANTABLE),
	FEATHER(288,                0,     64,   null, UNBREAKABLE),
	GUNPOWDER(289,              0,     64,   null, UNBREAKABLE),
	WOODEN_HOE(290,             60,    1,    ItemGroup.HOE, REPAIRABLE | ENCHANTABLE),
	STONE_HOE(291,              132,   1,    ItemGroup.HOE, REPAIRABLE | ENCHANTABLE),
	IRON_HOE(292,               251,   1,    ItemGroup.HOE, REPAIRABLE | ENCHANTABLE),
	DIAMOND_HOE(293,            1562,  1,    ItemGroup.HOE, REPAIRABLE | ENCHANTABLE),
	GOLDEN_HOE(294,             33,    1,    ItemGroup.HOE, REPAIRABLE | ENCHANTABLE),
	WHEAT_SEEDS(295,            0,     64,   null, UNBREAKABLE),
	WHEAT(296,                  0,     64,   null, UNBREAKABLE),
	BREAD(297,                  0,     64,   null, UNBREAKABLE),
	LEATHER_HELMET(298,         56,    1,    ItemGroup.HELMET, REPAIRABLE | ENCHANTABLE),
	LEATHER_CHESTPLATE(299,     81,    1,    ItemGroup.CHESTPLATE, REPAIRABLE | ENCHANTABLE),
	LEATHER_LEGGINGS(300,       76,    1,    ItemGroup.LEGGINGS, REPAIRABLE | ENCHANTABLE),
	LEATHER_BOOTS(301,          66,    1,    ItemGroup.BOOTS, REPAIRABLE | ENCHANTABLE),
	CHAINMAIL_HELMET(302,       166,   1,    ItemGroup.HELMET, REPAIRABLE | ENCHANTABLE),
	CHAINMAIL_CHESTPLATE(303,   241,   1,    ItemGroup.CHESTPLATE, REPAIRABLE | ENCHANTABLE),
	CHAINMAIL_LEGGINGS(304,     226,   1,    ItemGroup.LEGGINGS, REPAIRABLE | ENCHANTABLE),
	CHAINMAIL_BOOTS(305,        196,   1,    ItemGroup.LEGGINGS, REPAIRABLE | ENCHANTABLE),
	IRON_HELMET(306,            166,   1,    ItemGroup.HELMET, REPAIRABLE | ENCHANTABLE),
	IRON_CHESTPLATE(307,        241,   1,    ItemGroup.CHESTPLATE, REPAIRABLE | ENCHANTABLE),
	IRON_LEGGINGS(308,          226,   1,    ItemGroup.LEGGINGS, REPAIRABLE | ENCHANTABLE),
	IRON_BOOTS(309,             196,   1,    ItemGroup.BOOTS, REPAIRABLE | ENCHANTABLE),
	DIAMOND_HELMET(310,         364,   1,    ItemGroup.HELMET, REPAIRABLE | ENCHANTABLE),
	DIAMOND_CHESTPLATE(311,     529,   1,    ItemGroup.CHESTPLATE, REPAIRABLE | ENCHANTABLE),
	DIAMOND_LEGGINGS(312,       496,   1,    ItemGroup.LEGGINGS, REPAIRABLE | ENCHANTABLE),
	DIAMOND_BOOTS(313,          430,   1,    ItemGroup.BOOTS, REPAIRABLE | ENCHANTABLE),
	GOLD_HELMET(310,            78,    1,    ItemGroup.HELMET, REPAIRABLE | ENCHANTABLE),
	GOLD_CHESTPLATE(311,        113,   1,    ItemGroup.CHESTPLATE, REPAIRABLE | ENCHANTABLE),
	GOLD_LEGGINGS(312,          106,   1,    ItemGroup.LEGGINGS, REPAIRABLE | ENCHANTABLE),
	GOLD_BOOTS(313,             92,    1,    ItemGroup.BOOTS, REPAIRABLE | ENCHANTABLE),
	FLINT(318,                  0,     64,   null, UNBREAKABLE),
	PORKCHOP(319,               0,     64,   null, UNBREAKABLE | EDIBLE),
	COOKED_PORKCHOP(320,        0,     64,   null, UNBREAKABLE | EDIBLE),
	PAINTING(321,               0,     64,   null, UNBREAKABLE),
	GOLDEN_APPLE(322,           0,     64,   null, UNBREAKABLE | EDIBLE),
	ENCHANTED_GOLDEN_APPLE(322, 1,     64,   null, UNBREAKABLE | EDIBLE),
	SIGN(323,                   0,     16,   null, UNBREAKABLE),
	OAK_DOOR(324,               0,     64,   null, UNBREAKABLE),
	BUCKET(325,                 0,     16,   null, UNBREAKABLE),
	WATER_BUCKET(326,           0,     1,    null, UNBREAKABLE),
	LAVA_BUCKET(327,            0,     1,    null, UNBREAKABLE),
	MINECART(328,               0,     1,    null, UNBREAKABLE),
	SADDLE(329,                 0,     1,    null, UNBREAKABLE),
	IRON_DOOR(330,              0,     64,   null, UNBREAKABLE),
	REDSTONE(331,               0,     64,   null, UNBREAKABLE),
	SNOWBALL(332,               0,     16,   null, UNBREAKABLE),
	OAK_BOAT(333,               0,     1,    null, UNBREAKABLE),
	LEATHER(334,                0,     64,   null, UNBREAKABLE),
	MILK_BUCKET(335,            0,     1,    null, UNBREAKABLE),
	BRICK(336,                  0,     1,    null, UNBREAKABLE),
	CLAY_BALL(337,              0,     64,   null, UNBREAKABLE),
	SUGAR_CANE(338,             0,     64,   null, UNBREAKABLE),
	PAPER(339,                  0,     64,   null, UNBREAKABLE),
	BOOK(340,                   0,     64,   null, UNBREAKABLE),
	SLIME_BALL(341,             0,     64,   null, UNBREAKABLE),
	CHEST_MINECART(342,         0,     1,    null, UNBREAKABLE),
	FURNACE_MINECART(343,       0,     1,    null, UNBREAKABLE),
	EGG(344,                    0,     64,   null, UNBREAKABLE),
	COMPASS(345,                0,     64,   null, UNBREAKABLE),
	
	
	
	
	
	
	TURTLE_HELMET(0,           276,   1,    ItemGroup.HELMET, REPAIRABLE | ENCHANTABLE),
	
	
	
	SHEARS(359,             0,     1,    ItemGroup.SHEARS, REPAIRABLE | ENCHANTABLE),
	
	
	
	
	
	
	WAIT_DISC(2267, UNBREAKABLE);

	private final int id;
	private final int maxDurability;
	private final int maxStack;
	private final int flags;
	private final ItemGroup toolType;

	private ItemType(int id) {
		this(id, null);
	}
	
	private ItemType(int id, ItemGroup toolType) {
		this(id, toolType, 0);
	}
	
	private ItemType(int id, ItemGroup toolType, int flags) {
		this(id, -1, 64, toolType, flags);
	}
	
	private ItemType(int id, int flags) {
		this(id, -1, 64, null, flags);
	}
	
	/**
	 * Item ID, Damage Value/Max Durability, Max Stack Size, Tool Group, Flags.
	 * 
	 * If the UNBREAKABLE flag is set, the Max Durability is used to determine the Damage Value of the item.
	 * For example: Coal has the ID of 263:0. But Charcoal has the ID 263:1. Same ID, different Damage Values.
	 * 
	 * @param id
	 * @param maxDurability
	 * @param maxStack
	 * @param toolType
	 * @param flags
	 */
	private ItemType(int id, int maxDurability, int maxStack, ItemGroup toolType, int flags) {
		this.id = id;
		this.maxDurability = maxDurability;
		this.maxStack = maxStack;
		this.toolType = toolType;
		this.flags = flags;
	}

	public int getId() {
		return id;
	}
	
	public int getMaxDurability() {
		return maxDurability;
	}
	
	public String getName() {
		return this.name().toLowerCase();
	}

	public int getMaxStackSize() {
		return maxStack;
	}

	public boolean isUnbreakable() {
		return (flags & UNBREAKABLE) == UNBREAKABLE;
	}
	
	public boolean isInteractable() {
		return (flags & INTERACTABLE) == INTERACTABLE;
	}

	public boolean isRepairable() {
		return (flags & REPAIRABLE) == REPAIRABLE;
	}
	
	public boolean isEnchantable() {
		return (flags & ENCHANTABLE) == ENCHANTABLE;
	}
	
	public boolean isEdible() {
		return (flags & EDIBLE) == EDIBLE;
	}

	public ItemGroup getToolType() {
		return toolType;
	}

	public static ItemType getById(int id) {
		for (ItemType type : values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return UNDEFINED;
	}
	
	public static ItemType getByName(String name) {
		for (ItemType type : values()) {
			if (type.getName().equalsIgnoreCase(name)) {
				return type;
			}
		}
		return UNDEFINED;
	}


	protected static final class Flags {
		public static final int UNBREAKABLE = 1, INTERACTABLE = 2, REPAIRABLE = 4, ENCHANTABLE = 8, EDIBLE = 16;
	}

}
