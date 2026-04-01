package dev.celestiacraft.libs.utils.function;

@FunctionalInterface
public interface NonNullQuadConsumer<A, B, C, D> {
	void accept(A a, B b, C c, D d);
}