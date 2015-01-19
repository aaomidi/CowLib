package net.cowcraft.cowlib.spigot;

public enum SkullType
{

	SKELETON((byte) 0),
	WITHER_SKELETON((byte) 1),
	ZOMBIE((byte) 2),
	PLAYER((byte) 3),
	CREEPER((byte) 4);

	private final byte data;

	private SkullType(byte data)
	{
		this.data = data;
	}

	public byte getData()
	{
		return this.data;
	}
}
