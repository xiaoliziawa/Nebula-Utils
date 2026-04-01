package dev.celestiacraft.libs.register;

import com.simibubi.create.foundation.data.CreateRegistrate;
import com.simibubi.create.foundation.item.TooltipModifier;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import dev.celestiacraft.libs.register.item.builder.NebulaItemBuilder;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class NebulaRegistrate extends CreateRegistrate {
	protected NebulaRegistrate(String modid) {
		super(modid);
	}

	public static NebulaRegistrate create(String modid) {
		return new NebulaRegistrate(modid);
	}

	@Override
	public NebulaRegistrate setTooltipModifierFactory(@Nullable Function<Item, TooltipModifier> factory) {
		super.setTooltipModifierFactory(factory);
		return this;
	}

	@Override
	public <T extends Item, P> @NotNull NebulaItemBuilder<T, P> item(
			@NotNull P parent,
			@NotNull String name,
			@NotNull NonNullFunction<Item.Properties, T> factory
	) {
		return entry(name, callback ->
				NebulaItemBuilder.create(this, parent, name, callback, factory)
						.transform((builder) ->
								this.defaultCreativeModeTab == null
										? builder
										: builder.tab(this.defaultCreativeModeTab)
						)
		);
	}

	@Override
	public <T extends Item> @NotNull NebulaItemBuilder<T, CreateRegistrate> item(
			@NotNull String name,
			@NotNull NonNullFunction<Item.Properties, T> factory
	) {
		return item(self(), name, factory);
	}
}