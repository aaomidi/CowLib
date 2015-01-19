package net.cowcraft.cowlib.spigot.builder;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("unchecked")
public class ItemStackBuilder<T extends ItemStackBuilder, U extends ItemMeta>
{

	protected final ItemStack result;
	protected final U itemMeta;

	public ItemStackBuilder(ItemStack result)
	{
		Material material = result.getType();
		if (!this.isMaterialAllowed(material))
		{
			throw new IllegalArgumentException(String.format("Cannot instantiate %s with resulting ItemStack of Material '%s'!", this.getClass().getSimpleName(), material.name()));
		}

		this.result = result;
		this.itemMeta = (U) result.getItemMeta();
	}

	public ItemStackBuilder(Material material, int amount, short durability)
	{
		this(new ItemStack(material, amount, durability));
	}

	public ItemStackBuilder(Material material, int amount)
	{
		this(new ItemStack(material, amount));
	}

	public ItemStackBuilder(Material material, short durability)
	{
		this(new ItemStack(material, 1, durability));
	}

	public ItemStackBuilder(Material material)
	{
		this(new ItemStack(material));
	}

	protected boolean isMaterialAllowed(Material material)
	{
		return !material.equals(Material.AIR);
	}

	/*
	 * Start Builder Methods
	 */

	public T setMaterial(Material material)
	{
		if (!this.isMaterialAllowed(material))
		{
			throw new IllegalArgumentException(String.format("Cannot set Material of resulting ItemStack to '%s'!", material.name()));
		}

		this.result.setType(material);

		return (T) this;
	}

	public T setAmount(int amount)
	{
		if (amount < 1)
		{
			throw new IllegalArgumentException("Cannot set amount of resulting ItemStack to less than 1!");
		}

		this.result.setAmount(amount);

		return (T) this;
	}

	public T setDurability(short durability)
	{
		short maxDurability = this.result.getType().getMaxDurability();
		if (durability < 0)
		{
			durability += maxDurability;
		}

		if (durability < 0 || durability > maxDurability)
		{
			throw new IllegalArgumentException(String.format("Cannot set durability of resulting ItemStack to less than 0 or greater than %d!", maxDurability));
		}

		this.result.setDurability(durability);

		return (T) this;
	}

	public T addEnchantment(Enchantment enchantment, int level, boolean unsafe)
	{
		this.itemMeta.addEnchant(enchantment, level, unsafe);

		return (T) this;
	}

	public T addEnchantment(Enchantment enchantment, int level)
	{
		return this.addEnchantment(enchantment, level, false);
	}

	public T addEnchantment(Enchantment enchantment, boolean unsafe)
	{
		return this.addEnchantment(enchantment, 1, unsafe);
	}

	public T addEnchantment(Enchantment enchantment)
	{
		return this.addEnchantment(enchantment, 1, false);
	}

	public T removeEnchantment(Enchantment enchantment)
	{
		this.itemMeta.removeEnchant(enchantment);

		return (T) this;
	}

	public T removeEnchantments(Collection<Enchantment> enchantments)
	{
		enchantments.forEach(this::removeEnchantment);

		return (T) this;
	}

	public T removeEnchantments(Enchantment... enchantments)
	{
		return (enchantments.length == 0) ? this.removeEnchantments(this.itemMeta.getEnchants().keySet()) : this.removeEnchantments(Arrays.asList(enchantments));
	}

	public T setDisplayName(String displayName)
	{
		this.itemMeta.setDisplayName(displayName);

		return (T) this;
	}

	public T setLore(List<String> lore)
	{
		this.itemMeta.setLore(lore);

		return (T) this;
	}

	public T setLore(String... lore)
	{
		return this.setLore(Arrays.asList(lore));
	}

	public T addLore(List<String> lore)
	{
		lore.addAll(this.itemMeta.getLore());

		return this.setLore(lore);
	}

	public T addLore(String... lore)
	{
		return this.addLore(Arrays.asList(lore));
	}

	public T setUnbreakable(boolean unbreakable)
	{
		this.itemMeta.spigot().setUnbreakable(unbreakable);

		return (T) this;
	}

	/*
	 * Finish Builder Methods
	 */

	public final ItemStack build()
	{
		this.result.setItemMeta(this.itemMeta);

		return this.result;
	}
}
