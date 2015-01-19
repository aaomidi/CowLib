package net.cowcraft.cowlib.spigot.builder;

import net.cowcraft.cowlib.spigot.SkullType;
import org.bukkit.Material;
import org.bukkit.inventory.meta.SkullMeta;

public final class SkullBuilder extends ItemStackBuilder<SkullBuilder, SkullMeta>
{

	public SkullBuilder(SkullType type, int amount)
	{
		super(Material.SKULL_ITEM, amount, type.getData());
	}

	public SkullBuilder(SkullType type)
	{
		super(Material.SKULL_ITEM, type.getData());
	}

	public SkullBuilder(int amount)
	{
		super(Material.SKULL_ITEM, amount);
	}

	public SkullBuilder()
	{
		super(Material.SKULL_ITEM);
	}

	@Override
	protected boolean isMaterialAllowed(Material material)
	{
		return material.equals(Material.SKULL_ITEM);
	}

	public SkullBuilder setOwner(String owner)
	{
		this.itemMeta.setOwner(owner);

		return this;
	}
}
