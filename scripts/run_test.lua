
local function turn()
  bot.moveForward(4)
  bot.sleep(100)
  bot.rotateYaw(bot.getYaw()+90)
end

for i=1, 4 do
  turn()
end

bot.sendMessage("Done!")