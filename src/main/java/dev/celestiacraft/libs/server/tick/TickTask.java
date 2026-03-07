package dev.celestiacraft.libs.server.tick;

public abstract class TickTask {
	int ticks;

	public TickTask(int ticks) {
		this.ticks = ticks;
	}

	abstract void run();
}