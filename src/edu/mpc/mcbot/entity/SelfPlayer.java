package edu.mpc.mcbot.entity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ConnectException;
import java.net.Proxy;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.github.steveice10.mc.auth.exception.request.InvalidCredentialsException;
import com.github.steveice10.mc.auth.exception.request.RequestException;
import com.github.steveice10.mc.protocol.MinecraftConstants;
import com.github.steveice10.mc.protocol.MinecraftProtocol;
import com.github.steveice10.mc.protocol.data.SubProtocol;
import com.github.steveice10.mc.protocol.data.game.ClientRequest;
import com.github.steveice10.mc.protocol.data.game.entity.player.GameMode;
import com.github.steveice10.mc.protocol.data.message.Message;
import com.github.steveice10.mc.protocol.data.message.TranslationMessage;
import com.github.steveice10.mc.protocol.data.status.ServerStatusInfo;
import com.github.steveice10.mc.protocol.data.status.handler.ServerInfoHandler;
import com.github.steveice10.mc.protocol.data.status.handler.ServerPingTimeHandler;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.ClientRequestPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerAbilitiesPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientTeleportConfirmPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerHealthPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerSpawnPositionPacket;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;

import edu.mpc.mcbot.MCBot;
import edu.mpc.mcbot.Terminal;
import edu.mpc.mcbot.cli.AnsiColor;

public class SelfPlayer extends Player {

	private final static Logger logger = Logger.getLogger("");
	private Client client;

	public SelfPlayer(String username) {
		super(username);
	}

	public final boolean isConnected() {
		if (client != null) {
			if (client.getSession() != null) {
				return client.getSession().isConnected();
			}
		}
		return false;
	}

	public final synchronized void connect(String address, int port, Proxy proxy, String username, String password)
			throws ConnectException {

		if (client != null) {
			if (client.getSession() != null) {
				if (client.getSession().isConnected()) {
					logger.warning("Already connected!");
					return;
				}
			}
		}

		// Ping server first
		pingServer(address, port, proxy);

		// Attempt to authenticate with mojang
		final MinecraftProtocol protocol;
		if (!password.isEmpty()) {
			try {
				protocol = new MinecraftProtocol(username, password);
				logger.info(Terminal.colorize(AnsiColor.GREEN + "Successfully authenticated: " + AnsiColor.AQUA
						+ username + AnsiColor.RESET));
			} catch (InvalidCredentialsException e) {
				logger.warning("Invalid password!");
				return;
			} catch (RequestException e) {
				MCBot.getInstance().getTerminal().handleException(e);
				return;
			}
		} else {
			protocol = new MinecraftProtocol(username);
		}

		client = new Client(address, port, protocol, new TcpSessionFactory(proxy));
		client.getSession().setFlag(MinecraftConstants.AUTH_PROXY_KEY, proxy);
		client.getSession().addListener(new ClientSessionAdapter());
		client.getSession().connect(true);
	}

	public final synchronized void disconnect() {
		if (client.getSession().isConnected()) {
			client.getSession().disconnect("Finished");
		}
	}

	public void sendMessage(String message) {
		if (client != null) {
			if (client.getSession() != null) {
				if (client.getSession().isConnected()) {
					client.getSession().send(new ClientChatPacket(message));
				} else {
					logger.warning("Not connected to a server!");
				}
			}
		}
	}

	public void sendRotate(float yaw, float pitch) {
		if (client != null) {
			if (client.getSession() != null) {
				if (client.getSession().isConnected()) {

					setYaw(yaw);
					setPitch(pitch);

					client.getSession().send(new ClientPlayerRotationPacket(isOnGround(), getYaw(), getPitch()));
				} else {
					logger.warning("Not connected to a server!");
				}
			}
		}
	}
	
