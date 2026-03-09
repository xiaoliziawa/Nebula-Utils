package dev.celestiacraft.libs.server.tick;

import net.minecraft.server.level.ServerPlayer;

import java.util.function.Consumer;

public class PlayerTask extends TickTask {
	ServerPlayer player;
	Consumer<ServerPlayer> task;

	public PlayerTask(ServerPlayer player, int ticks, Consumer<ServerPlayer> task) {
		super(ticks);
		this.player = player;
		this.task = task;
	}

	@Override
	void run() {
		if (player != null && player.isAlive()) {
			task.accept(player);
		}
	}
}