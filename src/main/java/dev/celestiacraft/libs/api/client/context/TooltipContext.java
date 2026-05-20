package dev.celestiacraft.libs.api.client.context;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public class TooltipContext {
	@Getter
	private final ItemStack stack;
	@Getter
	private final Level level;
	@Getter
	private final List<Component> tooltip;
	@Getter
	private final TooltipFlag flag;
	private final Player player;

	public TooltipContext(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag, Player player) {
		this.stack = stack;
		this.level = level;
		this.tooltip = tooltip;
		this.flag = flag;
		this.player = player;
	}

	public Player getPlayer() {
		if (player != null) {
			return player;
		}
		return Minecraft.getInstance().player;
	}

	public BlockPos getLookingBlockPos() {
		Minecraft minecraft = Minecraft.getInstance();

		if (minecraft.hitResult instanceof BlockHitResult result) {
			return result.getBlockPos();
		}

		return BlockPos.ZERO;
	}

	public BlockState getLookingBlockState() {
		BlockPos pos = getLookingBlockPos();

		if (level == null) {
			return null;
		}

		return level.getBlockState(pos);
	}

	public Vec3 getPlayerPos() {
		Player player = getPlayer();
		return player != null ? player.position() : Vec3.ZERO;
	}

	public BlockPos getPlayerBlockPos() {
		Player player = getPlayer();
		return player != null ? player.blockPosition() : BlockPos.ZERO;
	}

	public void addEmpty() {
		tooltip.add(Component.empty());
	}

	public void add(Component component) {
		tooltip.add(component);
	}

	public void add(String text) {
		tooltip.add(Component.literal(text));
	}

	public void addTranslatable(String key) {
		tooltip.add(Component.translatable(key));
	}

	public void addTranslatable(String key, Object... args) {
		tooltip.add(Component.translatable(key, args));
	}

	public boolean isShiftDown() {
		return Screen.hasShiftDown();
	}

	public boolean isCtrlDown() {
		return Screen.hasControlDown();
	}

	public boolean isAltDown() {
		return Screen.hasAltDown();
	}

	public boolean isAdvanced() {
		return flag != null && flag.isAdvanced();
	}

	public void addShiftTooltip(String normalKey, String shiftKey) {
		if (isShiftDown()) {
			addTranslatable(shiftKey);
		} else {
			addTranslatable(normalKey);
		}
	}

	public void addCtrlTooltip(String normalKey, String ctrlKey) {
		if (isCtrlDown()) {
			addTranslatable(ctrlKey);
		} else {
			addTranslatable(normalKey);
		}
	}

	public void addAltTooltip(String normalKey, String altKey) {
		if (isAltDown()) {
			addTranslatable(altKey);
		} else {
			addTranslatable(normalKey);
		}
	}
}