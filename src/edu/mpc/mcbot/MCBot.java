package edu.mpc.mcbot;

import java.net.ConnectException;
import java.net.Proxy;
import java.util.logging.Level;

import edu.mpc.mcbot.cli.ICLICommand;
import edu.mpc.mcbot.cli.ICLIEvent;
import edu.mpc.mcbot.cli.ICLIInvalidCommand;
import edu.mpc.mcbot.cli.commands.ExitCommand;
import edu.mpc.mcbot.cli.commands.LoadCommand;
import edu.mpc.mcbot.cli.commands.MoveForwardCommand;
import edu.mpc.mcbot.cli.commands.RotateCommand;
import edu.mpc.mcbot.cli.commands.SayCommand;
import edu.mpc.mcbot.entity.SelfPlayer;
import edu.mpc.mcbot.lua.LuaSandbox;

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
		terminal.setPrompt("$> ");
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
		terminal.registerCommand(new SayCommand());
		terminal.registerCommand(new LoadCommand());
		terminal.registerCommand(new MoveForwardCommand());
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
	
	
	
	public Terminal getTerminal() {
		return terminal;
	}
	
	public BotConfig getConfig() {
		return config;
	}
	
	public SelfPlayer getPlayer() {
		return player;
	}
	
	public LuaSandbox getLua() {
		return lua;
	}
	
	
	
	
	
	
	public synchronized final void shutdown() {
		running = false;
		terminal.print(Level.INFO, "Shutting down ...");
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
