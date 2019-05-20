package com.github.anthonywww.mcbot;

import java.net.ConnectException;
import java.net.Proxy;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;

import javax.naming.InvalidNameException;

import com.github.anthonywww.mcbot.cli.AnsiColor;
import com.github.anthonywww.mcbot.cli.ICLICommand;
import com.github.anthonywww.mcbot.cli.ICLIEvent;
import com.github.anthonywww.mcbot.cli.ICLIInvalidCommand;
import com.github.anthonywww.mcbot.cli.commands.ExitCommand;
import com.github.anthonywww.mcbot.cli.commands.GotoCommand;
import com.github.anthonywww.mcbot.cli.commands.HelpCommand;
import com.github.anthonywww.mcbot.cli.commands.LoadCommand;
import com.github.anthonywww.mcbot.cli.commands.MoveCommand;
import com.github.anthonywww.mcbot.cli.commands.RotateCommand;
import com.github.anthonywww.mcbot.cli.commands.SayCommand;
import com.github.anthonywww.mcbot.event.EventBus;
import com.github.anthonywww.mcbot.lua.LuaSandbox;
import com.github.anthonywww.mcbot.task.Task;
import com.github.anthonywww.mcbot.utils.Timer;
import com.github.anthonywww.mcbot.utils.Timer.Profile;
import com.github.anthonywww.mcbot.world.entity.SelfPlayer;

public class MCBot {
	
	public static final String NAME = "MCBot";
	public static final String VERSION = "0.2.2";
	private static MCBot instance;
	private boolean running;
	
	private BotConfig config;
	private Terminal terminal;
	private SelfPlayer player;
	private LuaSandbox lua;
	//private ArduinoStatus arduino;
	private Queue<Task> tasks;
	private EventBus eventBus;
	
	public MCBot(BotConfig config) {
		if (instance != null) {
			return;
		}
		
		Timer.time("core.init");
		System.out.println("Initializing ...");
		this.config = config;
		
		// -- Initialize --
		Timer.time("core.init.terminal.events");
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
		Timer.time("core.init.terminal.commands");
		terminal.registerCommand(new ExitCommand());
		terminal.registerCommand(new HelpCommand());
		terminal.registerCommand(new SayCommand());
		terminal.registerCommand(new LoadCommand());
		terminal.registerCommand(new MoveCommand());
		terminal.registerCommand(new RotateCommand());
		terminal.registerCommand(new GotoCommand());
		
		// Initialize the terminal
		terminal.initialize();
		terminal.print(Level.INFO, NAME + " v" + VERSION);
		terminal.print(Level.INFO, Terminal.colorize("Type '§ehelp§r' for a list of commands, or type '§eexit§r' to shutdown the bot."));
		instance = this;
		running = true;
		
		
		// ---- Initialize Components ----
		Timer.time("core.init.components");
		tasks = new PriorityQueue<Task>();
		lua = new LuaSandbox();
		player = new SelfPlayer(config.getUsername());
		
		
		terminal.print(Level.INFO, "Friends: " + Arrays.toString(config.getFriends().toArray()));
		
		// TODO: Load blocks from resources/assets/blocks.json
		// TODO: Load blocks from resources/assets/items.json
		
		Timer.time("core.loop");
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
		Timer.time("shutdown");
		
		// FIXME: temporary
		System.out.println("+ PROFILER STATS");
		System.out.println("| Name | Delta");
		for (Profile p : Timer.getProfiles()) {
			try {
				System.out.println(String.format("| %s | %.2fms", p.getName(), Timer.getTimeInMillis(p.getName())));
			} catch (InvalidNameException e) {
				e.printStackTrace();
			}
		}
		System.out.println("+");
		
		//arduino.shutdown();
		player.disconnect();
		terminal.shutdown();
		
		//Thread.currentThread().interrupt();
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
	}
	
	/**
	 * Get the current running instance of MCBot
	 * @return
	 */
	public static final MCBot getInstance() {
		return instance;
	}
	
}
