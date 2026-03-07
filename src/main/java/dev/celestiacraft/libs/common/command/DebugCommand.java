package dev.celestiacraft.libs.common.command;

import com.mojang.brigadier.CommandDispatcher;
import dev.celestiacraft.libs.NebulaLibs;
import dev.celestiacraft.libs.debug.DebugUserManager;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NebulaLibs.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class DebugCommand {
	@SubscribeEvent
	public static void onRegisterClientCommands(RegisterClientCommandsEvent event) {
		CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();
		dispatcher.register(Commands.literal("nebula")
				.then(Commands.literal("debug")
						.then(Commands.literal("reload_users")
								.executes((context) -> {
									DebugUserManager.load();
									CommandSourceStack source = context.getSource();

									source.sendSuccess(
											() -> Component.translatable("debug.reload_users"),
											false
									);
									return 1;
								})
						)
				)
		);
	}
}