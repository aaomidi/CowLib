package net.cowcraft.cowlib.spigot.builder;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.inventory.meta.FireworkMeta;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public final class FireworkBuilder extends ItemStackBuilder<FireworkBuilder, FireworkMeta>
{

	public FireworkBuilder(int amount)
	{
		super(Material.FIREWORK, amount);
	}

	public FireworkBuilder()
	{
		super(Material.FIREWORK);
	}

	@Override
	protected boolean isMaterialAllowed(Material material)
	{
		return material.equals(Material.FIREWORK);
	}

	public FireworkBuilder setPower(int power)
	{
		if (power < 0 || power > 128)
		{
			throw new IllegalArgumentException("Cannot set power of resulting ItemStack to less than 0 or greater than 128!");
		}

		this.itemMeta.setPower(power);

		return this;
	}

	public FireworkBuilder addEffect(FireworkEffect effect)
	{
		this.itemMeta.addEffect(effect);

		return this;
	}

	public FireworkBuilder addEffects(Collection<FireworkEffect> effects)
	{
		this.itemMeta.addEffects(effects);

		return this;
	}

	public FireworkBuilder addEffects(FireworkEffect... effects)
	{
		this.itemMeta.addEffects(effects);

		return this;
	}

	public FireworkBuilder removeEffect(int index)
	{
		this.itemMeta.removeEffect(index);

		return this;
	}

	public FireworkBuilder removeEffect(FireworkEffect effect)
	{
		List<FireworkEffect> effects = this.itemMeta.getEffects();

		return (effects.contains(effect)) ? this.removeEffect(effects.indexOf(effect)) : this;
	}

	public FireworkBuilder removeEffects(Collection<FireworkEffect> effects)
	{
		effects.forEach(this::removeEffect);

		return this;
	}

	public FireworkBuilder removeEffects(FireworkEffect... effects)
	{
		if (effects.length == 0)
		{
			this.itemMeta.clearEffects();
		}
		else
		{
			this.removeEffects(Arrays.asList(effects));
		}

		return this;
	}
}
