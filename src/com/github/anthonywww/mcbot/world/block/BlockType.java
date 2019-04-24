package com.github.anthonywww.mcbot.world.block;

import static com.github.anthonywww.mcbot.world.block.BlockType.Flags.*;
import static com.github.anthonywww.mcbot.world.item.ItemGroup.*;
import com.github.anthonywww.mcbot.world.item.ItemGroup;

/**
 * Block registry
 */
public enum BlockType {

	UNDEFINED(-1, 0),
	
	AIR(0, 0, INDESTRUCTABLE),
	STONE(1, 0, PICKAXE),
	GRANITE(1, 1, PICKAXE),
	POLISHED_GRANITE(1, 2, PICKAXE),
	DIORITE(1, 3, PICKAXE),
	POLISHED_DIORITE(1, 4, PICKAXE),
	ANDESITE(1, 5, PICKAXE),
	POLISHED_ANDESITE(1, 6, PICKAXE),
	GRASS_BLOCK(2, 0, SHOVEL),
	DIRT(3, 0, SHOVEL),
	COARSE_DIRT(3, 1, SHOVEL),
	PODZOL(3, 2, SHOVEL),
	COBBLESTONE(4, 0, PICKAXE),
	OAK_PLANKS(5, 0, AXE),
	BIRCH_PLANKS(5, 1, AXE),
	JUNGLE_PLANKS(5, 3, AXE),
	ACACIA_PLANKS(5, 4, AXE),
	DARK_OAK_PLANKS(5, 5, AXE),
	OAK_SAPLING(6, 0, PLACEABLE | INTERACTABLE),
	SPRUCE_SAPLING(6, 1, PLACEABLE | INTERACTABLE),
	BIRCH_SAPLING(6, 2, PLACEABLE | INTERACTABLE),
	JUNGLE_SAPLING(6, 3, PLACEABLE | INTERACTABLE),
	ACACIA_SAPLING(6, 4, PLACEABLE | INTERACTABLE),
	DARK_OAK_SAPLING(6, 5, PLACEABLE | INTERACTABLE),
	BEDROCK(7, 0, SOLID | PLACEABLE | INDESTRUCTABLE), // Adminium, Bedrock
	FLOWING_WATER(8, 0, PLACEABLE | INDESTRUCTABLE),
	STILL_WATER(9, 0, PLACEABLE | INDESTRUCTABLE),
	FLOWING_LAVA(10, 0, PLACEABLE | INDESTRUCTABLE),
	STILL_LAVA(11, 0, PLACEABLE | INDESTRUCTABLE),
	SAND(12, 0, SHOVEL, SOLID | GRAVITY | PLACEABLE),
	RED_SAND(12, 1, SHOVEL, SOLID | PLACEABLE | GRAVITY),
	GRAVEL(13, 0, SHOVEL, SOLID | PLACEABLE | GRAVITY),
	GOLD_ORE(14, 0, PICKAXE),
	IRON_ORE(15, 0, PICKAXE),
	COAL_ORE(16, 0, PICKAXE),
	OAK_LOG(17, 0, AXE),
	SPRUCE_LOG(17, 1, AXE),
	BIRCH_LOG(17, 2, AXE),
	JUNGLE_LOG(17, 3, AXE),
	OAK_LEAVES(18, 0, SHEARS),
	SPRUCE_LEAVES(18, 1, SHEARS),
	BIRCH_LEAVES(18, 2, SHEARS),
	JUNGLE_LEAVES(18, 3, SHEARS),
	SPONGE(19, 0, SHEARS),
	WET_SPONGE(19, 1, SHEARS),
	GLASS(20, 0, SWORD),
	LAPIS_LAZULI_ORE(21, 0, PICKAXE),
	LAPIS_LAZULI(22, 0, PICKAXE),
	DISPENSER(23, 0, PICKAXE),
	SANDSTONE(24, 0, PICKAXE),
	CHISELED_SANDSTONE(24, 1, PICKAXE),
	CUT_SANDSTONE(24, 2, PICKAXE),
	NOTEBLOCK(25, 0, AXE),
	BED(26, 0, AXE),
	POWERED_RAIL(27, 0, PICKAXE),
	DETECTOR_RAIL(28, 0, PICKAXE),
	STICKY_PISTON(29, 0, PICKAXE),
	COBWEB(30, 0, SWORD),
	DEAD_SHRUB(31, 0, PLACEABLE),
	GRASS(31, 1, PLACEABLE),
	FERN(31, 2, PLACEABLE),
	DEAD_BUSH(32, 0, PLACEABLE),
	PISTON(33, 0, PICKAXE),
	PISTON_HEAD(34, 0, PICKAXE),
	WHITE_WOOL(35, 0, SHEARS),
	ORANGE_WOOL(35, 1, SHEARS),
	MAGENTA_WOOL(35, 2, SHEARS),
	LIGHT_BLUE_WOOL(35, 3, SHEARS),
	YELLOW_WOOL(35, 4, SHEARS),
	LIME_WOOL(35, 5, SHEARS),
	PINK_WOOL(35, 6, SHEARS),
	GRAY_WOOL(35, 7, SHEARS),
	LIGHT_GRAY_WOOL(35, 8, SHEARS),
	CYAN_WOOL(35, 9, SHEARS),
	PURPLE_WOOL(35, 10, SHEARS),
	BLUE_WOOL(35, 11, SHEARS),
	BROWN_WOOL(35, 12, SHEARS),
	GREEN_WOOL(35, 13, SHEARS),
	RED_WOOL(35, 14, SHEARS),
	BLACK_WOOL(35, 15, SHEARS),
	DANDELION(37, 0, PLACEABLE),
	POPPY(38, 0, PLACEABLE),
	BLUE_ORCHID(38, 1, PLACEABLE),
	ALLIUM(38, 2, PLACEABLE),
	AZURE_BLUET(38, 3, PLACEABLE),
	RED_TULIP(38, 4, PLACEABLE),
	ORANGE_TULIP(38, 5, PLACEABLE),
	WHITE_TULIP(38, 6, PLACEABLE),
	PINK_TULIP(38, 7, PLACEABLE),
	OXEYE_DAISY(38, 8, PLACEABLE),
	BROWN_MUSHROOM(39, 0, PLACEABLE),
	RED_MUSHROOM(40, 0, PLACEABLE),
	GOLD_BLOCK(41, 0, PICKAXE),
	IRON_BLOCK(42, 0, PICKAXE),
	DOUBLE_STONE_SLAB(43, 0, PICKAXE),
	DOUBLE_SANDSTONE_SLAB(43, 1, PICKAXE),
	DOUBLE_WOODEN_SLAB(43, 2, AXE),
	DOUBLE_COBBLESTONE_SLAB(43, 3, PICKAXE),
	DOUBLE_BRICK_SLAB(43, 4, PICKAXE),
	DOUBLE_STONE_BRICK_SLAB(43, 5, PICKAXE),
	DOUBLE_NETHER_BRICK_SLAB(43, 6, PICKAXE),
	DOUBLE_QUARTZ_SLAB(43, 7, PICKAXE),
	STONE_SLAB(44, 0, PICKAXE),
	SANDSTONE_SLAB(44, 1, PICKAXE),
	WOODEN_SLAB(44, 2, AXE),
	COBBLESTONE_SLAB(44, 3, PICKAXE),
	BRICK_SLAB(44, 4, PICKAXE),
	STONE_BRICK_SLAB(44, 5, PICKAXE),
	NETHER_BRICK_SLAB(44, 6, PICKAXE),
	QUARTZ_SLAB(44, 7, PICKAXE),
	BRICK_BLOCK(45, 0, PICKAXE),
	TNT(46, 0),
	BOOKSHELF(47, 0, AXE),
	MOSSY_COBBLESTONE(48, 0, PICKAXE),
	OBSIDIAN(49, 0, PICKAXE), // TODO: Diamond pickaxe only
	TORCH(50, 0),
	FIRE(51, 0, PLACEABLE),
	MOB_SPANWER(52, 0, PICKAXE), // TODO: Iron pickaxe and above only
	OAK_WOOD_STAIRS(53, 0, AXE),
	CHEST(54, 0, AXE, SOLID | PLACEABLE | INTERACTABLE),
	REDSTONE_WIRE(55, 0, PLACEABLE),
	DIAMOND_ORE(56, 0, PICKAXE), // TODO: Iron pickaxe and above only
	DIAMOND_BLOCK(57, 0, PICKAXE),
	CRAFTING_TABLE(58, 0, AXE, SOLID | PLACEABLE | INTERACTABLE),
	WHEAT_CROP(59, 0, PLACEABLE | INTERACTABLE),
	FARMLAND(60, 0, SHOVEL, SOLID | PLACEABLE | INTERACTABLE),
	FURNACE(61, 0, PICKAXE),
	LIT_FURNACE(62, 0, PICKAXE),
	STANDING_SIGN(63, 0, AXE, PLACEABLE | INTERACTABLE),
	OAK_WOOD_DOOR(64, 0, AXE),
	LADDER(65, 0, PLACEABLE),
	RAIL(66, 0, PICKAXE, PLACEABLE | INTERACTABLE),
	COBBLESTONE_STAIRS(67, 0, PICKAXE),
	WALL_SIGN(68, 0, AXE, PLACEABLE | INTERACTABLE),
	LEVER(69, 0, PLACEABLE | INTERACTABLE),
	STONE_PRESSURE_PLATE(70, 0, PICKAXE, PLACEABLE),
	IRON_DOOR(71, 0, PICKAXE, SOLID | PLACEABLE | WATER_LOGGABLE),
	WOODEN_PRESSURE_PLATE(72, 0, PLACEABLE),
	REDSTONE_ORE(73, 0, PICKAXE),
	LIT_REDSTONE_ORE(74, 0, PICKAXE),
	REDSTONE_TORCH(75, 0, PLACEABLE),
	LIT_REDSTONE_TORCH(76, 0, PLACEABLE),
	STONE_BUTTON(77, 0, PLACEABLE),
	SNOW(78, 0, SHOVEL, SOLID | PLACEABLE | INTERACTABLE),
	ICE(79, 0), // TODO: Needs silk touch to recover
	SNOW_BLOCK(80, 0, SHOVEL),
	CACTUS(81, 0), // TODO: Torches/entities cannot be placed on
	CLAY(82, 0, SHOVEL),
	SUGAR_CANE(83, 0, PLACEABLE | INTERACTABLE),
	JUKEBOX(84, 0, AXE, SOLID | PLACEABLE | INTERACTABLE),
	OAK_WOOD_FENCE(85, 0, AXE),
	PUMPKIN(86, 0, AXE),
	NETHERRACK(87, 0, PICKAXE),
	SOUL_SAND(88, 0, SHOVEL),
	GLOWSTONE(89, 0), // TODO: Prefer silk touch 
	PORTAL(90, 0, INDESTRUCTABLE),
	JACK_O_LANTERN(91, 0, AXE),
	CAKE(92, 0, SOLID | PLACEABLE | INTERACTABLE),
	REPEATER(93, 0, SOLID | PLACEABLE | INTERACTABLE),
	LIT_REPEATER(94, 0, SOLID | PLACEABLE | INTERACTABLE),
	WHITE_STAINED_GLASS(95, 0), // TODO: Needs silk touch to recover
	ORANGE_STAINED_GLASS(95, 1),
	MAGENTA_STAINED_GLASS(95, 2),
	LIGHT_BLUE_STAINED_GLASS(95, 3),
	YELLOW_STAINED_GLASS(95, 4),
	LIME_STAINED_GLASS(95, 5),
	PINK_STAINED_GLASS(95, 6),
	GRAY_STAINED_GLASS(95, 7),
	LIGHT_GRAY_STAINED_GLASS(95, 8),
	CYAN_STAINED_GLASS(95, 9),
	PURPLE_STAINED_GLASS(95, 10),
	
	
	
	
	
	
	STRUCTURE_BLOCK(255, 0, SOLID | PLACEABLE | INDESTRUCTABLE | INTERACTABLE);
	
