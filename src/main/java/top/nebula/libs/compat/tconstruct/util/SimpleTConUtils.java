package top.nebula.libs.compat.tconstruct.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.util.NonNullConsumer;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.ModifierManager;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ToolStack;

import java.util.List;
import java.util.stream.Stream;

/**
 * Tinkers' Construct 常用工具方法集合.
 *
 * <p>
 * 本类对 TConstruct 中常见的 Modifier, ToolStack, Material,
 * Capability 等操作进行了轻量级封装,
 * 主要用于脚本层或兼容层快速调用.
 * </p>
 *
 * <p>
 * 所有方法均为 static, 不需要实例化.
 * 本工具类不包含任何缓存逻辑, 仅做 API 转发和简化封装.
 * </p>
 *
 * <h2>功能分类</h2>
 * <ul>
 *     <li>Modifier 查询与构建</li>
 *     <li>Tag 与注册表访问</li>
 *     <li>ToolStack 读取与转换</li>
 *     <li>材料统计</li>
 *     <li>TinkerData Capability 访问</li>
 * </ul>
 *
 * <p>
 * 注意: 调用前应确保 TConstruct 已加载,
 * 建议配合 ICheckModLoaded.hasTCon() 使用.
 * </p>
 */
public class SimpleTConUtils {
	/**
	 * 将字符串解析为 ResourceLocation.
	 *
	 * @param key 形如 "namespace:path" 的字符串
	 * @return 对应的 ResourceLocation
	 */
	public static ResourceLocation getLocationKey(String key) {
		return ResourceLocation.parse(key);
	}

	/**
	 * 根据字符串 ID 获取 Modifier 实例.
	 *
	 * @param id modifier id
	 * @return 对应的 Modifier
	 */
	public static Modifier getModifier(String id) {
		return ModifierManager.getValue(new ModifierId(id));
	}

	/**
	 * 创建 ModifierId 实例.
	 *
	 * @param id modifier id
	 * @return ModifierId
	 */
	public static ModifierId getModifierId(String id) {
		return new ModifierId(id);
	}

	/**
	 * 判断 ItemStack 是否拥有指定 modifier.
	 *
	 * @param stack 物品栈
	 * @param id modifier id
	 * @return 若等级大于 0 则返回 true
	 */
	public static boolean hasModifier(ItemStack stack, String id) {
		return ModifierUtil.getModifierLevel(stack, new ModifierId(id)) > 0;
	}

	/**
	 * 获取 ItemStack 上指定 modifier 的等级.
	 *
	 * @param stack 物品栈
	 * @param id modifier id
	 * @return modifier 等级, 若不存在则为 0
	 */
	public static int getModifierLevel(ItemStack stack, String id) {
		return ModifierUtil.getModifierLevel(stack, new ModifierId(id));
	}

	/**
	 * 构建 ModifierEntry.
	 *
	 * @param modifier modifier 实例
	 * @param level 等级
	 * @return ModifierEntry
	 */
	public static ModifierEntry getModifierEntry(Modifier modifier, int level) {
		return new ModifierEntry(modifier, level);
	}

	/**
	 * 根据字符串 ID 构建 ModifierEntry.
	 *
	 * @param id modifier id
	 * @param level 等级
	 * @return ModifierEntry
	 */
	public static ModifierEntry getModifierEntry(String id, int level) {
		return new ModifierEntry(new ModifierId(id), level);
	}

	/**
	 * 获取当前游戏中注册的所有 Modifier.
	 *
	 * @return Modifier 流
	 */
	public static Stream<Modifier> getModifiersFromGame() {
		return ModifierManager.INSTANCE.getAllValues();
	}

	/**
	 * 根据 tag 获取所有 Modifier.
	 *
	 * @param tag tag 字符串
	 * @return Modifier 列表
	 */
	public static List<Modifier> getModifiersFromTag(String tag) {
		ResourceLocation resource = ResourceLocation.parse(tag);
		TagKey<Modifier> tagKey = TagKey.create(ModifierManager.REGISTRY_KEY, resource);
		return ModifierManager.getTagValues(tagKey);
	}

	/**
	 * 获取实体指定槽位的 ToolStack.
	 *
	 * @param entity 实体
	 * @param slot 装备槽
	 * @return ToolStack, 若不存在则为 null
	 */
	public static @Nullable ToolStack getToolInSlot(LivingEntity entity, EquipmentSlot slot) {
		return Modifier.getHeldTool(entity, slot);
	}

	/**
	 * 将 IToolStackView 强制转换为 ToolStack.
	 *
	 * @param view IToolStackView
	 * @return ToolStack
	 */
	public static ToolStack castToolStack(IToolStackView view) {
		return (ToolStack) view;
	}

	/**
	 * 统计工具中指定材料出现的次数.
	 *
	 * @param tool 工具视图
	 * @param materialId 材料 id 字符串
	 * @return 出现次数
	 */
	public static int getMaterialsInTool(IToolStackView tool, String materialId) {
		return (int) tool.getMaterials()
				.getList()
				.stream()
				.filter(variant ->
						variant.get()
								.getIdentifier()
								.toString()
								.equals(materialId))
				.count();
	}

	/**
	 * 判断工具中是否包含指定材料.
	 *
	 * @param tool 工具视图
	 * @param materialId 材料 id
	 * @return 若存在则返回 true
	 */
	public static boolean hasMaterialInTool(IToolStackView tool, String materialId) {
		return getMaterialsInTool(tool, materialId) > 0;
	}

	/**
	 * 将 ItemStack 转换为 ToolStack.
	 *
	 * <p>
	 * 若物品不是可修改工具或已损坏, 则返回 null.
	 * </p>
	 *
	 * @param stack 物品栈
	 * @return ToolStack 或 null
	 */
	public static @Nullable ToolStack getToolStack(ItemStack stack) {
		if (!stack.isEmpty() && stack.is(TinkerTags.Items.MODIFIABLE)) {
			return ToolStack.from(stack).isBroken() ? null : ToolStack.from(stack);
		} else {
			return null;
		}
	}

	/**
	 * 获取实体的 TinkerDataCapability 数据.
	 *
	 * @param entity 实体
	 * @param consumer capability 消费者
	 */
	public static void getTinkerData(Entity entity, NonNullConsumer<TinkerDataCapability.Holder> consumer) {
		entity.getCapability(TinkerDataCapability.CAPABILITY).ifPresent(consumer);
	}
}