package net.cowcraft.cowlib.spigot;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public enum ArmorMaterial
{

	LEATHER("leather"),
	CHAINMAIL("chainmail"),
	IRON("iron"),
	GOLD("gold"),
	DIAMOND("diamond");

	private static final Map<String, ArmorMaterial> ALIAS_MAP = new LinkedHashMap<>();
	private final String[] aliases;

	private ArmorMaterial(String... aliases)
	{
		this.aliases = aliases;
	}

	public static ArmorMaterial get(String name)
	{
		name = name.toLowerCase(Locale.ENGLISH);

		if (!ALIAS_MAP.containsKey(name))
		{
			throw new IllegalArgumentException(String.format("No ArmorMaterial found with alias '%s'!", name));
		}

		return ALIAS_MAP.get(name);
	}

	public Material getMaterial(ArmorPiece piece)
	{
		return Material.valueOf(String.format("%s_%s", this.name(), piece.name()));
	}

	static
	{
		Arrays.asList(values()).forEach((material) -> Arrays.asList(material.aliases).forEach((alias) -> ALIAS_MAP.put(alias, material)));
	}
}
