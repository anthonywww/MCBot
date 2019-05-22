package com.github.anthonywww.mcbot.world.entity;

import java.net.ConnectException;
import java.net.Proxy;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import com.github.anthonywww.mcbot.MCBot;
import com.github.anthonywww.mcbot.Terminal;
import com.github.anthonywww.mcbot.cli.AnsiColor;
import com.github.anthonywww.mcbot.cli.ICLICommand;
import com.github.anthonywww.mcbot.utils.MathHelper;
import com.github.anthonywww.mcbot.utils.Timer;
import com.github.anthonywww.mcbot.utils.Vector3d;
import com.github.anthonywww.mcbot.world.WorldLocation;
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
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.player.ClientPlayerRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.client.world.ClientTeleportConfirmPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerChatPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.ServerJoinGamePacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerAbilitiesPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerHealthPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.entity.player.ServerPlayerPositionRotationPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerChunkDataPacket;
import com.github.steveice10.mc.protocol.packet.ingame.server.world.ServerSpawnPositionPacket;
import com.github.steveice10.packetlib.Client;
import com.github.steveice10.packetlib.Session;
import com.github.steveice10.packetlib.event.session.DisconnectedEvent;
import com.github.steveice10.packetlib.event.session.PacketReceivedEvent;
import com.github.steveice10.packetlib.event.session.SessionAdapter;
import com.github.steveice10.packetlib.tcp.TcpSessionFactory;

public class SelfPlayer extends Player {

	private static final Logger logger = Logger.getLogger("");
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
	
	/**
	 * Connect to a Minecraft Server
	 * @param address
	 * @param port
	 * @param proxy
	 * @param username
	 * @param password
	 * @throws ConnectException
	 */
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
				logger.info(Terminal.colorize(AnsiColor.GREEN + "Successfully authenticated: " + AnsiColor.AQUA + username + AnsiColor.RESET));
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

