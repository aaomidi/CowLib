package net.cowcraft.cowlib.spigot.builder;

import net.cowcraft.cowlib.spigot.ArmorMaterial;
import net.cowcraft.cowlib.spigot.ArmorPiece;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public final class LeatherArmorBuilder extends ItemStackBuilder<LeatherArmorBuilder, LeatherArmorMeta>
{

	public LeatherArmorBuilder(ArmorPiece piece, int amount, short durability)
	{
		super(piece.getMaterial(ArmorMaterial.LEATHER), amount, durability);
	}

	public LeatherArmorBuilder(ArmorPiece piece, int amount)
	{
		super(piece.getMaterial(ArmorMaterial.LEATHER), amount);
	}

	public LeatherArmorBuilder(ArmorPiece piece, short durability)
	{
		super(piece.getMaterial(ArmorMaterial.LEATHER), durability);
	}

	public LeatherArmorBuilder(ArmorPiece piece)
	{
		super(piece.getMaterial(ArmorMaterial.LEATHER));
	}

	@Override
	protected boolean isMaterialAllowed(Material material)
	{
		return material.name().startsWith("LEATHER_");
	}

	public LeatherArmorBuilder setColor(Color color)
	{
		this.itemMeta.setColor(color);

		return this;
	}
}
