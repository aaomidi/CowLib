package net.cowcraft.cowlib.spigot.wrapper;

import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public enum PotionEffectTypeWrapper
{

	SPEED(PotionEffectType.SPEED, "speed"),
	SLOW(PotionEffectType.SLOW, "slow"),
	FAST_DIGGING(PotionEffectType.FAST_DIGGING, "fast_digging"),
	SLOW_DIGGING(PotionEffectType.SLOW_DIGGING, "slow_digging"),
	INCREASE_DAMAGE(PotionEffectType.INCREASE_DAMAGE, "increase_damage"),
	HEAL(PotionEffectType.HEAL, "heal"),
	HARM(PotionEffectType.HARM, "harm"),
	JUMP(PotionEffectType.JUMP, "jump"),
	CONFUSION(PotionEffectType.CONFUSION, "confusion"),
	REGENERATION(PotionEffectType.REGENERATION, "regeneration"),
	DAMAGE_RESISTANCE(PotionEffectType.DAMAGE_RESISTANCE, "damage_resistance"),
	FIRE_RESISTANCE(PotionEffectType.FIRE_RESISTANCE, "fire_resistance"),
	WATER_BREATHING(PotionEffectType.WATER_BREATHING, "water_breathing"),
	INVISIBILITY(PotionEffectType.INVISIBILITY, "invisibility"),
	BLINDNESS(PotionEffectType.BLINDNESS, "blindness"),
	NIGHT_VISION(PotionEffectType.NIGHT_VISION, "night_vision"),
	HUNGER(PotionEffectType.HUNGER, "hunger"),
	WEAKNESS(PotionEffectType.WEAKNESS, "weakness"),
	POISON(PotionEffectType.POISON, "posion"),
	WITHER(PotionEffectType.WITHER, "wither"),
	HEALTH_BOOST(PotionEffectType.HEALTH_BOOST, "health_boost"),
	ABSORPTION(PotionEffectType.ABSORPTION, "absorption"),
	SATURATION(PotionEffectType.SATURATION, "saturation");

	private static final Map<String, PotionEffectTypeWrapper> ALIAS_MAP = new LinkedHashMap<>();
	private final PotionEffectType effectType;
	private final String[] aliases;

	private PotionEffectTypeWrapper(PotionEffectType effectType, String... aliases)
	{
		this.effectType = effectType;
		this.aliases = aliases;
	}

	public PotionEffectTypeWrapper get(String name)
	{
		name = name.toLowerCase(Locale.ENGLISH).replace(" ", "_");

		if (!ALIAS_MAP.containsKey(name))
		{
			throw new IllegalArgumentException(String.format("No PotionEffectTypeWrapper with alias '%s'!", name));
		}

		return ALIAS_MAP.get(name);
	}

	public PotionEffectType getEffectType()
	{
		return this.effectType;
	}

	static
	{
		Arrays.asList(values()).forEach((wrapper) -> Arrays.asList(wrapper.aliases).forEach((alias) -> ALIAS_MAP.put(alias, wrapper)));
	}
}