	private final int id;
	private final int metadata;
	private final int maxStack;
	private final int flags;
	private final ItemGroup toolType;

//	private BlockType(int id) {
//		this(id, 0);
//	}
	
	private BlockType(int id, int metadata) {
		this(id, metadata, SOLID | PLACEABLE);
	}
	
	private BlockType(int id, int metadata, int flags) {
		this(id, metadata, 64, flags, null);
	}
	
	private BlockType(int id, ItemGroup toolType) {
		this(id, toolType, SOLID | PLACEABLE);
	}
	
	private BlockType(int id, ItemGroup toolType, int flags) {
		this(id, 0, 64, flags, toolType);
	}
	
	private BlockType(int id, int metadata, ItemGroup toolType) {
		this(id, metadata, 64, SOLID | PLACEABLE, toolType);
	}
	
	private BlockType(int id, int metadata, ItemGroup toolType, int flags) {
		this(id, metadata, 64, flags, toolType);
	}
	
	private BlockType(int id, int metadata, int maxStack, int flags, ItemGroup toolType) {
		this.id = id;
		this.metadata = metadata;
		this.flags = flags;
		this.maxStack = maxStack;
		this.toolType = toolType;
	}

	public int getId() {
		return id;
	}
	
	public int getMetadata() {
		return metadata;
	}
	
