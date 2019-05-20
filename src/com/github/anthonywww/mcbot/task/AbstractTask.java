package com.github.anthonywww.mcbot.task;

public abstract class AbstractTask implements Task, Comparable<AbstractTask> {
	
	public AbstractTask() {
		
	}
	
	@Override
	public int compareTo(AbstractTask other) {
		return 0;
	}
	
}
