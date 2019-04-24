package com.github.anthonywww.mcbot.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.anthonywww.mcbot.event.EventListener;
import com.github.anthonywww.mcbot.world.block.Block;
import com.github.anthonywww.mcbot.world.block.BlockLocation;
import com.github.anthonywww.mcbot.world.block.Chunk;
import com.github.anthonywww.mcbot.world.block.ChunkLocation;
import com.github.anthonywww.mcbot.world.block.TileEntity;
import com.github.anthonywww.mcbot.world.entity.Entity;
import com.github.anthonywww.mcbot.world.pathfinding.EuclideanHeuristic;
import com.github.anthonywww.mcbot.world.pathfinding.PathSearchProvider;
import com.github.anthonywww.mcbot.world.pathfinding.astar.AStarPathSearchProvider;

public class DefaultWorld implements World, EventListener {
	
	private final WorldType type;
	private final Dimension dimension;
	private final Difficulty difficulty;
	private final int height;
	private final Map<ChunkLocation, Chunk> chunks;
	private final List<Entity> entities;
	private PathSearchProvider pathFinder;
	private long time, age;
	
	public DefaultWorld(WorldType type, Dimension dimension, Difficulty difficulty, int height) {
		this.type = type;
		this.dimension = dimension;
		this.difficulty = difficulty;
		this.height = height;
		chunks = new HashMap<ChunkLocation, Chunk>();
		entities = new ArrayList<Entity>();
		pathFinder = new AStarPathSearchProvider(new EuclideanHeuristic(), new SimpleWorldPhysics(this));
	}
	
	
	@Override
	public Block getBlockAt(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block getBlockAt(BlockLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBlockIdAt(int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBlockIdAt(BlockLocation location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBlockIdAt(int id, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlockIdAt(int id, BlockLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getBlockMetadataAt(int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBlockMetadataAt(BlockLocation location) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setBlockMetadataAt(int metadata, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setBlockMetadataAt(int metadata, BlockLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TileEntity getTileEntityAt(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TileEntity getTileEntityAt(BlockLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setTileEntityAt(TileEntity tileEntity, int x, int y, int z) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setTileEntityAt(TileEntity tileEntity, BlockLocation location) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Chunk getChunkAt(int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Chunk getChunkAt(ChunkLocation location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity[] getEntities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Entity getEntityById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void spawnEntity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void despawnEntity(Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension getDimension() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Difficulty getDifficulty() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorldType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getMaxHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public PathSearchProvider getPathFinder() {
		
		return null;
	}

	@Override
	public void setPathFinder(PathSearchProvider pathFinder) throws UnsupportedOperationException {
		
	}

	@Override
	public long getTime() {
		return 0;
	}

	@Override
	public long getAge() {
		return 0;
	}

	@Override
	public void destroy() {
		
	}

}
