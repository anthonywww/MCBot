package edu.mpc.mcbot;

import java.util.logging.Level;

import edu.mpc.mcbot.cli.ICLICommand;
import edu.mpc.mcbot.cli.ICLIEvent;
import edu.mpc.mcbot.cli.ICLIInvalidCommand;
import edu.mpc.mcbot.cli.commands.LoadCommand;
import edu.mpc.mcbot.cli.commands.SayCommand;
import edu.mpc.mcbot.lua.LuaSandbox;

public class MCBot {
	
	public static final String NAME = "MCBot";
	public static final String VERSION = "0.1.0";
	private static MCBot instance;
	private boolean running;
	
	private BotConfig config;
	private Terminal terminal;
	private MCClient client;
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
			public void onReturnEvent(String line) {}
			@Override
			public void onCommandEvent(String command, String[] params, ICLICommand handler) {}
			@Override
			public void eofInterruptEvent() {
				terminal.print(Level.INFO, "Caught interrupt signal ^D use ^C to shutdown.");
			}
		});
		terminal.setInvalidCommandHandler(new ICLIInvalidCommand() {
			@Override
			public void invalidInvoke(String command, String[] params) {
				
			}
		});
		terminal.registerCommand(new ICLICommand() {
			@Override
			public void invoke(String[] params) {
				shutdown();
			}
			
			@Override
			public String commandName() {
				return "exit";
			}
			
			@Override
			public String commandDescription() {
				return "Shutdown the bot.";
			}
			
			@Override
			public boolean caseSensitive() {
				return false;
			}
			
			@Override
			public boolean addToCompleter() {
				return true;
			}
		});
		
		// Register CLI commands
		terminal.registerCommand(new SayCommand());
		terminal.registerCommand(new LoadCommand());
		
		terminal.initialize();
		terminal.print(Level.INFO, NAME + " v" + VERSION);
		instance = this;
		running = true;
		
		// ---- Initialize Components ----
		
		lua = new LuaSandbox();
		
		client = new MCClient();
		
		
		
		
		// ---- Loop ----
		while (running) {
			
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {}
			
		}
		
		
		
		
		// ---- Shutdown procedure ----
		
		client.disconnect();
		
		terminal.shutdown();
		
		System.exit(0);
	}
	
	
	
	public Terminal getTerminal() {
		return terminal;
	}
	
	public BotConfig getConfig() {
		return config;
	}
	
	public MCClient getClient() {
		return client;
	}
	
	public LuaSandbox getLua() {
		return lua;
	}
	
	
	
	
	
	
	public synchronized final void shutdown() {
		running = false;
		terminal.print(Level.INFO, "Shutting down ...");
	}
	
	/**
	 * Singleton method
	 * @return
	 */
	public static final MCBot getInstance() {
		return instance;
	}
	
}
