# MC Bot - A Programmable Minecraft 1.13 Bot

A Lua programmable Minecraft Bot for 1.13 servers.

Minecraft is a open-world voxel sandbox game with both single-player and multi-player capabilities.
Standard gameplay involves players surviving hostile creatures in game by building shelters and crafting
items in game with an optional route to "complete" the game.

## Features

This bot has a lot of neat features:

- Ability to be run on headless (CLI) servers, such as Debian Linux.
- Ability to load and execute Lua scripts gives you a lot of power and control
  over the bot via the Lua programming language without the worry of overhead syntax complexity.
- A* path finding integration for tracking players and being aware of the the world around it.
- In game chat commands can be issued by friends of the bot to run simple tasks.

## Commands

This bot can be commanded and controlled using in-game commands
as well as CLI commands. Here's a list of all the commands that
are available. Required parameters are denoted as **<required>**
while optional parameters are denoted as **[optional]**.

- `!say <message>` - Send *message* to the chat. This can be prefixed with a forward-slash (/) to issue a server command.
- `!load <path>` - Load and execute a lua script.
- `!forward [blocks]` - Walk *n* number of blocks forward relative to which way the bot is facing. If no parameter is provided, the default number of blocks is one.
- `!left [blocks]` - Same as `!forward` but strafing to the left.
- `!right [blocks]` - Same as `!forward` but strafing to the right.
- `!backward [blocks]` - Same as `!forward` but walking backwards.
- `!goto <player>` - Find *player* and walk to their coordinates.
- `!goto <x> <y> <z>` - Go to the coordinates *x*, *y*, *z* in the world.

## Scripting

If you would like to automate the bot with pre-defined scripts,
you can create a `script.lua` file in the same directory as the
executable and pass in `--script script.lua script2.lua` to
automatically load and execute the scripts when the bot connects. Or
you can issue the in game command `!load script.lua` which will
load and execute a script on command.

An example script might look something like:

```lua
-- Send the message "Hello World" to chat
bot.sendMessage("Hello World!")

local function moveTurn()
	-- Move forward 3 blocks
	bot.moveForward(3)
	
	bot.rotateYaw(90)
end

for i=1, i<4 do
	-- Call function moveTurn()
	moveTurn()
	
	-- Wait 1000 milliseconds (1 second)
	bot.wait(1000)
end
```

## Building and Launching

This project can be compiled using `./build.sh` which requires Maven.

To run this project, simply execute `./launch.sh` after building the project.


## License

[MIT License](./LICENSE).
