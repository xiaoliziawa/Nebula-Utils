package dev.celestiacraft.libs.utils;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.world.entity.player.Player;

public class CommandUtils {
	public static void runCommand(Player player, String command) {
		CommandSourceStack source = player.createCommandSourceStack();
		player.getServer().getCommands().performPrefixedCommand(source, command);
	}
}