package dev.celestiacraft.libs.api.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.structure.Structure;

public class BlockUtils {
	public static boolean isInFortress(Level level, BlockPos pos, ResourceKey<Structure> structure) {
		if (!(level instanceof ServerLevel serverLevel)) {
			return false;
		}

		return serverLevel.structureManager()
				.getStructureWithPieceAt(pos, structure)
				.isValid();
	}
}