	public void centerPosition() {
		double dx = getX() > 0 ? Math.floor(getX()) + 0.5d : Math.round(getX()) - 0.5d;
		double dy = Math.floor(getY());
		double dz = getZ() > 0 ? Math.floor(getZ()) + 0.5d : Math.round(getZ()) - 0.5d;
		
		setX(dx);
		setY(dy);
		setZ(dz);
		
		client.getSession().send(new ClientPlayerPositionRotationPacket(isOnGround(), getX(), getY(), getZ(), getYaw(), getPitch()));
	}
	
	
	public void move(double dx, double dy, double dz) {
		double l = (getX() + dx) - getX();
		double h = (getY() + dy) - getY();
		double w = (getZ() + dz) - getZ();

		double c = Math.sqrt(l * l + w * w);
		double a1 = -Math.asin(l / c) / Math.PI * 180;
		double a2 = Math.acos(w / c) / Math.PI * 180;

		if (a2 > 90) {
			setYaw((float) (180 - a1));
		} else {
			setYaw((float) a1);
		}

		if (dx == 0 && dy == 0) {
			setYaw(0);
		}

		setPitch((float) Math.atan((h / c)));

		// PhaseBot.getConsole().println("p: " + pitch + " y:" + yaw);
		int steps = (int) ((int) 2.0 * Math.floor(Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2) + Math.pow(dz, 2))));
		double sx = dx / steps;
		double sy = dy / steps;
		double sz = dz / steps;

		for (int i = 0; i < steps; i++) {
			setX(getX() + sx);
			setY(getY() + sy);
			setZ(getZ() + sz);

			client.getSession().send(
					new ClientPlayerPositionRotationPacket(isOnGround(), getX(), getY(), getZ(), getYaw(), getPitch()));

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}

		// client.getSession().send(new ClientPlayerPositionPacket(isOnGround(), getX(),
		// getY(), getZ()));
	}

	public static final void pingServer(String address, int port, Proxy proxy) {
		MinecraftProtocol protocol = new MinecraftProtocol(SubProtocol.STATUS);
		Client statusClient = new Client(address, port, protocol, new TcpSessionFactory(proxy));

		statusClient.getSession().setFlag(MinecraftConstants.AUTH_PROXY_KEY, proxy);
		statusClient.getSession().setFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY, new ServerInfoHandler() {
			@Override
			public void handle(Session session, ServerStatusInfo info) {
				logger.info("Version: " + info.getVersionInfo().getVersionName() + ", "
						+ info.getVersionInfo().getProtocolVersion());
				logger.info("Player Count: " + info.getPlayerInfo().getOnlinePlayers() + " / "
						+ info.getPlayerInfo().getMaxPlayers());
				logger.finer("Players: " + Arrays.toString(info.getPlayerInfo().getPlayers()));
				logger.finer("Description: " + info.getDescription().getFullText());
				logger.finer("Icon: " + info.getIcon());
			}
		});
		statusClient.getSession().setFlag(MinecraftConstants.SERVER_PING_TIME_HANDLER_KEY, new ServerPingTimeHandler() {
			@Override
			public void handle(Session session, long pingTime) {
				logger.info("Response in: " + pingTime + "ms.");
			}
		});

		statusClient.getSession().setConnectTimeout(3);
		statusClient.getSession().connect(true);

		while (statusClient.getSession().isConnected()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				MCBot.getInstance().getTerminal().handleException(e);
			}
		}
	}

	private class ClientSessionAdapter extends SessionAdapter {

		protected ClientSessionAdapter() {

		}

		@Override
		public void packetReceived(PacketReceivedEvent event) {

			// On join server
			if (event.getPacket() instanceof ServerJoinGamePacket) {
				ServerJoinGamePacket packet = (ServerJoinGamePacket) event.getPacket();

				setEntityId(packet.getEntityId());
				setGamemode(packet.getGameMode());
				setOnGround(true);

				// TODO: add features
				packet.getWorldType();
				packet.getDifficulty();
				packet.getDimension();
				packet.getHardcore();

				logger.info(Terminal.colorize(AnsiColor.GREEN + "Successfully connected to server." + AnsiColor.RESET));

				final String joinMessage = MCBot.getInstance().getConfig().getJoinMessage();
				logger.info("Sending join message: " + joinMessage);
				event.getSession().send(new ClientChatPacket(joinMessage));
			}

			// Server Player Abilities
			else if (event.getPacket() instanceof ServerPlayerAbilitiesPacket) {
				ServerPlayerAbilitiesPacket packet = (ServerPlayerAbilitiesPacket) event.getPacket();

				setInvincible(packet.getInvincible());
				setCanFly(packet.getCanFly());
				setGamemode((packet.getCreative() ? GameMode.CREATIVE : GameMode.SURVIVAL));
				setFlySpeed(packet.getFlySpeed());
				setWalkSpeed(packet.getWalkSpeed());

				event.getSession().send(new ClientPlayerAbilitiesPacket(isInvincible(), getCanFly(), !isOnGround(),
						(getGamemode() == GameMode.CREATIVE ? true : false), getFlySpeed(), getWalkSpeed()));
			}

			// Server Player Position Rotation
			else if (event.getPacket() instanceof ServerPlayerPositionRotationPacket) {
				ServerPlayerPositionRotationPacket packet = (ServerPlayerPositionRotationPacket) event.getPacket();

				setX(packet.getX());
				setY(packet.getY());
				setZ(packet.getZ());
				setYaw(packet.getYaw());
				setPitch(packet.getPitch());

				event.getSession().send(new ClientTeleportConfirmPacket(packet.getTeleportId()));
				event.getSession().send(new ClientPlayerPositionRotationPacket(isOnGround(), getX(), getY(), getZ(),
						getYaw(), getPitch()));
			}

			// Update health record
			else if (event.getPacket() instanceof ServerPlayerHealthPacket) {
				ServerPlayerHealthPacket health = (ServerPlayerHealthPacket) event.getPacket();

				logger.info("[health] Hearts: " + health.getHealth() + " Food: " + health.getFood());

				if (health.getFood() <= 4) {
					event.getSession().send(new ClientChatPacket("I'm hungry"));
				}

				if (health.getHealth() < getHealth()) {
					if (health.getHealth() < 6.0F) {
						event.getSession().send(new ClientChatPacket("I have critical health!"));

						if (health.getHealth() == 0.0F) {
							System.out.println("[DEATH] I died at " + getPosition());

							try {
								Thread.sleep(1000L);
							} catch (InterruptedException e) {

							}
							event.getSession().send(new ClientRequestPacket(ClientRequest.RESPAWN));
						}

					} else {

						final String[] messages = new String[] {
							"Ouch!", "Ow!",
							"Hey!", "That hurts!",
							"That's not nice!"
						};

						final int i = new Random().nextInt(messages.length);
						event.getSession().send(new ClientChatPacket(messages[i]));
					}
				}

				setHealth(health.getHealth());
				setFood(health.getFood());
			}

			// Spawn position
			else if (event.getPacket() instanceof ServerSpawnPositionPacket) {
				ServerSpawnPositionPacket packet = (ServerSpawnPositionPacket) event.getPacket();
				setX(packet.getPosition().getX());
				setY(packet.getPosition().getY());
				setZ(packet.getPosition().getZ());

				// event.getSession().send(new ClientPlayerPositionPacket(bot.isOnGround(),
				// bot.getX(), bot.getY(), bot.getZ()));
			}

			// Chat message
			else if (event.getPacket() instanceof ServerChatPacket) {
				Message message = ((ServerChatPacket) event.getPacket()).getMessage();

				logger.info("[chat] " + message.getFullText());

				// TODO: Extract and parse player name and chat message
				// TODO: Prevent bot from spamming itself
				
				
				if (message.getFullText().contains("!exit")) {
					disconnect();
					MCBot.getInstance().shutdown();
				}

				if (message.getFullText().contains("!respawn")) {
					event.getSession().send(new ClientRequestPacket(ClientRequest.RESPAWN));
				}
				
				if (message.getFullText().contains("!load ")) {
					final String[] params = message.getFullText().split(Pattern.quote("!load "));
					
					if (params.length != 2) {
						sendMessage("Syntax: load <filename>");
					}
					
					File f = new File(params[1]);

					if (f.exists() && f.isFile() && f.getName().endsWith(".lua")) {
						try {
							String src = readFileAsString(f);
							sendMessage("Running " + f.getPath() + " ...");
							MCBot.getInstance().getLua().runScriptInSandbox(f.getPath(), src);
						} catch (IOException e) {
							MCBot.getInstance().getTerminal().handleException(e);
						}
					} else {
						sendMessage("Error: " + params[1] + " is not a lua script.");
					}
				}

				if (message.getFullText().contains("!rotate")) {
					client.getSession().send(new ClientChatPacket("Rotating ..."));
					Executors.newSingleThreadExecutor().submit(() -> {
						for (float f = 0; f < 360.0F; f += 10.0F) {
							setYaw(f);
							event.getSession().send(new ClientPlayerRotationPacket(isOnGround(), getYaw(), getPitch()));
							try {
								Thread.sleep(50);
							} catch (InterruptedException ex) {
								System.err.println("Interrupted");
							}
						}
						client.getSession().send(new ClientChatPacket("Done!"));
					});
				}

				else if (message instanceof TranslationMessage) {
					logger.info("Received Translation Components: "
							+ Arrays.toString(((TranslationMessage) message).getTranslationParams()));
				}
			}
		}

		@Override
		public void disconnected(DisconnectedEvent event) {
			logger.finer("Disconnected: " + Message.fromString(event.getReason()).getFullText());

			if (event.getCause() != null) {

				if (event.getCause() instanceof ConnectException) {
					logger.warning("Failed to connect to server! (" + event.getCause().getMessage() + ")");
					// MCBot.getInstance().shutdown();
					return;
				}

				logger.warning("Error while disconnecting: " + event.getReason());
				MCBot.getInstance().getTerminal().handleException(event.getCause());
			} else {
				logger.warning("Disconnected from server: " + Message.fromString(event.getReason()).getText());
			}
		}
	}
	
	
	private static final String readFileAsString(File f) throws IOException {
		StringBuffer fileData = new StringBuffer();
		BufferedReader reader = new BufferedReader(new FileReader(f));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
		}
		reader.close();
		return fileData.toString();
	}

}
