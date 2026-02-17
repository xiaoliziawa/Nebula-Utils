package dev.celestiacraft.libs.compat.jei.function;

import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import net.minecraft.client.gui.GuiGraphics;

@FunctionalInterface
public interface IDrawHandler<T> {
	void draw(T recipe, IRecipeSlotsView view, GuiGraphics graphics, double mouseX, double mouseY);

	static <T> IDrawHandler<T> empty() {
		return (r, view, graphics, x, y) -> {
		};
	}
}