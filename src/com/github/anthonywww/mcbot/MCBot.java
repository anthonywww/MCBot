package com.github.anthonywww.mcbot;

import java.net.ConnectException;
import java.net.Proxy;
import java.util.logging.Level;

import com.github.anthonywww.mcbot.cli.AnsiColor;
import com.github.anthonywww.mcbot.cli.ICLICommand;
import com.github.anthonywww.mcbot.cli.ICLIEvent;
import com.github.anthonywww.mcbot.cli.ICLIInvalidCommand;
import com.github.anthonywww.mcbot.cli.commands.ExitCommand;
import com.github.anthonywww.mcbot.cli.commands.LoadCommand;
import com.github.anthonywww.mcbot.cli.commands.MoveForwardCommand;
import com.github.anthonywww.mcbot.cli.commands.RotateCommand;
import com.github.anthonywww.mcbot.cli.commands.SayCommand;
import com.github.anthonywww.mcbot.cli.commands.VoiceCommand;
import com.github.anthonywww.mcbot.event.EventBus;
import com.github.anthonywww.mcbot.lua.LuaSandbox;
import com.github.anthonywww.mcbot.world.entity.SelfPlayer;

public class MCBot {
	
	public static final String NAME = "MCBot";
	public static final String VERSION = "0.2.0";
	private static MCBot instance;
	private boolean running;
	
	private BotConfig config;
	private Terminal terminal;
	private SelfPlayer player;
	private LuaSandbox lua;
	private ArduinoStatus arduino;
	private EventBus eventBus;
	
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
		arduino = new ArduinoStatus();
		arduino.setColor(0, 1, 0);
		
		try {
			arduino.setColor(0, 1, 1);
			Thread.sleep(100);
			arduino.setColor(1, 0, 1);
			Thread.sleep(100);
			arduino.setColor(1, 1, 0);
			Thread.sleep(100);
			arduino.setColor(1, 1, 1);
			Thread.sleep(100);
			arduino.setColor(1, 0, 0);
			Thread.sleep(100);
			arduino.setColor(0, 0, 1);
		} catch (InterruptedException e) {}
		
		arduino.setBlinkingColor(0, 128, 0, 400);
		
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
		arduino.shutdown();
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
	
	
	public EventBus getEventBus() {
		return eventBus;
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
	 * Get the current running instance of MCBot
	 * @return
	 */
	public static final MCBot getInstance() {
		return instance;
	}
	
}
