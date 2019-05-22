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
	FISHING_ROD(346,            0),
	CLOCK(347,                  0),
	GLOWSTONE_DUST(348),
	RAW_FISH(349),
	RAW_SALMON(349, 1),
	CLOWNFISH(349, 2),
	PUFFERFISH(349, 3),
	COOKED_FISH(350, 0),
	COOKED_SALMON(350, 1),
	INK_SACK(351, 0),
	ROSE_RED(351, 1),
	CACTUS_GREEN(351, 2),
	COCO_BEANS(351, 3),
	LAPIS_LAZULI(351, 4),
	PURPLE_DYE(351, 5),
	CYAN_DYE(351, 6),
	LIGHT_GRAY_DYE(351, 7),
	GRAY_DYE(351, 8),
	PINK_DYE(351, 9),
	LIME_DYE(351, 10),
	DANDELION_YELLOW(351, 11),
	LIGHT_BLUE_DYE(351, 12),
	MAGENTA_DYE(351, 13),
	ORANGE_DYE(351, 14),
	BONE_MEAL(351, 15),
	BONE(352, 0),
	SUGAR(353, 0),
	CAKE(354, 0),
	BED(355, 0),
	REPEATER(356, 0),
	COOKIE(357, 0),
	FILLED_MAP(358, 0),
	SHEARS(359,             0,     1,    ItemGroup.SHEARS, REPAIRABLE | ENCHANTABLE),
	MELON(360, 0),
	PUMKIN_SEEDS(361, 0),
	MELON_SEEDS(362, 0),
	BEEF(363, 0),
	COOKED_BEEF(364, 0),
	CHICKEN(365, 0),
	COOKED_CHICKEN(366, 0),
	ROTTEN_FLESH(367, 0),
	ENDER_PEARL(368, 0),
	BLAZE_ROD(369, 0),
	GHAST_TEAR(370, 0),
	GOLD_NUGGET(371, 0),
	NETHER_WART(372, 0),
	POTION(373, 0),
	GLASS_BOTTLE(374, 0),
	SPIDER_EYE(375, 0),
	FERMENTED_SPIDER_EYE(376, 0),
	BLAZE_POWDER(377, 0),
	MAGMA_CREAM(378, 0),
	BREWING_STAND(379, 0),
	CAULDRON(380, 0),
	ENDER_EYE(381, 0),
	SPECKLED_MELON(382, 0),
	SPAWN_ELDER_GUARDIAN(383, 4),
	SPAWN_WITHER_SKELETON(383, 5),
	SPAWN_STRAY(383, 6),
	SPAWN_HUSK(383, 23),
	SPAWN_ZOMBIE_VILLAGER(383, 27),
	SPAWN_SKELETON_HORSE(383, 28),
	SPAWN_ZOMBIE_HORSE(383, 29),
	SPAWN_DONKEY(383, 31),
	SPAWN_MULE(383, 32),
	SPAWN_EVOKER(383, 34),
	SPAWN_VEX(383, 35),
	SPAWN_VINDICATOR(383, 36),
	SPAWN_CREEPER(383, 50),
	SPAWN_SKELETON(383, 51),
	SPAWN_SPIDER(383, 52),
	SPAWN_ZOMBIE(383, 54),
	SPAWN_SLIME(383, 55),
	SPAWN_GHAST(383, 56),
	SPAWN_ZOMBIE_PIGMAN(383, 57),
	SPAWN_ENDERMAN(383, 58),
	SPAWN_CAVE_SPIDER(383, 59),
	SPAWN_SILVERFISH(383, 60),
	SPAWN_BLAZE(383, 61),
	SPAWN_MAGMA_CUBE(383, 62),
	SPAWN_BAT(383, 65),
	SPAWN_WITCH(383, 66),
	SPAWN_ENDERMITE(383, 67),
	SPAWN_GUARDIAN(383, 68),
	SPAWN_SHULKER(383, 69),
	SPAWN_PIG(383, 90),
	SPAWN_SHEEP(383, 91),
	SPAWN_COW(383, 92),
	SPAWN_CHICKEN(383, 93),
	SPAWN_SQUID(383, 94),
	SPAWN_WOLF(383, 95),
	SPAWN_MOOSHROOM(383, 96),
	SPAWN_OCELOT(383, 98),
	SPAWN_HORSE(383, 100),
	SPAWN_RABBIT(383, 101),
	SPAWN_POLAR_BEAR(383, 102),
	SPAWN_LIAMA(383, 103),
	SPAWN_PARROT(383, 105),
	SPAWN_VILLAGER(383, 120),
	EXPERIENCE_BOTTLE(384, 0),
	FIRE_CHARGE(385, 0),
	BOOK_AND_QUILL(386, 0),
	WRITTEN_BOOK(387, 0),
	EMERALD(388, 0),
	ITEM_FRAME(389, 0),
	FLOWER_POT(390, 0),
	CARROT(391, 0),
	POTATO(392, 0),
	BAKED_POTATO(393, 0),
	POISONOUS_POTATO(394, 0),
	EMPTY_MAP(395, 0),
	GOLDEN_CARROT(396, 0),
	//MOB_HEAD TODO: look into mob heads
	CARROT_ON_A_STICK(398, 0),
	NETHER_STAR(399, 0),
	PUMPKIN_PIE(400, 0),
	FIREWORKS(401, 0),
	FIREWORK_CHARGE(402, 0),
	ENCHANTED_BOOK(403, 0),
	COMPARATOR(404, 0),
	NETHERBRICK(405, 0),
	
	
	
	
	
	
	
	
	
	
	TURTLE_HELMET(0,           276,   1,    ItemGroup.HELMET, REPAIRABLE | ENCHANTABLE),
	
	
	
	
	
	
	
	
	
	
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
