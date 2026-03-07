package dev.celestiacraft.server.tick;

import net.minecraft.server.MinecraftServer;

import java.util.function.Consumer;

public class ServerTask extends TickTask {
	MinecraftServer server;
	Consumer<MinecraftServer> task;

	public ServerTask(MinecraftServer server, int ticks, Consumer<MinecraftServer> task) {
		super(ticks);
		this.server = server;
		this.task = task;
	}

	@Override
	void run() {
		if (server != null)
			task.accept(server);
	}
}