		logger.info("Connecting to " + address + ":" + port + " ...");
		client = new Client(address, port, protocol, new TcpSessionFactory(proxy));
		client.getSession().setFlag(MinecraftConstants.AUTH_PROXY_KEY, proxy);
		client.getSession().addListener(new ClientSessionAdapter());
		client.getSession().connect(true);
	}

	/**
	 * Gracefully disconnect from the Minecraft Server
	 */
	public final synchronized void disconnect() {
		if (client.getSession().isConnected()) {
			client.getSession().disconnect("Finished");
		}
	}

	/**
	 * Send a chat message
	 * @param message
	 */
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

	private Vector3d getUnitVector(double angleYaw, double anglePitch) {
		final double dx = -Math.cos(anglePitch) * Math.sin(angleYaw);
		final double dy = -Math.sin(anglePitch);
		final double dz = Math.cos(anglePitch) * Math.cos(angleYaw);

		return new Vector3d(dx, dy, dz);
	}

	public void moveForward(double distance) {
		final Vector3d vec = getUnitVector(Math.toRadians(getYaw()), Math.toRadians(getPitch()));
		move(vec.getX() * distance, vec.getY() * distance, vec.getZ() * distance);
	}

	public void moveBackward(double distance) {
		moveForward(-distance);
	}

	public void moveLeft(double distance) {
		double yaw = getYaw() - 90;
		final Vector3d vec = getUnitVector(Math.toRadians(yaw), Math.toRadians(getPitch()));
		move(vec.getX() * distance, vec.getY() * distance, vec.getZ() * distance);
	}

	public void moveRight(double distance) {
		moveLeft(-distance);
	}

	public void move(double dx, double dy, double dz) {

		int steps = (int) (2.73f * (Math.sqrt(MathHelper.power(dx, 2) + MathHelper.power(dy, 2) + MathHelper.power(dz, 2))));
		double sx = dx / steps;
		//double sy = dy / steps;
		double sz = dz / steps;

		for (int i = 0; i < steps; i++) {
			setX(getX() + sx);
			// setY(getY() + sy);
			setZ(getZ() + sz);

			logger.finest(String.format("Moving X=%.2f Y=%.2f Z=%.2f (Yaw=%.2f Pitch=%.2f)", getX(), getY(), getZ(), getYaw(), getPitch()));
			client.getSession().send(new ClientPlayerPositionRotationPacket(isOnGround(), getX(), getY(), getZ(), getYaw(), getPitch()));

			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
			}
		}

		// FIXME: center the bot's position to the center of the block (0.5)

	}

	public void face(WorldLocation target) {
		face(target.getX(), target.getY(), target.getZ());
	}

	public void face(double x, double y, double z) {
		setYaw(getRotationX(x, y, z));
		setPitch(getRotationY(x, y, z));
	}

	private float getRotationX(double x, double y, double z) {
		double d = this.getX() - x;
		double d1 = this.getZ() - z;
		return (float) (((Math.atan2(d1, d) * 180D) / Math.PI) + 90) % 360;
	}

	private float getRotationY(double x, double y, double z) {
		double dis1 = y - (this.getY() + 1);
		double dis2 = Math.sqrt(MathHelper.power(x - this.getX(), 2) + MathHelper.power(z - this.getZ(), 2));
		return (float) ((Math.atan2(dis2, dis1) * 180.0) / Math.PI) - 90.0f;
	}

	public static final void pingServer(String address, int port, Proxy proxy) {
		MinecraftProtocol protocol = new MinecraftProtocol(SubProtocol.STATUS);
		Client statusClient = new Client(address, port, protocol, new TcpSessionFactory(proxy));

		statusClient.getSession().setFlag(MinecraftConstants.AUTH_PROXY_KEY, proxy);

		statusClient.getSession().setFlag(MinecraftConstants.SERVER_INFO_HANDLER_KEY, new ServerInfoHandler() {
			@Override
			public void handle(Session session, ServerStatusInfo info) {
				logger.info("Server Version: " + info.getVersionInfo().getVersionName() + ", " + info.getVersionInfo().getProtocolVersion());
				logger.info("Player Count: " + info.getPlayerInfo().getOnlinePlayers() + " / " + info.getPlayerInfo().getMaxPlayers());
				logger.info("Players: " + Arrays.toString(info.getPlayerInfo().getPlayers()));
				logger.info("Description: " + info.getDescription().getFullText());
				logger.fine("Icon: " + info.getIcon());
			}
		});

		statusClient.getSession().setFlag(MinecraftConstants.SERVER_PING_TIME_HANDLER_KEY, new ServerPingTimeHandler() {
			@Override
			public void handle(Session session, long pingTime) {
				logger.info("Server ping response " + pingTime + "ms.");
			}
		});

		statusClient.getSession().setConnectTimeout(3);
		statusClient.getSession().connect(true);
	}

	private class ClientSessionAdapter extends SessionAdapter {

		protected ClientSessionAdapter() {
			
		}

		@Override
		public void packetReceived(PacketReceivedEvent event) {

			// On join server
			if (event.getPacket() instanceof ServerJoinGamePacket) {
				ServerJoinGamePacket packet = (ServerJoinGamePacket) event.getPacket();

				setId(packet.getEntityId());
				setGamemode(packet.getGameMode());
				setOnGround(true);

				// TODO: add world handlers
				packet.getWorldType();
				packet.getDifficulty();
				packet.getDimension();
				packet.getHardcore();
				packet.getMaxPlayers();

				logger.info(Terminal.colorize(AnsiColor.GREEN + "Successfully connected to server." + AnsiColor.RESET));

				final String joinMessage = MCBot.getInstance().getConfig().getJoinMessage();
				if (joinMessage != null && !joinMessage.isEmpty()) {
					logger.info("Sending join message: " + joinMessage);
					event.getSession().send(new ClientChatPacket(joinMessage));
				}
			}

			// Server Player Abilities
			else if (event.getPacket() instanceof ServerPlayerAbilitiesPacket) {
				ServerPlayerAbilitiesPacket packet = (ServerPlayerAbilitiesPacket) event.getPacket();

				setInvincible(packet.getInvincible());
				setCanFly(packet.getCanFly());
				setGamemode((packet.getCreative() ? GameMode.CREATIVE : GameMode.SURVIVAL));
				setFlySpeed(packet.getFlySpeed());
				setWalkSpeed(packet.getWalkSpeed());

				event.getSession().send(new ClientPlayerAbilitiesPacket(isInvincible(), getCanFly(), !isOnGround(), (getGamemode() == GameMode.CREATIVE ? true : false), getFlySpeed(), getWalkSpeed()));
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
				event.getSession().send(new ClientPlayerPositionRotationPacket(isOnGround(), getX(), getY(), getZ(), getYaw(), getPitch()));
			}

			// Update health record
			else if (event.getPacket() instanceof ServerPlayerHealthPacket) {
				ServerPlayerHealthPacket health = (ServerPlayerHealthPacket) event.getPacket();

				if (health.getHealth() <= 0.0) {
					// Request respawn on death
					event.getSession().send(new ClientRequestPacket(ClientRequest.RESPAWN));
				}

				// Set local player health/food
				setHealth(health.getHealth());
				setFood(health.getFood());

				// Lua callback
				Timer.time("lua.callback.health");
				MCBot.getInstance().getLua().getLuaBot().healthCallback(getHealth(), getFood());
				Timer.time("lua.callback.health.end");
			}

			// Spawn position
			else if (event.getPacket() instanceof ServerSpawnPositionPacket) {
				ServerSpawnPositionPacket packet = (ServerSpawnPositionPacket) event.getPacket();
				setX(packet.getPosition().getX());
				setY(packet.getPosition().getY());
				setZ(packet.getPosition().getZ());

				event.getSession().send(new ClientPlayerPositionPacket(isOnGround(), getX(), getY(), getZ()));
			}

			// Chunk handling
			else if (event.getPacket() instanceof ServerChunkDataPacket) {
//				ServerChunkDataPacket packet = (ServerChunkDataPacket) event.getPacket();
//				final Chunk[] chunkSections = packet.getColumn().getChunks();
//				final int[] biomes = packet.getColumn().getBiomeData();
//				final int chunkX = packet.getColumn().getX();
//				final int chunkZ = packet.getColumn().getZ();
//				final CompoundTag[] tileEntities = packet.getColumn().getTileEntities();

				// worldX = (horizPos >> 4 & 15) + (chunkX * 16);
				// worldY = vertPos;
				// worldZ = (horizPos & 15) + (chunkZ * 16);
//				if (chunkX == 0 && chunkZ == 0) {
//					int hPos = 0;
//					int vPos = 0;
//					for (int chunkIndex = 0; chunkIndex < chunkSections.length; chunkIndex++) {
//						vPos = chunkIndex;
//						if (chunkSections[chunkIndex] != null) {
//							List<BlockState> states = chunkSections[chunkIndex].getBlocks().getStates();
//							for (int stateIndex = 0; stateIndex < states.size(); stateIndex++) {
//								hPos = stateIndex;
//								int x = (hPos >> 4 & 15) + (chunkX * 16);
//								int y = vPos;
//								int z = (hPos & 15) + (chunkZ * 16);
//								logger.finest(x + " " + y + " " + z + " -> " + MCBot.getInstance()
//										.getBlockRegistry().getBlockTypeFromInternalId(states.get(stateIndex).getId()));
//							}
//
//						}
//					}
//				}

			}

			// Chat message
			else if (event.getPacket() instanceof ServerChatPacket) {
				Message message = ((ServerChatPacket) event.getPacket()).getMessage();

				logger.info("[chat] " + message.getFullText());

				if (message instanceof TranslationMessage) {
					logger.finest("Received Translation Components: " + Arrays.toString(((TranslationMessage) message).getTranslationParams()));
				}

				String rawMessage = message.getFullText();
				String username = null;
				String text = "";
				String textParts[] = rawMessage.split(" ");

				// Parse username from message (VANILLA)
				if (textParts[0].startsWith("<") && textParts[0].endsWith(">")) {
					username = textParts[0].substring(1, textParts[0].length() - 1);
				}

				for (int i = 1; i < textParts.length; i++) {
					text += textParts[i];
					if (i != textParts.length - 1) {
						text += " ";
					}
				}

				text = text.trim();

				// Call chat callback method in lua sandbox
				Timer.time("lua.callback.rawchat");
				MCBot.getInstance().getLua().getLuaBot().rawChatCallback(text);
				Timer.time("lua.callback.rawchat.end");
				if (username != null) {
					Timer.time("lua.callback.chat");
					MCBot.getInstance().getLua().getLuaBot().chatCallback(username, text);
					Timer.time("lua.callback.chat.end");
				}

				// Built in commands
				for (String friend : MCBot.getInstance().getConfig().getFriends()) {
					// Allow all players access
					if (friend.equals("*")) {
						friend = username;
					}
					if (username != null && username.equalsIgnoreCase(friend) && !username.equals(MCBot.getInstance().getConfig().getUsername())) {
						if (text.startsWith(MCBot.getInstance().getConfig().getPrefix())) {
							for (ICLICommand command : MCBot.getInstance().getTerminal().getRegisteredCommands()) {
								String[] texts = text.split(Pattern.quote(" "));
								String textCmd = texts[0].substring(1);
								String textArgs[] = Arrays.copyOfRange(texts, 1, texts.length);
								if (command.commandName().equalsIgnoreCase(textCmd)) {
									command.invoke(textArgs);
									break;
								}
							}
						}
					}
				}

			}
		}

		@Override
		public void disconnected(DisconnectedEvent event) {
			logger.fine("Disconnected: " + Message.fromString(event.getReason()).getFullText());
			if (event.getCause() != null) {

				if (event.getCause() instanceof ConnectException) {
					logger.warning("Failed to connect to server! (" + event.getCause().getMessage() + ")");
					return;
				}

				logger.warning("Error while connected: " + event.getReason());
				MCBot.getInstance().getTerminal().handleException(event.getCause());
			} else {
				logger.info("Disconnected from server: " + Message.fromString(event.getReason()).getText());
			}
		}
	}

}
