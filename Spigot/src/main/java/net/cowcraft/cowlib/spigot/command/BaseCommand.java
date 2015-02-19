package net.cowcraft.cowlib.spigot.command;

import org.bukkit.block.CommandBlock;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.entity.minecart.CommandMinecart;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseCommand implements CommandExecutor
{

	protected static final int SENDER_PLAYER = 0x01;
	protected static final int SENDER_CONSOLE = 0x02;
	protected static final int SENDER_REMOTE = 0x04;
	protected static final int SENDER_BLOCK = 0x08;
	protected static final int SENDER_MINECART = 0x10;

	private final String command;
	private final int sender;

	private String description = "No description provided :(";
	private String permission = "";
	private String defaultSubCommand = "help";

	protected final Map<String, SubCommand> subCommands = new LinkedHashMap<>();

	protected BaseCommand(String command, int sender)
	{
		this.command = command;
		this.sender = sender;
	}

	protected BaseCommand(String command)
	{
		this(command, (SENDER_PLAYER | SENDER_CONSOLE | SENDER_REMOTE | SENDER_BLOCK | SENDER_MINECART));
	}

	@Override
	public final boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args)
	{
		if (!this.canExecute(sender))
		{

		}

		String subCommand = this.getDefaultSubCommand();
		if (args.length > 0)
		{

		}
	}

	public final String getCommand()
	{
		return this.command;
	}

	protected final int getSender()
	{
		return this.sender;
	}

	public final String getDescription()
	{
		return this.description;
	}

	protected final void setDescription(String description)
	{
		this.description = description;
	}

	public final String getPermission()
	{
		return this.permission;
	}

	protected final void setPermission(String permission)
	{
		this.permission = permission;
	}

	public final String getDefaultSubCommand()
	{
		return this.defaultSubCommand;
	}

	protected final void setDefaultSubCommand(String defaultSubCommand)
	{
		this.defaultSubCommand = defaultSubCommand;
	}

	public final Collection<SubCommand> getSubCommands()
	{
		return this.subCommands.values();
	}

	public final SubCommand getSubCommand(String command)
	{
		return this.subCommands.get(command);
	}

	public final boolean addSubCommand(SubCommand subCommand, boolean overwrite)
	{
		String command = subCommand.getCommand();

		if (!overwrite && this.subCommands.containsKey(command))
		{
			return false;
		}

		this.subCommands.put(command, subCommand);

		return true;
	}

	public final boolean addSubCommand(SubCommand subCommand)
	{
		return this.addSubCommand(subCommand, false);
	}

	public final boolean removeSubCommand(String command)
	{
		if (this.subCommands.containsKey(command))
		{
			return false;
		}

		this.subCommands.remove(command);

		return true;
	}

	public boolean hasPermission(CommandSender sender)
	{
		return ("".equals(this.permission) || sender.hasPermission(this.permission));
	}

	public boolean canExecute(CommandSender sender)
	{
		if (!this.hasPermission(sender))
		{
			return false;
		}

		if (sender instanceof Player)
		{
			return ((this.sender & SENDER_PLAYER) != 0);
		}
		else if (sender instanceof ConsoleCommandSender)
		{
			return ((this.sender & SENDER_CONSOLE) != 0);
		}
		else if (sender instanceof RemoteConsoleCommandSender)
		{
			return ((this.sender & SENDER_REMOTE) != 0);
		}
		else if (sender instanceof CommandBlock)
		{
			return ((this.sender & SENDER_BLOCK) != 0);
		}
		else if (sender instanceof CommandMinecart)
		{
			return ((this.sender & SENDER_MINECART) != 0);
		}

		return false;
	}
}
