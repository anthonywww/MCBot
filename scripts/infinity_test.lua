
bot.sendMessage("Running infinity test ...")

local amp = 0.5

-- ) top-to-mid
for i=0, 180, 20 do
  bot.rotateYaw(i)
  bot.moveForward(amp)
end

-- U mid-to-bottom-to-mid
for i=180, -180, -20 do
  bot.rotateYaw(i)
  bot.moveForward(amp)
end

-- ( -- mid-to-top
for i=-180, 0, 20 do
  bot.rotateYaw(i)
  bot.moveForward(amp)
end

bot.sendMessage("Done!")