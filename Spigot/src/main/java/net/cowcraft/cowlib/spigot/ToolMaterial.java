package net.cowcraft.cowlib.spigot;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public enum ToolMaterial
{

	WOOD("wood"),
	STONE("stone"),
	IRON("iron"),
	GOLD("gold"),
	DIAMOND("diamond");

	private static final Map<String, ToolMaterial> ALIAS_MAP = new LinkedHashMap<>();
	private final String[] aliases;

	private ToolMaterial(String... aliases)
	{
		this.aliases = aliases;
	}

	public static ToolMaterial get(String name)
	{
		name = name.toLowerCase(Locale.ENGLISH);

		if (!ALIAS_MAP.containsKey(name))
		{
			throw new IllegalArgumentException(String.format("No ToolMaterial found with alias '%s'!", name));
		}

		return ALIAS_MAP.get(name);
	}

	public Material getMaterial(ToolType type)
	{
		return Material.valueOf(String.format("%s_%s", this.name(), type.name()));
	}

	static
	{
		Arrays.asList(values()).forEach((material) -> Arrays.asList(material.aliases).forEach((alias) -> ALIAS_MAP.put(alias, material)));
	}
}
