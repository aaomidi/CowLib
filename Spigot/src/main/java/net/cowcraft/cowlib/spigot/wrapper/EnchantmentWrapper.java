package net.cowcraft.cowlib.spigot.wrapper;

import org.bukkit.enchantments.Enchantment;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public enum EnchantmentWrapper
{

	PROTECTION_ENVIRONMENTAL(Enchantment.PROTECTION_ENVIRONMENTAL, "protection_environmental"),
	PROTECTION_FIRE(Enchantment.PROTECTION_FIRE, "protection_fire"),
	PROTECTION_FALL(Enchantment.PROTECTION_FALL, "protection_fall"),
	PROTECTION_EXPLOSIONS(Enchantment.PROTECTION_EXPLOSIONS, "protection_explosions"),
	PROTECTION_PROJECTILE(Enchantment.PROTECTION_PROJECTILE, "protection_projectile"),
	OXYGEN(Enchantment.OXYGEN, "oxygen"),
	WATER_WORKER(Enchantment.WATER_WORKER, "water_worker"),
	THORNS(Enchantment.THORNS, "thorns"),
	DAMAGE_ALL(Enchantment.DAMAGE_ALL, "damage_all"),
	DAMAGE_UNDEAD(Enchantment.DAMAGE_UNDEAD, "damage_undead"),
	DAMAGE_ARTHROPODS(Enchantment.DAMAGE_ARTHROPODS, "damage_arthropods"),
	KNOCKBACK(Enchantment.KNOCKBACK, "knockback"),
	FIRE_ASPECT(Enchantment.FIRE_ASPECT, "fire_aspect"),
	LOOT_BONUS_MOBS(Enchantment.LOOT_BONUS_MOBS, "loot_bonus_mobs"),
	DIG_SPEED(Enchantment.DIG_SPEED, "dig_speed"),
	SILK_TOUCH(Enchantment.SILK_TOUCH, "silk_touch"),
	DURABILITY(Enchantment.DURABILITY, "durability"),
	LOOT_BONUS_BLOCKS(Enchantment.LOOT_BONUS_BLOCKS, "loot_bonus_blocks"),
	ARROW_DAMAGE(Enchantment.ARROW_DAMAGE, "arrow_damage"),
	ARROW_KNOCKBACK(Enchantment.ARROW_KNOCKBACK, "arrow_knockback"),
	ARROW_FIRE(Enchantment.ARROW_FIRE, "arrow_fire"),
	ARROW_INFINITE(Enchantment.ARROW_INFINITE, "arrow_infinite"),
	LUCK(Enchantment.LUCK, "luck"),
	LURE(Enchantment.LURE, "lure");

	private static final Map<String, EnchantmentWrapper> ALIAS_MAP = new LinkedHashMap<>();
	private final Enchantment enchantment;
	private final String[] aliases;

	private EnchantmentWrapper(Enchantment enchantment, String... aliases)
	{
		this.enchantment = enchantment;
		this.aliases = aliases;
	}

	public static EnchantmentWrapper get(String name)
	{
		name = name.toLowerCase(Locale.ENGLISH).replace(" ", "_");

		if (!ALIAS_MAP.containsKey(name))
		{
			throw new IllegalArgumentException(String.format("No EnchantmentWrapper found with alias '%s'!", name));
		}

		return ALIAS_MAP.get(name);
	}

	public Enchantment getEnchantment()
	{
		return this.enchantment;
	}

	static
	{
		Arrays.asList(values()).forEach((wrapper) -> Arrays.asList(wrapper.aliases).forEach((alias) -> ALIAS_MAP.put(alias, wrapper)));
	}
}
