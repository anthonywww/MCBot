package com.github.anthonywww.mcbot.event;

public interface CancellableEvent {

	public boolean isCancelled();
	
	public void setCancelled(boolean cancelled);
	
}
