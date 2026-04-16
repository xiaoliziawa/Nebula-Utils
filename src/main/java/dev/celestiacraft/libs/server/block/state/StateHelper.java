package dev.celestiacraft.libs.server.block.state;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;

import java.util.Optional;

public class StateHelper {
	public static BlockState with(BlockState state, String name, String value) {
		Property<?> prop = state.getBlock().getStateDefinition().getProperty(name);
		if (prop == null) {
			return state;
		}

		try {
			// Boolean
			if (prop instanceof BooleanProperty boolProp) {
				return state.setValue(boolProp, Boolean.parseBoolean(value));
			}

			// Integer
			if (prop instanceof IntegerProperty intProp) {
				int intValue = Integer.parseInt(value);
				if (intProp.getPossibleValues().contains(intValue)) {
					return state.setValue(intProp, intValue);
				}
				return state;
			}

			// Enum (包含 Direction / Half / Axis / Shape 等全部)
			if (prop instanceof EnumProperty<?> enumProp) {
				Optional<?> match = enumProp.getPossibleValues().stream()
						.filter((object) -> {
							return object.toString().equalsIgnoreCase(value);
						})
						.findFirst();

				return match.map((object) -> {
					return setEnum(state, enumProp, object);
				}).orElse(state);
			}

			Optional<?> parsed = prop.getValue(value);
			if (parsed.isPresent()) {
				return setGeneric(state, prop, parsed.get());
			}

		} catch (Exception ignored) {
		}

		return state;
	}

	private static <T extends Comparable<T>> BlockState setGeneric(BlockState state, Property<?> prop, Object value) {
		return state.setValue((Property<T>) prop, (T) value);
	}

	private static <T extends Enum<T> & Comparable<T> & StringRepresentable> BlockState setEnum(BlockState state, EnumProperty<?> prop, Object value) {
		return state.setValue((EnumProperty<T>) prop, (T) value);
	}

	public static <T extends Comparable<T>> BlockState with(BlockState state, Property<T> prop, T value) {
		if (state.hasProperty(prop)) {
			return state.setValue(prop, value);
		}
		return state;
	}
}