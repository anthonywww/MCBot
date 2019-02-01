package edu.mpc.mcbot;

import java.net.Proxy;
import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

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

import edu.mpc.mcbot.cli.AnsiColor;
import edu.mpc.mcbot.entity.Player;

public class MCClient {

	private final static Logger logger = Logger.getLogger("");
	private Client client;
	private Player bot;

	protected MCClient() {
		String username = MCBot.getInstance().getConfig().getUsername();
		String password = MCBot.getInstance().getConfig().getPassword();
		String address = MCBot.getInstance().getConfig().getServerAddress();
		int port = MCBot.getInstance().getConfig().getServerPort();

		// Get server status
		status(address, port, Proxy.NO_PROXY);

		// Login
		login(username, password, address, port, Proxy.NO_PROXY);
	}

	public void status(String address, int port, Proxy proxy) {
		logger.info("Pinging " + address + ":" + port + " ...");

		MinecraftProtocol protocol = new MinecraftProtocol(SubProtocol.STATUS);
		Client client = new Client(address, port, protocol, new TcpSessionFactory(proxy));
		client.getSession().setFlag(MinecraftConstants.AUTH_PROXY_KEY, proxy);
		client.getSession().setFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY, new ServerInfoHandler() {
			@Override
			public void handle(Session session, ServerStatusInfo info) {
				logger.info("Version: " + info.getVersionInfo().getVersionName() + ", " + info.getVersionInfo().getProtocolVersion());
				logger.info("Player Count: " + info.getPlayerInfo().getOnlinePlayers() + " / " + info.getPlayerInfo().getMaxPlayers());
				// logger.info("Players: " + Arrays.toString(info.getPlayerInfo().getPlayers()));
				// logger.info("Description: " + info.getDescription().getFullText());
				logger.info("Icon: " + info.getIcon());
			}
		});
		client.getSession().setFlag(MinecraftConstants.SERVER_PING_TIME_HANDLER_KEY, new ServerPingTimeHandler() {
			@Override
			public void handle(Session session, long pingTime) {
				logger.info("Response in: " + pingTime + "ms.");
			}
		});
		client.getSession().connect();
		while (client.getSession().isConnected()) {
			try {
				Thread.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void login(String username, String password, String address, int port, Proxy proxy) {
		logger.info("Logging in to " + address + ":" + port + " ...");

		MinecraftProtocol protocol = null;

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
		client.getSession().addListener(new SessionAdapter() {

			@Override
			public void packetReceived(PacketReceivedEvent event) {
				
				
				if (event.getPacket() instanceof ServerJoinGamePacket) {
					ServerJoinGamePacket packet = (ServerJoinGamePacket) event.getPacket();
					
					bot = new Player(MCBot.getInstance().getConfig().getUsername());
					
					bot.setEntityId(packet.getEntityId());
					bot.setGamemode(packet.getGameMode());
					bot.setOnGround(true);
					
					packet.getWorldType();

					logger.info(Terminal.colorize(AnsiColor.GREEN + "Successfully connected to server." + AnsiColor.RESET));

					final String joinMessage = MCBot.getInstance().getConfig().getJoinMessage();
					logger.info("Sending join message: " + joinMessage);
					event.getSession().send(new ClientChatPacket(joinMessage));
				}
				
				
				// Server Player Abilities
				else if (event.getPacket() instanceof ServerPlayerAbilitiesPacket) {
					ServerPlayerAbilitiesPacket packet = (ServerPlayerAbilitiesPacket) event.getPacket();
					
					bot.setInvincible(packet.getInvincible());
					bot.setCanFly(packet.getCanFly());
					bot.setGamemode((packet.getCreative() ? GameMode.CREATIVE : GameMode.SURVIVAL));
					bot.setFlySpeed(packet.getFlySpeed());
					bot.setWalkSpeed(packet.getWalkSpeed());
					
					event.getSession().send(new ClientPlayerAbilitiesPacket(bot.isInvincible(), bot.getCanFly(), !bot.isOnGround(), (bot.getGamemode() == GameMode.CREATIVE ? true : false), bot.getFlySpeed(), bot.getWalkSpeed()));
				}
				
				
				// Server Player Position Rotation
				else if (event.getPacket() instanceof ServerPlayerPositionRotationPacket) {
					ServerPlayerPositionRotationPacket packet = (ServerPlayerPositionRotationPacket) event.getPacket();
					
					bot.setX(packet.getX());
					bot.setY(packet.getY());
					bot.setZ(packet.getZ());
					bot.setYaw(packet.getYaw());
					bot.setPitch(packet.getPitch());
					
					event.getSession().send(new ClientTeleportConfirmPacket(packet.getTeleportId()));
					event.getSession().send(new ClientPlayerPositionRotationPacket(bot.isOnGround(), bot.getX(), bot.getY(), bot.getZ(), bot.getYaw(), bot.getPitch()));
				}
				
				
				// Update health record
				else if (event.getPacket() instanceof ServerPlayerHealthPacket) {
					ServerPlayerHealthPacket health = (ServerPlayerHealthPacket) event.getPacket();
					bot.setHealth(health.getHealth());
					bot.setFood(health.getFood());
				}


				else if (event.getPacket() instanceof ServerSpawnPositionPacket) {
					ServerSpawnPositionPacket packet = (ServerSpawnPositionPacket) event.getPacket();
					bot.setX(packet.getPosition().getX());
					bot.setY(packet.getPosition().getY());
					bot.setZ(packet.getPosition().getZ());
					
					//event.getSession().send(new ClientPlayerPositionPacket(bot.isOnGround(), bot.getX(), bot.getY(), bot.getZ()));
				}
				
				
				else if (event.getPacket() instanceof ServerChatPacket) {
					Message message = ((ServerChatPacket) event.getPacket()).getMessage();
					
					
					logger.info("[chat] " + message.getFullText());
					
					
					// Find how to get player name
					if (message.getFullText().contains("!exit")) {
						disconnect();
						MCBot.getInstance().shutdown();
					}

					if (message.getFullText().contains("!respawn")) {
						event.getSession().send(new ClientRequestPacket(ClientRequest.RESPAWN));
					}

					if (message.getFullText().contains("!rotate")) {
						client.getSession().send(new ClientChatPacket("Rotating ..."));
						Executors.newSingleThreadExecutor().submit(() -> {
							for (float f = 0; f < 360.0F; f += 10.0F) {
								bot.setYaw(f);
								event.getSession().send(new ClientPlayerRotationPacket(bot.isOnGround(), bot.getYaw(), bot.getPitch()));
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
						logger.info("Received Translation Components: " + Arrays.toString(((TranslationMessage) message).getTranslationParams()));
					}
				}
			}

			@Override
			public void disconnected(DisconnectedEvent event) {
				logger.info("Disconnected: " + Message.fromString(event.getReason()).getFullText());

				if (event.getCause() != null) {
					logger.warning("Error while disconnecting: " + event.getReason());
					MCBot.getInstance().getTerminal().handleException(event.getCause());
				}

			}

		});
		client.getSession().connect();
	}

	
	
	
	
	public synchronized void disconnect() {
		if (client.getSession().isConnected()) {
			client.getSession().disconnect("Finished");
		}
	}
	
	
	
	public synchronized void sendMessage(String message) {
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

	public synchronized void sendRotate(float yaw, float pitch) {
		if (client != null) {
			if (client.getSession() != null) {
				if (client.getSession().isConnected()) {
					bot.setYaw(yaw);
					bot.setPitch(pitch);
					
					client.getSession().send(new ClientPlayerRotationPacket(bot.isOnGround(), bot.getYaw(), bot.getPitch()));
				} else {
					logger.warning("Not connected to a server!");
				}
			}
		}
	}

}
