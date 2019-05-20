
function handleMessage(username, msg)
  print("Got message from " .. username .. ": " .. msg)
end

bot.onMessage(handleMessage)