package com.github.anthonywww.mcbot.world.block;

import java.util.HashMap;
import java.util.Map;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.event.EventBus;
import com.github.anthonywww.mcbot.event.world.BlockChangeEvent;
import com.github.anthonywww.mcbot.world.World;

public final class Chunk {
	
	private final World world;
	private final ChunkLocation location;
	private final BlockLocation baseLocation;
	private final int[] light, skylight, biomes;
	private final Block[] blocks;
	private final Map<BlockLocation, TileEntity> tileEntities;

	public Chunk(World world, ChunkLocation location, Block[] blocks, int[] light, int[] skylight, int[] biomes) {
		this.world = world;
		this.location = location;
		this.baseLocation = new BlockLocation(location);
		this.blocks = blocks;
		this.light = light;
		this.skylight = skylight;
		this.biomes = biomes;
		tileEntities = new HashMap<BlockLocation, TileEntity>();
	}
	
	/**
	 * Create a chunk with an empty array of 4096 blocks and an empty array of 256 biomes
	 * @param world
	 * @param location
	 */
	public Chunk(World world, ChunkLocation location) {
		this(world, location, new Block[4096], null, null, new int[256]);
	}

	/**
	 * Get the world that this chunk belongs to
	 * @return
	 */
	public World getWorld() {
		return world;
	}

	/**
	 * Get the location of this chunk
	 * @return
	 */
	public ChunkLocation getLocation() {
		return location;
	}

	public TileEntity getTileEntityAt(int x, int y, int z) {
		return getTileEntityAt(new BlockLocation(x, y, z));
	}

	public TileEntity getTileEntityAt(BlockLocation location) {
		synchronized (tileEntities) {
			return tileEntities.get(location);
		}
	}

	public void setTileEntityAt(TileEntity tileEntity, int x, int y, int z) {
		setTileEntityAt(tileEntity, new BlockLocation(x, y, z));
	}

	public void setTileEntityAt(TileEntity tileEntity, BlockLocation location) {
		synchronized (tileEntities) {
			tileEntities.put(location, tileEntity);
		}
	}
	
	public void setBlock(Block block) {
		final int x = block.getLocation().getX();
		final int y = block.getLocation().getY();
		final int z = block.getLocation().getZ();
		final int index = y << 8 | z << 4 | x;
		if (index < 0 || index > blocks.length) {
			return;
		}
		BlockLocation location = new BlockLocation((this.location.getX() * 16) + x, (this.location.getY() * 16) + y, (this.location.getZ() * 16) + z);
		final Block oldBlock = blocks[index];
		final Block newBlock = block;
		blocks[index] = newBlock;
		EventBus eventBus = MCBot.getInstance().getEventBus();
		eventBus.fire(new BlockChangeEvent(world, location, oldBlock, newBlock));
	}
	
	public Block getBlockAt(BlockLocation location) {
		return getBlockAt(location.getX(), location.getY(), location.getZ());
	}
	
	public Block getBlockAt(int x, int y, int z) {
		int index = y << 8 | z << 4 | x;
		if (index < 0 || index > blocks.length) {
			return null;
		}
		return blocks[index];
	}
	
	public Block[] getBlocks() {
		return blocks.clone();
	}

	public int getBlockMetadataAt(BlockLocation location) {
		return getBlockMetadataAt(location.getX(), location.getY(), location.getZ());
	}

	public int getBlockMetadataAt(int x, int y, int z) {
		return getBlockAt(x, y, z).getMetadata();
	}

	public void setBlockMetadataAt(int metadata, BlockLocation location) {
		setBlockMetadataAt(metadata, location.getX(), location.getY(), location.getZ());
	}

	public void setBlockMetadataAt(int metadata, int x, int y, int z) {
		Block block = getBlockAt(x, y, z);
		Block newBlock = new Block(block.getWorld(), block.getChunk(), block.getLocation(), block.getType(), metadata);
		setBlock(newBlock);
	}

	public int getBlockLightAt(BlockLocation location) {
		return getBlockLightAt(location.getX(), location.getY(), location.getZ());
	}

	public int getBlockLightAt(int x, int y, int z) {
		int index = y << 8 | z << 4 | x;
		if (index < 0 || index > light.length) {
			return 0;
		}
		return light[index];
	}

	public int getBlockSkylightAt(BlockLocation location) {
		return getBlockSkylightAt(location.getX(), location.getY(), location.getZ());
	}

	public int getBlockSkylightAt(int x, int y, int z) {
		int index = y << 8 | z << 4 | x;
		if (index < 0 || index > skylight.length) {
			return 0;
		}
		return skylight[index] & 0xFF;
	}

	public BiomeType getBlockBiomeAt(BlockLocation location) {
		return getBlockBiomeAt(location.getX(), location.getY(), location.getZ());
	}

	public BiomeType getBlockBiomeAt(int x, int y, int z) {
		int index = z << 4 | x;
		if (index < 0 || index > biomes.length) {
			return null;
		}
		return BiomeType.getById(biomes[index] & 0xFF);
	}

	public void setBlockBiomeAt(BiomeType biome, BlockLocation location) {
		setBlockBiomeAt(biome, location.getX(), location.getY(), location.getZ());
	}

	public void setBlockBiomeAt(BiomeType biome, int x, int y, int z) {
		int index = z << 4 | x;
		if (index < 0 || index > biomes.length) {
			return;
		}
		biomes[index] = biome.getId();
	}

	public BlockLocation getBaseLocation() {
		return baseLocation;
	}
}
