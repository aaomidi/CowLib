package net.cowcraft.cowlib.spigot.player;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class PlayerData
{

	private final Map<String, Object> data;

	public PlayerData()
	{
		this.data = new LinkedHashMap<>();
	}

	protected PlayerData(Map<String, Object> data)
	{
		this.data = data;
	}
}
