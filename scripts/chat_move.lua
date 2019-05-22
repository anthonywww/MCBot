
local prefix = "Bot: "
local tellraw = true

-- Check if the server supports the "tellraw" command
local function checkServerTellraw()
  math.randomseed(os.time())
  local num = "trt" .. math.random()
  local command = "/tellraw " .. bot.getUsername() .. " {\"text\":\"" .. num .. "\"}"
  bot.sendMessage(command)
  bot.onRawMessage(function(msg)
    if msg == num then
      tellraw = true
      bot.onRawMessage(nil)
    end
  end)
end

-- Send chat messages
local function chat(message, color)
  if color == nil then
    color = ""
  end
  if tellraw then
    bot.sendMessage("/tellraw @a {\"text\":\"" .. prefix .. message .. "\", \"color\":\"" .. color .. "\"}")
  else
    bot.sendMessage(message)
  end
  bot.sleep(50)
end

-- Handle chat messages 
local function handleMessage(username, msg)

  -- Don't listen to myself!
  if (username == bot.getUsername()) then
    return
  end

  if msg == "stop" then
    chat("No longer listening. Thanks for playing!", "red")
    bot.onMessage(nil)
    return
  end
  
  for i=1, #msg do
    local c = msg:sub(i,i)
    if c == "f" then
      bot.moveForward(1)
    elseif c == "b" then
      bot.moveBackward(1)
    elseif c == "l" then
      bot.moveLeft(1)
    elseif c == "r" then
      bot.moveRight(1)
    elseif c == "<" then
      bot.rotateYaw(bot.getYaw() - 90)
    elseif c == ">" then
      bot.rotateYaw(bot.getYaw() + 90)
    elseif c == "^" then
      if bot.getPitch() > -90 then
        bot.rotatePitch(bot.getPitch() - 90)
      end
    elseif c == "_" then
      if bot.getPitch() < 90 then
        bot.rotatePitch(bot.getPitch() + 90)
      end
    end
    bot.sleep(200)
  end
  
end

bot.sendMessage("Please wait ...")
checkServerTellraw()
bot.sleep(500)

bot.onMessage(handleMessage)
chat("Now listening!", "green")
chat("Type 'f' for Forward or 'b' for Backward", "yellow")
chat("Type 'l' for Left or 'r' for Right", "yellow")
chat("Type '<' to look left or '>' for to look right", "yellow")
chat("Type '^' to look up or '_' for to look down", "yellow")
chat("Type 'stop' to stop listening!", "green")