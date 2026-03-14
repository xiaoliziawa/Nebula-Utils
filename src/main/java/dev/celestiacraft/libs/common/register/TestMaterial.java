package dev.celestiacraft.libs.common.register;

import dev.celestiacraft.libs.NebulaLibs;
import dev.celestiacraft.libs.common.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NebulaLibs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TestMaterial {
	public static final Material MATERIAL = new Material("test_namespace");

	public static final Material COPPER = MATERIAL.material("copper", BlockTags.NEEDS_IRON_TOOL)
			.ingot()
			.dust();
}