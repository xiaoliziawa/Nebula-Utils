package dev.celestiacraft.server.tick;

public class TickTask {
	int ticks;

	public TickTask(int ticks) {
		this.ticks = ticks;
	}

	abstract void run();
}