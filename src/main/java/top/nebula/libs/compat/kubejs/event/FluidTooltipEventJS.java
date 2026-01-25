package top.nebula.libs.compat.kubejs.event;

import dev.latvian.mods.kubejs.event.EventJS;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import top.nebula.libs.event.FluidTooltipHandler;

import java.util.List;
import java.util.function.Consumer;

public class FluidTooltipEventJS extends EventJS {

	/**
	 * JS:
	 * event.add("minecraft:lava", tooltip => {})
	 * event.add("#minecraft:water", tooltip => {})
	 */
	public void add(String id, Consumer<List<Component>> tooltipHandler) {
		if (id.startsWith("#")) {
			FluidTooltipHandler.registerTag(
					new ResourceLocation(id.substring(1)),
					tooltipHandler
			);
		} else {
			FluidTooltipHandler.registerFluid(
					new ResourceLocation(id),
					tooltipHandler
			);
		}
	}
}