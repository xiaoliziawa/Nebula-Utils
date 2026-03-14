package dev.celestiacraft.libs.common.material;

import dev.celestiacraft.libs.NebulaLibs;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = NebulaLibs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MaterialRegister {
	@SubscribeEvent
	public static void onCommonSetup(FMLCommonSetupEvent event) {
		RegisterManager manager = new RegisterManager(NebulaLibs.INSTANCE.registrate);

		for (Material material : Material.MATERIALS) {
			for (MaterialType type : material.types) {
				switch (type) {
					case INGOT -> manager.ingot(material);
					case PLATE -> manager.plate(material);
					case NUGGET -> manager.nugget(material);
					case DUST -> manager.dust(material);
					case ROD -> manager.rod(material);
					case GEAR -> manager.gear(material);
					case WIRE -> manager.wire(material);
					case PRISM -> manager.prism(material);

					case BLOCK -> manager.metalBlock(material);
					case RAW_BLOCK -> manager.rawBlock(material);

					case MOLTEN -> manager.moltenFluid(material);

					/*
					 * case CLUMP -> manager.clump(material);
					 * case SHARD -> manager.shard(material);
					 * case CRYSTAL -> manager.crystal(material);
					 * case DIRTY_SLURRY -> manager.dirtySlurry(material);
					 * case SLURRY -> manager.slurry(material);
					 */
				}
			}
		}
	}
}