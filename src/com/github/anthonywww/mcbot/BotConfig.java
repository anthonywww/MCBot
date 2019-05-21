package com.github.anthonywww.mcbot;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name=MCBot.NAME, mixinStandardHelpOptions=false, version=MCBot.VERSION, helpCommand=true)
public final class BotConfig implements Runnable {

	@Option(names={"-h","--help", "-?"}, help=true, description="Show " + MCBot.NAME + " CLI arguments.", usageHelp=true)
	private boolean helpRequested;
	
	private Level logLevel;
	private String username;
	private String password;
	private String serverAddress;
	private int serverPort;
	private String prefix;
	private List<String> friends;
	private String joinMessage;
	private int respawnDelay;
	private double maxFollowRange;
	private double minFollowRange;

	public BotConfig() {
		this.logLevel = Level.FINEST;
		this.username = "MCBot";
		this.password = "";
		this.serverAddress = "127.0.0.1";
		this.serverPort = 25565;
		this.prefix = "!";
		this.friends = new ArrayList<String>();
		this.joinMessage = "";
		this.maxFollowRange = 300.0;
		this.minFollowRange = 1.0;
	}
	
	public Level getLogLevel() {
		return logLevel;
	}

	@Option(names={"-l", "--level"}, description="Set the console log level (FINEST, FINER, FINE, INFO, WARNING, SEVERE).", arity="1", paramLabel="<logLevel>")
	private void setLogLevel(String name) {
		setLogLevel(Level.parse(name));
	}
	
	public void setLogLevel(Level logLevel) {
		this.logLevel = logLevel;
	}

	public String getUsername() {
		return username;
	}

	@Option(names={"-u", "--username"}, description="Set bot username.", arity="1", paramLabel="<username>")
	public void setUsername(String username) {
		if (username.equalsIgnoreCase("bob")) {
			final String err = "setUsername() Username cannot be \"bob\".";
			System.err.println(err);
			throw new IllegalArgumentException(err);
		}
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	@Option(names={"-P", "--password"}, description="Set bot password (authenticate with Mojang).", arity="1", paramLabel="<password>")
	public void setPassword(String password) {
		this.password = password;
	}

	public String getServerAddress() {
		return serverAddress;
	}

	@Option(names={"-s", "--server"}, description="Connect to server IP Address or FQDN.", arity="1", paramLabel="<host>")
	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress.trim();
	}

	public int getServerPort() {
		return serverPort;
	}

	@Option(names={"-p", "--port"}, description="Connect to this server port.", arity="1", paramLabel="<port>")
	public void setServerPort(int serverPort) {
		if (serverPort < 80 || serverPort > 65536) {
			final String err = "setServerPort() must be between 80 and 65536.";
			System.err.println(err);
			throw new IllegalArgumentException(err);
		}
		this.serverPort = serverPort;
	}

	public String getPrefix() {
		return prefix;
	}

	@Option(names={"-x", "--prefix"}, description="Set bot command prefix.", arity="1", paramLabel="<prefix>")
	public void setPrefix(String prefix) {
		this.prefix = prefix.trim();
	}

	public List<String> getFriends() {
		return friends;
	}
	
	@Option(names={"-f", "--friend"}, description="Set bot friend list.", arity="1..*", paramLabel="<friend>")
	public void addFriend(String username) {
		if (username.length() >= 30) {
			final String err = "addFriend() must be less than 30 characters.";
			System.err.println(err);
			throw new IllegalArgumentException(err);
		}
		this.friends.add(username.trim());
	}
	
	public void removeFriend(String username) {
		if (friends.contains(username)) {
			friends.remove(username);
		}
	}

	public String getJoinMessage() {
		return joinMessage;
	}

	@Option(names={"-j", "--joinmsg"}, description="Set bot join message.", arity="1", paramLabel="<joinmessage>")
	public void setJoinMessage(String joinMessage) {
		if (joinMessage.trim().length() > 100) {
			final String err = "setJoinMessage() must be less than 100 characters.";
			System.err.println(err);
			throw new IllegalArgumentException(err);
		}
		this.joinMessage = joinMessage.trim();
	}

	public int getRespawnDelay() {
		return respawnDelay;
	}

	public void setRespawnDelay(int respawnDelay) {
		if (respawnDelay < 0) {
			final String err = "setRespawnDelay() must be greater than or equal to 0.";
			System.err.println(err);
			throw new IllegalArgumentException(err);
		}
		this.respawnDelay = respawnDelay;
	}

	public double getMaxFollowRange() {
		return maxFollowRange;
	}

	public void setMaxFollowRange(double maxFollowRange) {
		if (respawnDelay <= 0.0) {
			final String err = "setMaxFollowRange() must be greater than 0.0.";
			System.err.println(err);
			throw new IllegalArgumentException("setMaxFollowRange() must be greater than 0.0.");
		}
		this.maxFollowRange = maxFollowRange;
	}

	public double getMinFollowRange() {
		return minFollowRange;
	}

	public void setMinFollowRange(double minFollowRange) {
		if (respawnDelay < 0.0) {
			final String err = "setMinFollowRange() must be greater than or equal to 0.0.";
			System.err.println(err);
			throw new IllegalArgumentException(err);
		}
		this.minFollowRange = minFollowRange;
	}

	@Override
	public void run() {
		new MCBot(this);
	}
	

}
