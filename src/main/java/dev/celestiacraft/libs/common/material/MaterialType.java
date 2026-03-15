package dev.celestiacraft.libs.common.material;

import lombok.Getter;

@Getter
public enum MaterialType {
	INGOT("ingot"),
	PLATE("plate"),
	NUGGET("nugget"),
	DUST("dust"),
	ROD("rod"),
	GEAR("gear"),
	PRISM("prism"),
	WIRE("wire"),
	RAW_ORE("raw_ore"),

	BLOCK("block"),
	RAW_BLOCK("raw_block"),
	MOLTEN("molten"),

	DIRTY("dirty"),
	CLUMP("clump"),
	SHARD("shard"),
	CRYSTAL("crystal"),
	SLURRY("slurry"),
	DIRTY_SLURRY("dirty_slurry");

	public final String id;

	MaterialType(String id) {
		this.id = id;
	}
}