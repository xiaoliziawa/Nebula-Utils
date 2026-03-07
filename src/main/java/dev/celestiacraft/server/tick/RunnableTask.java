package dev.celestiacraft.server.tick;

public class RunnableTask extends TickTask {
	Runnable task;

	public RunnableTask(int ticks, Runnable task) {
		super(ticks);
		this.task = task;
	}

	@Override
	void run() {
		task.run();
	}
}