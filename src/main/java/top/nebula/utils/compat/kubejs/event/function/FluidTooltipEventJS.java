package top.nebula.utils.compat.kubejs.event.function;

import dev.latvian.mods.kubejs.event.EventJS;
import dev.latvian.mods.kubejs.fluid.FluidStackJS;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FluidTooltipEventJS extends EventJS {
	private final FluidStackJS fluidId;
	private final List<Component> tooltip;
	private final List<Consumer<List<Component>>> queuedAdds = new ArrayList<>();

	public FluidTooltipEventJS(FluidStackJS fluid, List<Component> hanlder) {
		this.fluidId = fluid;
		this.tooltip = hanlder;
	}

	public FluidStackJS getFluid() {
		return fluidId;
	}

	/**
	 * event.add(Fluid.of("minecraft:lava"), (tooltip) => {
	 *     tooltip.add(Component.xxxx("aaa"))
	 * })
	 *
	 * @param fluid 传入流体
	 * @param hanlder 处理器
	 */
	public void add(FluidStackJS fluid, Consumer<List<Component>> hanlder) {
		if (fluid == null || fluidId == null) {
			return;
		}
		if (fluidId.equals(fluid)) {
			queuedAdds.add(hanlder);
		}
	}

	public void apply() {
		for (Consumer<List<Component>> consumer : queuedAdds) {
			consumer.accept(tooltip);
		}
	}
}