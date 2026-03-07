package dev.celestiacraft.libs.common.register;

import dev.celestiacraft.libs.NebulaLibs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class NebulaItem {
	public static final DeferredRegister<Item> ITEMS;
	public static final Supplier<Item> GEOLOGICAL_HAMMER;

	static {
		ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, NebulaLibs.MODID);

		GEOLOGICAL_HAMMER = ITEMS.register("geological_hammer", () -> {
			return new Item(new Item.Properties()
					.rarity(Rarity.EPIC)
					.stacksTo(1)
			);
		});
	}

	public static void register(IEventBus bus) {
		NebulaLibs.LOGGER.info("Nebula Libs Items Registered!");
		ITEMS.register(bus);
	}
}