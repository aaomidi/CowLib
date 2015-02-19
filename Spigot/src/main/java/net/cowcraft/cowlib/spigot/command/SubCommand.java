package net.cowcraft.cowlib.spigot.command;

import org.bukkit.command.CommandSender;

public abstract class SubCommand extends BaseCommand
{

	private final BaseCommand parent;

	private int minimumArgs = 0;
	private int maximumArgs = Integer.MAX_VALUE;

	protected SubCommand(BaseCommand parent, String command, int sender)
	{
		super(command, sender);

		this.parent = parent;
	}

	protected SubCommand(BaseCommand parent, String command)
	{
		super(command);

		this.parent = parent;
	}

	public abstract boolean execute(CommandSender sender, String[] args);

	public final BaseCommand getParent()
	{
		return this.parent;
	}

	public final BaseCommand getAbsoluteParent()
	{
		BaseCommand parent = this.getParent();

		while (parent instanceof SubCommand)
		{
			parent = ((SubCommand) parent).getParent();
		}

		return parent;
	}

	public final int getMinimumArgs()
	{
		return this.minimumArgs;
	}

	protected final void setMinimumArgs(int minimumArgs)
	{
		this.minimumArgs = minimumArgs;
	}

	public final int getMaximumArgs()
	{
		return this.maximumArgs;
	}

	protected final void setMaximumArgs(int maximumArgs)
	{
		this.maximumArgs = maximumArgs;
	}

	protected final void setArgs(int args)
	{
		this.minimumArgs = this.maximumArgs = args;
	}

	public final boolean checkArgsLength(int count)
	{
		return (count >= this.minimumArgs && count <= this.maximumArgs);
	}
}
