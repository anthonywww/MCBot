package com.github.anthonywww.mcbot.cli.commands;

import java.util.Arrays;
import java.util.logging.Logger;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.cli.ICLICommand;
import com.github.anthonywww.mcbot.world.block.Chunk;

public class ChunkCommand implements ICLICommand {

	private static final Logger logger = Logger.getLogger("");
	
	@Override
	public void invoke(String[] params) {
		
		// Get current chunk column info
		if (params.length == 0) {
			
		}
		
		// Get chunk column info at [x,z]
		if (params.length == 2) {
			try {
				final int x = Integer.parseInt(params[0]);
				final int z = Integer.parseInt(params[1]);
				final Chunk[] chunks = new Chunk[16];
				for (int y=0; y<16; y++) {
					Chunk chunk = MCBot.getInstance().getPlayer().getWorld().getChunkAt(x, y, z);
					chunks[y] = chunk;
					logger.info(String.format("Chunk[%d,%d,%d]: ", x, y, z, Arrays.toString(chunk.getBlocks())));
				}
				
				
				
			} catch (NumberFormatException e) {}
		}
		
		logger.warning("Usage: " + commandName() + " [x,z]");
	}
	
	@Override
	public String commandName() {
		return "chunk";
	}
	
	@Override
	public String commandDescription() {
		return "Chunk information";
	}
	
	@Override
	public boolean caseSensitive() {
		return false;
	}
	
	@Override
	public boolean addToCompleter() {
		return true;
	}
	
	
}
