package dev.celestiacraft.libs.register.item.builder;

import com.tterrag.registrate.AbstractRegistrate;
import com.tterrag.registrate.builders.BuilderCallback;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import dev.celestiacraft.libs.register.item.TooltipItem;
import dev.celestiacraft.libs.utils.function.NonNullQuadConsumer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.ArrayList;
import java.util.List;

public class NebulaItemBuilder<T extends Item, P> extends ItemBuilder<T, P> {
	private final List<NonNullQuadConsumer<ItemStack, Level, List<Component>, TooltipFlag>> tooltips;

	public NebulaItemBuilder(
			AbstractRegistrate<?> owner,
			P parent,
			String name,
			BuilderCallback callback,
			NonNullFunction<Item.Properties, T> factory
	) {
		this(owner, parent, name, callback, factory, new ArrayList<>());
	}

	public NebulaItemBuilder(
			AbstractRegistrate<?> owner,
			P parent,
			String name,
			BuilderCallback callback,
			NonNullFunction<Item.Properties, T> factory,
			List<NonNullQuadConsumer<ItemStack, Level, List<Component>, TooltipFlag>> tooltips
	) {
		super(owner, parent, name, callback, factory);
		this.tooltips = tooltips;
	}

	public NebulaItemBuilder<T, P> tooltip(
			NonNullQuadConsumer<ItemStack, Level, List<Component>, TooltipFlag> tooltip
	) {
		List<NonNullQuadConsumer<ItemStack, Level, List<Component>, TooltipFlag>> newList =
				new ArrayList<>(this.tooltips);

		newList.add(tooltip);

		return new NebulaItemBuilder<>(
				getOwner(),
				getParent(),
				getName(),
				getCallback(),
				(properties) -> {
					return (T) new TooltipItem(properties, newList);
				},
				newList
		);
	}

	public NebulaItemBuilder<T, P> tooltip(Component component) {
		return tooltip((stack, level, tooltip, flag) -> {
			tooltip.add(component);
		});
	}
}