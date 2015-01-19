package net.cowcraft.cowlib.spigot.builder;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import java.util.Arrays;
import java.util.Collection;

public final class EnchantedBookBuilder extends ItemStackBuilder<EnchantedBookBuilder, EnchantmentStorageMeta>
{

	public EnchantedBookBuilder(int amount)
	{
		super(Material.ENCHANTED_BOOK, amount);
	}

	public EnchantedBookBuilder()
	{
		super(Material.ENCHANTED_BOOK);
	}

	@Override
	protected boolean isMaterialAllowed(Material material)
	{
		return material.equals(Material.ENCHANTED_BOOK);
	}

	public EnchantedBookBuilder addStoredEnchantment(Enchantment enchantment, int level, boolean unsafe)
	{
		this.itemMeta.addStoredEnchant(enchantment, level, unsafe);

		return this;
	}

	public EnchantedBookBuilder addStoredEnchantment(Enchantment enchantment, int level)
	{
		return this.addStoredEnchantment(enchantment, level, false);
	}

	public EnchantedBookBuilder addStoredEnchantment(Enchantment enchantment, boolean unsafe)
	{
		return this.addStoredEnchantment(enchantment, 1, unsafe);
	}

	public EnchantedBookBuilder addStoredEnchantment(Enchantment enchantment)
	{
		return this.addStoredEnchantment(enchantment, 1, false);
	}

	public EnchantedBookBuilder removeStoredEnchantment(Enchantment enchantment)
	{
		this.itemMeta.removeStoredEnchant(enchantment);

		return this;
	}

	public EnchantedBookBuilder removeStoredEnchantments(Collection<Enchantment> enchantments)
	{
		enchantments.forEach(this::removeStoredEnchantment);

		return this;
	}

	public EnchantedBookBuilder removeStoredEnchantments(Enchantment... enchantments)
	{
		return (enchantments.length == 0) ? this.removeStoredEnchantments(this.itemMeta.getStoredEnchants().keySet()) : this.removeStoredEnchantments(Arrays.asList(enchantments));
	}
}
