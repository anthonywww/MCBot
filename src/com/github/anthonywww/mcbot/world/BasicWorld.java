package com.github.anthonywww.mcbot.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.event.EventBus;
import com.github.anthonywww.mcbot.event.EventListener;
import com.github.anthonywww.mcbot.world.block.Block;
import com.github.anthonywww.mcbot.world.block.BlockLocation;
import com.github.anthonywww.mcbot.world.block.Chunk;
import com.github.anthonywww.mcbot.world.block.ChunkLocation;
import com.github.anthonywww.mcbot.world.block.TileEntity;
import com.github.anthonywww.mcbot.world.entity.Entity;
import com.github.anthonywww.mcbot.world.entity.LivingEntity;
import com.github.anthonywww.mcbot.world.pathfinding.EuclideanHeuristic;
import com.github.anthonywww.mcbot.world.pathfinding.PathSearchProvider;
import com.github.anthonywww.mcbot.world.pathfinding.astar.AStarPathSearchProvider;

public class BasicWorld implements World, EventListener {
	
	private final WorldType type;
	private final Dimension dimension;
	private final Difficulty difficulty;
	private final int height;
	private final Map<ChunkLocation, Chunk> chunks;
	private final List<Entity> entities;
	private PathSearchProvider pathFinder;
	private long time, age;
	
	public BasicWorld(WorldType type, int height, Dimension dimension, Difficulty difficulty) {
		this.type = type;
		this.height = height;
		this.dimension = dimension;
		this.difficulty = difficulty;
		chunks = new HashMap<ChunkLocation, Chunk>();
		entities = new ArrayList<Entity>();
		pathFinder = new AStarPathSearchProvider(new EuclideanHeuristic(), new SimpleWorldPhysics(this));
		EventBus eventBus = MCBot.getInstance().getEventBus();
		eventBus.register(this);
	}
	
	@Override
	public Block getBlockAt(int x, int y, int z) {
		return null;
	}

	@Override
	public Block getBlockAt(BlockLocation location) {
		return null;
	}
	
	@Override
	public void setBlock(Block block) {
		ChunkLocation chunkLocation = new ChunkLocation(block.getLocation());
		Chunk chunk = getChunkAt(chunkLocation);
		if (chunk == null) {
			return;
		}
		chunk.setBlock(block);
	}

	@Override
	public int getBlockMetadataAt(int x, int y, int z) {
		return getBlockMetadataAt(new BlockLocation(x, y, z));
	}

	@Override
	public int getBlockMetadataAt(BlockLocation blockLocation) {
		ChunkLocation chunkLocation = new ChunkLocation(blockLocation);
		Chunk chunk = getChunkAt(chunkLocation);
		if (chunk == null) {
			return -1;
		}
		return chunk.getBlockAt(blockLocation.getX(), blockLocation.getY(), blockLocation.getZ()).getMetadata();
	}

	@Override
	public void setBlockMetadataAt(int metadata, int x, int y, int z) {
		
		
		
	}

	@Override
	public void setBlockMetadataAt(int metadata, BlockLocation location) {
		setBlockMetadataAt(metadata, location.getX(), location.getY(), location.getZ());
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
		return dimension;
	}

	@Override
	public Difficulty getDifficulty() {
		return difficulty;
	}

	@Override
	public WorldType getType() {
		return type;
	}

	@Override
	public int getMaxHeight() {
		return height;
	}

	@Override
	public PathSearchProvider getPathFinder() {
		return pathFinder;
	}

	@Override
	public void setPathFinder(PathSearchProvider pathFinder) throws UnsupportedOperationException {
		// TODO Auto-generated method stub
	}

	@Override
	public long getTime() {
		return time;
	}

	@Override
	public long getAge() {
		return age;
	}

	@Override
	public void destroy() {
		EventBus eventBus = MCBot.getInstance().getEventBus();
		eventBus.unregister(this);
		synchronized (entities) {
			for (Entity entity : entities) {
				if (entity instanceof LivingEntity) {
					((LivingEntity) entity).setDead(true);
				}
			}
			entities.clear();
		}
		synchronized (chunks) {
			chunks.clear();
		}
		System.gc();
	}

}
