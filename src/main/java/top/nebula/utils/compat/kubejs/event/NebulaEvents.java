package top.nebula.utils.compat.kubejs.event;

import dev.latvian.mods.kubejs.event.EventGroup;
import dev.latvian.mods.kubejs.event.EventHandler;
import top.nebula.utils.compat.kubejs.event.function.FluidTooltipEvent;

public interface NebulaEvents {
	EventGroup INSTANCE = EventGroup.of("NebulaEvents");
	EventHandler FLUID_TOOLTIP = INSTANCE.client("fluidTooltip", () -> {
		return FluidTooltipEvent.class;
	});
}