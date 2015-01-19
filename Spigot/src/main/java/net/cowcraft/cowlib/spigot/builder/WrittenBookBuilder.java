package net.cowcraft.cowlib.spigot.builder;

import org.bukkit.Material;
import org.bukkit.inventory.meta.BookMeta;

import java.util.List;

public final class WrittenBookBuilder extends ItemStackBuilder<WrittenBookBuilder, BookMeta>
{

	public WrittenBookBuilder(int amount, boolean signed)
	{
		super(signed ? Material.WRITTEN_BOOK : Material.BOOK_AND_QUILL, amount);
	}

	public WrittenBookBuilder(int amount)
	{
		super(Material.BOOK_AND_QUILL, amount);
	}

	public WrittenBookBuilder(boolean signed)
	{
		super(signed ? Material.WRITTEN_BOOK : Material.BOOK_AND_QUILL);
	}

	public WrittenBookBuilder()
	{
		super(Material.BOOK_AND_QUILL);
	}

	@Override
	protected boolean isMaterialAllowed(Material material)
	{
		return (material.equals(Material.BOOK_AND_QUILL) || material.equals(Material.WRITTEN_BOOK));
	}

	public WrittenBookBuilder setSigned(boolean signed)
	{
		this.setMaterial(signed ? Material.WRITTEN_BOOK : Material.BOOK_AND_QUILL);

		return this;
	}

	public WrittenBookBuilder setTitle(String title)
	{
		this.itemMeta.setTitle(title);

		return this;
	}

	public WrittenBookBuilder setAuthor(String author)
	{
		this.itemMeta.setAuthor(author);

		return this;
	}

	public WrittenBookBuilder setPages(List<String> pages)
	{
		this.itemMeta.setPages(pages);

		return this;
	}

	public WrittenBookBuilder setPages(String... pages)
	{
		this.itemMeta.setPages(pages);

		return this;
	}

	public WrittenBookBuilder addPages(List<String> pages)
	{
		pages.addAll(this.itemMeta.getPages());

		return this.setPages(pages);
	}

	public WrittenBookBuilder addPages(String... pages)
	{
		this.itemMeta.addPage(pages);

		return this;
	}

	public WrittenBookBuilder setPage(int i, String page)
	{
		this.itemMeta.setPage(i, page);

		return this;
	}
}
