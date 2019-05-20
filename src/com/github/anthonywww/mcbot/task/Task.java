package com.github.anthonywww.mcbot.task;

public interface Task {
	
	/**
	 * Get the name of the current task
	 * @return
	 */
	public String getName();
	
	/**
	 * Set if this task should be cancelled while running or in queue
	 * @return
	 */
	public boolean setCancelled();
	
	/**
	 * Set the priority level of this task (0 = max priority, 10 = low priority)
	 * @param priority
	 */
	public void setPriority(int priority);
	
	/**
	 * Get the current task's priority level (0 = high priority, 10 = low priority)
	 * @return
	 */
	public int getPriority();
	
	
}
