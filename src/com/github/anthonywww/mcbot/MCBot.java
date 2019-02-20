package com.github.anthonywww.mcbot;

import java.net.ConnectException;
import java.net.Proxy;
import java.util.logging.Level;

import com.github.anthonywww.mcbot.cli.AnsiColor;
import com.github.anthonywww.mcbot.cli.ICLICommand;
import com.github.anthonywww.mcbot.cli.ICLIEvent;
import com.github.anthonywww.mcbot.cli.ICLIInvalidCommand;
import com.github.anthonywww.mcbot.cli.commands.ExitCommand;
import com.github.anthonywww.mcbot.cli.commands.GotoCommand;
import com.github.anthonywww.mcbot.cli.commands.LoadCommand;
import com.github.anthonywww.mcbot.cli.commands.MoveForwardCommand;
import com.github.anthonywww.mcbot.cli.commands.RotateCommand;
import com.github.anthonywww.mcbot.cli.commands.SayCommand;
import com.github.anthonywww.mcbot.cli.commands.VoiceCommand;
import com.github.anthonywww.mcbot.entity.SelfPlayer;
import com.github.anthonywww.mcbot.lua.LuaSandbox;

public class MCBot {
	
	public static final String NAME = "MCBot";
	public static final String VERSION = "0.2.0";
	private static MCBot instance;
	private boolean running;
	
	private BotConfig config;
	private Terminal terminal;
	private SelfPlayer player;
	private LuaSandbox lua;
	
	public MCBot(BotConfig config) {
		
		if (instance != null) {
			return;
		}
		
		System.out.println("Initializing ...");
		
		this.config = config;
		
		// -- Initialize --
		terminal = new Terminal();
		terminal.setPrompt(Terminal.colorize(AnsiColor.GREEN + "> " + AnsiColor.RESET));
		terminal.setHistory(true);
		terminal.setLevel(config.getLogLevel());
		terminal.registerEvent(new ICLIEvent() {
			@Override
			public void userInterruptEvent() {
				terminal.print(Level.INFO, "Caught interrupt signal ^C.");
				shutdown();
			}
			@Override
			public void onReturnEvent(String line) {
				
			}
			@Override
			public void onCommandEvent(String command, String[] params, ICLICommand handler) {
				
			}
			@Override
			public void eofInterruptEvent() {
				terminal.print(Level.FINER, "Caught interrupt signal ^D use ^C to shutdown.");
			}
		});
		terminal.setInvalidCommandHandler(new ICLIInvalidCommand() {
			@Override
			public void invalidInvoke(String command, String[] params) {
				
			}
		});
		
		// Register CLI commands
		terminal.registerCommand(new ExitCommand());
		terminal.registerCommand(new VoiceCommand());
		terminal.registerCommand(new SayCommand());
		terminal.registerCommand(new LoadCommand());
		terminal.registerCommand(new MoveForwardCommand());
		//terminal.registerCommand(new GotoCommand());
		terminal.registerCommand(new RotateCommand());
		
		// Initialize the terminal
		terminal.initialize();
		terminal.print(Level.INFO, NAME + " v" + VERSION);
		terminal.print(Level.INFO, "Type 'exit' to shutdown the bot.");
		instance = this;
		running = true;
		
		
		// ---- Initialize Components ----
		lua = new LuaSandbox();
		player = new SelfPlayer(config.getUsername());
		
		
		// ---- Loop ----
		while (running) {
			try {
				
				if (!player.isConnected()) {
					player.connect(config.getServerAddress(), config.getServerPort(), Proxy.NO_PROXY, config.getUsername(), config.getPassword());
					Thread.sleep(3000);
				}
				
				Thread.yield();
			} catch (ConnectException e) {
				terminal.handleException(e);
			} catch (InterruptedException e) {}
		}
		
		
		
		
		// ---- Shutdown procedure ----
		player.disconnect();
		terminal.shutdown();
		
		System.exit(0);
	}
	
	/**
	 * Log a message to the console
	 * @param level
	 * @param msg
	 */
	public void log(Level level, String msg) {
		terminal.print(level, msg);
	}
	
	/**
	 * Get the current terminal instance
	 * @return
	 */
	public Terminal getTerminal() {
		return terminal;
	}
	
	/**
	 * Get the bot configuration
	 * @return
	 */
	public BotConfig getConfig() {
		return config;
	}
	
	/**
	 * Get the bot's player object
	 * @return
	 */
	public SelfPlayer getPlayer() {
		return player;
	}
	
	/**
	 * Get the current Lua Sandbox instance
	 * @return
	 */
	public LuaSandbox getLua() {
		return lua;
	}
	
	/**
	 * Shutdown the bot
	 */
	public synchronized final void shutdown() {
		running = false;
		terminal.print(Level.INFO, "Shutting down ...");
		STTManager.setEnabled(false);
		Thread.currentThread().interrupt();
	}
	
	/**
	 * Singleton method
	 * @return
	 */
	public static final MCBot getInstance() {
		return instance;
	}
	
}
