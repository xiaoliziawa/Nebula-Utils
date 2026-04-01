package dev.celestiacraft.libs.register.item;

import dev.celestiacraft.libs.utils.function.NonNullQuadConsumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TooltipItem extends Item {
	private final List<NonNullQuadConsumer<ItemStack, Level, List<Component>, TooltipFlag>> tooltips;

	public TooltipItem(Properties properties, List<NonNullQuadConsumer<ItemStack, Level, List<Component>, TooltipFlag>> tooltips) {
		super(properties);
		this.tooltips = tooltips;
	}

	@Override
	public void appendHoverText(
			@NotNull ItemStack stack,
			Level level,
			@NotNull List<Component> tooltip,
			@NotNull TooltipFlag flag
	) {
		super.appendHoverText(stack, level, tooltip, flag);

		if (tooltips != null) {
			for (NonNullQuadConsumer<ItemStack, Level, List<Component>, TooltipFlag> t : tooltips) {
				t.accept(stack, level, tooltip, flag);
			}
		}
	}
}