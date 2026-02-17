package dev.celestiacraft.libs.tags;

import dev.celestiacraft.libs.tags.type.BlockTag;
import dev.celestiacraft.libs.tags.type.EntityTypeTag;
import dev.celestiacraft.libs.tags.type.FluidTag;
import dev.celestiacraft.libs.tags.type.ItemTag;

/**
 * Tag 构建工厂类.
 *
 * <p>
 * 该类作为各类 TagBuilder 的统一入口,
 * 用于根据标签类型快速创建对应的构建器实例.
 * </p>
 *
 * <p>
 * 通过该类可以避免直接 new 具体类型的 TagBuilder,
 * 使代码更加语义化和集中化.
 * </p>
 *
 * <h2>支持的标签类型</h2>
 * <ul>
 *     <li>BlockTag</li>
 *     <li>ItemTag</li>
 *     <li>FluidTag</li>
 *     <li>EntityTypeTag</li>
 * </ul>
 *
 * <h2>示例</h2>
 *
 * <pre>{@code
 * TagKey<Block> blockTag = TagsBuilder.block("example")
 *         .forge()
 *         .build();
 *
 * TagKey<Item> itemTag = TagsBuilder.item("example_item")
 *         .custom("mymod")
 *         .build();
 * }</pre>
 *
 * <p>
 * 具体的 namespace 设置与构建逻辑由 AbstractTagBuilder 及其子类实现.
 * </p>
 */
public class TagsBuilder {

	/**
	 * 创建一个方块标签构建器.
	 *
	 * @param name 标签路径
	 * @return BlockTag 构建器
	 */
	public static BlockTag block(String name) {
		return new BlockTag(name);
	}

	/**
	 * 创建一个物品标签构建器.
	 *
	 * @param name 标签路径
	 * @return ItemTag 构建器
	 */
	public static ItemTag item(String name) {
		return new ItemTag(name);
	}

	/**
	 * 创建一个流体标签构建器.
	 *
	 * @param name 标签路径
	 * @return FluidTag 构建器
	 */
	public static FluidTag fluid(String name) {
		return new FluidTag(name);
	}

	/**
	 * 创建一个实体类型标签构建器.
	 *
	 * @param name 标签路径
	 * @return EntityTypeTag 构建器
	 */
	public static EntityTypeTag entity(String name) {
		return new EntityTypeTag(name);
	}
}