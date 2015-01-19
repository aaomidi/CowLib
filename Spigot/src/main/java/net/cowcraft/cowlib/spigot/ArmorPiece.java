package net.cowcraft.cowlib.spigot;

import org.bukkit.Material;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public enum ArmorPiece
{

	HELMET("helmet"),
	CHESTPLATE("chestplate"),
	LEGGINGS("leggings"),
	BOOTS("boots");

	private static final Map<String, ArmorPiece> ALIAS_MAP = new LinkedHashMap<>();
	private final String[] aliases;

	private ArmorPiece(String... aliases)
	{
		this.aliases = aliases;
	}

	public static ArmorPiece get(String name)
	{
		name = name.toLowerCase(Locale.ENGLISH);

		if (!ALIAS_MAP.containsKey(name))
		{
			throw new IllegalArgumentException(String.format("No ArmorPiece found with alias '%s'!", name));
		}

		return ALIAS_MAP.get(name);
	}

	public Material getMaterial(ArmorMaterial material)
	{
		return Material.valueOf(String.format("%s_%s", material.name(), this.name()));
	}

	static
	{
		Arrays.asList(values()).forEach((piece) -> Arrays.asList(piece.aliases).forEach((alias) -> ALIAS_MAP.put(alias, piece)));
	}
}