	public String getName() {
		return this.name().toLowerCase();
	}

	public int getMaxStackSize() {
		return maxStack;
	}

	public boolean isSolid() {
		return (flags & Flags.SOLID) == Flags.SOLID;
	}

	public boolean isInteractable() {
		return (flags & Flags.INTERACTABLE) == Flags.INTERACTABLE;
	}

	public boolean isPlaceable() {
		return (flags & Flags.PLACEABLE) == Flags.PLACEABLE;
	}

	public boolean isIndestructable() {
		return (flags & Flags.INDESTRUCTABLE) == Flags.INDESTRUCTABLE;
	}
	
	public boolean isWaterLoggable() {
		return (flags & Flags.WATER_LOGGABLE) == Flags.WATER_LOGGABLE;
	}

	public boolean hasGravity() {
		return (flags & Flags.GRAVITY) == Flags.GRAVITY;
	}
	
	public ItemGroup getToolType() {
		return toolType;
	}

	public static BlockType getById(int id) {
		for (BlockType type : values()) {
			if (type.getId() == id) {
				return type;
			}
		}
		return UNDEFINED;
	}
	
	public static BlockType getByName(String name) {
		for (BlockType type : values()) {
			if (type.getName().equalsIgnoreCase(name)) {
				return type;
			}
		}
		return UNDEFINED;
	}


	protected static final class Flags {
		public static final int SOLID = 1, INTERACTABLE = 2, PLACEABLE = 4, INDESTRUCTABLE = 8, WATER_LOGGABLE = 16, GRAVITY = 32;
	}

}
