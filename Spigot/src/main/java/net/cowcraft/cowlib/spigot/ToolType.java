package net.cowcraft.cowlib.spigot;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public enum ToolType
{

	AXE("axe"),
	HOE("hoe"),
	PICKAXE("pickaxe"),
	SPADE("spade"),
	SWORD("sword");

	private static final Map<String, ToolType> ALIAS_MAP = new LinkedHashMap<>();
	private final String[] aliases;

	private ToolType(String... aliases)
	{
		this.aliases = aliases;
	}

	public static ToolType get(String name)
	{
		name = name.toLowerCase(Locale.ENGLISH);

		if (!ALIAS_MAP.containsKey(name))
		{
			throw new IllegalArgumentException(String.format("No ToolType found with alias '%s'!", name));
		}

		return ALIAS_MAP.get(name);
	}

	public Material getMaterial(ToolMaterial material)
	{
		return Material.valueOf(String.format("%s_%s", material.name(), this.name()));
	}

	static
	{
		Arrays.asList(values()).forEach((type) -> Arrays.asList(type.aliases).forEach((alias) -> ALIAS_MAP.put(alias, type)));
	}
}
