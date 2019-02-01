# MC Bot - A CLI Minecraft 1.12 Bot

A scriptable Minecraft bot with a lot of neat features. 

## Features

This bot has a lot of neat features:

- Ability to load and execute lua scripts gives you a lot of power and control
  over the bot via the Lua programming language without overhead complexity.
- A* path finding integration for tracking players and being aware of the surroundings.
- In game chat commands can be issued by friends of the bot to run simple tasks.

## Commands

This bot can be commanded and controlled using in-game commands
as well as CLI commands. Here's a list of all the commands that
are available. Required parameters are denoted as **<required>**
while optional parameters are denoted as **[optional]**.

- **!say <message>** - Send *message* to the chat. This can be prefixed with a **/** to issue a server command.
- **!load <path>** - Load and execute a lua script.
- **!forward [blocks]** - Walk *n* number of blocks forward relative to which way the bot is facing. If no parameter is provided, the default number of blocks is one.
- **!left [blocks]** - Same as `!forward` but walking to the left.
- **!right [blocks]** - Same as `!forward` but walking to the right.
- **!backward [blocks]** - Same as `!forward` but walking backwards.
- **!goto <player>** - Find *player* and walk to their coordinates.
- **!goto <x> <y> <z>** - Go to the coordinates *x*, *y*, *z* in the world.

## Scripts

If you would like to automate the bot with pre-defined scripts,
you can create a `script.lua` file in the same directory as the
executable and pass in `--script script.lua script2.lua` to
automatically load and execute the scripts when the bot connects. Or
you can issue the in game command `!load script.lua` which will
load and execute a script on command.

An example script may look like:

```lua
-- Send the message "Hello World" to chat
sendMessage("Hello World!")

function moveTurn()
	-- Move forward 3 blocks
	forward(3)
	
	-- Turn 90 degrees
	turn(90)

	-- Wait 2000 milliseconds (2 seconds)
	wait(2000)
end

for i=1, i<3 do
	moveTurn()
end
```

## License

[MIT License](./LICENSE).
