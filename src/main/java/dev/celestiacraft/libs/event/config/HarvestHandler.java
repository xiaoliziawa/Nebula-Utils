package dev.celestiacraft.libs.event.config;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import dev.celestiacraft.libs.NebulaLibs;
import dev.celestiacraft.libs.config.matches.UseToolBlocks;

@Mod.EventBusSubscriber(modid = NebulaLibs.MODID)
public class HarvestHandler {
	@SubscribeEvent
	public static void onHarvestCheck(PlayerEvent.HarvestCheck event) {
		Player player = event.getEntity();
		BlockState state = event.getTargetBlock();

		if (!UseToolBlocks.matches(state)) {
			return;
		}

		ItemStack stack = player.getMainHandItem();

		// 直接使用原版工具判定
		if (stack.isEmpty() || !player.hasCorrectToolForDrops(state)) {
			event.setCanHarvest(false);
		}
	}
}