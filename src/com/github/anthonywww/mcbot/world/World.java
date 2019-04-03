package com.github.anthonywww.mcbot.world;

import com.github.anthonywww.mcbot.world.block.Block;
import com.github.anthonywww.mcbot.world.block.BlockLocation;
import com.github.anthonywww.mcbot.world.block.Chunk;
import com.github.anthonywww.mcbot.world.block.ChunkLocation;
import com.github.anthonywww.mcbot.world.block.TileEntity;
import com.github.anthonywww.mcbot.world.entity.Entity;
import com.github.anthonywww.mcbot.world.pathfinding.PathSearchProvider;

public interface World {

	public Block getBlockAt(int x, int y, int z);

	public Block getBlockAt(BlockLocation location);

	public int getBlockIdAt(int x, int y, int z);

	public int getBlockIdAt(BlockLocation location);

	public void setBlockIdAt(int id, int x, int y, int z);

	public void setBlockIdAt(int id, BlockLocation location);

	public int getBlockMetadataAt(int x, int y, int z);

	public int getBlockMetadataAt(BlockLocation location);

	public void setBlockMetadataAt(int metadata, int x, int y, int z);

	public void setBlockMetadataAt(int metadata, BlockLocation location);

	public TileEntity getTileEntityAt(int x, int y, int z);

	public TileEntity getTileEntityAt(BlockLocation location);

	public void setTileEntityAt(TileEntity tileEntity, int x, int y, int z);

	public void setTileEntityAt(TileEntity tileEntity, BlockLocation location);

	public Chunk getChunkAt(int x, int y, int z);

	public Chunk getChunkAt(ChunkLocation location);

	public Entity[] getEntities();

	public Entity getEntityById(int id);

	public void spawnEntity(Entity entity);

	public void despawnEntity(Entity entity);

	public Dimension getDimension();

	public Difficulty getDifficulty();

	public WorldType getType();

	public int getMaxHeight();

	public PathSearchProvider getPathFinder();

	public void setPathFinder(PathSearchProvider pathFinder) throws UnsupportedOperationException;

	public long getTime();

	public long getAge();

	public void destroy();

}
