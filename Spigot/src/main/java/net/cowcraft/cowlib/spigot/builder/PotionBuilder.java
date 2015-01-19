package net.cowcraft.cowlib.spigot.builder;

import org.bukkit.Material;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.Collection;

public final class PotionBuilder extends ItemStackBuilder<PotionBuilder, PotionMeta>
{

	public PotionBuilder(int amount)
	{
		super(Material.POTION, amount);
	}

	public PotionBuilder()
	{
		super(Material.POTION);
	}

	@Override
	protected boolean isMaterialAllowed(Material material)
	{
		return material.equals(Material.POTION);
	}

	public PotionBuilder setMainEffect(PotionEffectType effectType)
	{
		this.itemMeta.setMainEffect(effectType);

		return this;
	}

	public PotionBuilder addCustomEffect(PotionEffect effect, boolean overwrite)
	{
		this.itemMeta.addCustomEffect(effect, overwrite);

		return this;
	}

	public PotionBuilder addCustomEffect(PotionEffect effect)
	{
		return this.addCustomEffect(effect, false);
	}

	public PotionBuilder removeCustomEffect(PotionEffectType effectType)
	{
		this.itemMeta.removeCustomEffect(effectType);

		return this;
	}

	public PotionBuilder removeCustomEffects(Collection<PotionEffectType> effectTypes)
	{
		effectTypes.forEach(this::removeCustomEffect);

		return this;
	}

	public PotionBuilder removeCustomEffects(PotionEffectType... effectTypes)
	{
		if (effectTypes.length == 0)
		{
			this.itemMeta.clearCustomEffects();
		}
		else
		{
			this.removeCustomEffects(Arrays.asList(effectTypes));
		}

		return this;
	}
}
