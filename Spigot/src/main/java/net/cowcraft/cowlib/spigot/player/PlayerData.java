package net.cowcraft.cowlib.spigot.player;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public abstract class PlayerData implements Cloneable
{

	protected final Map<String, Object> data;

	protected PlayerData()
	{
		this.data = new LinkedHashMap<>();
	}

	protected PlayerData(Map<String, Object> data)
	{
		this.data = data;
	}

	protected PlayerData(File file) throws IOException
	{
		this.data = this.load(file);
	}

	protected PlayerData(Reader reader) throws IOException
	{
		this.data = this.load(reader);
	}

	protected abstract Map<String, Object> load(File file) throws IOException;

	protected abstract Map<String, Object> load(Reader reader) throws IOException;

	public abstract void save(File file) throws IOException;

	protected final Object breakdown(String path)
	{
		Map<String, Object> data = this.data;
		Object result = null;

		String[] split = (path.contains(".") ? path.split("\\.") : new String[] {path});
		int limit = (split.length - 1);

		for (int i = 0; i <= limit; i++)
		{
			result = data.get(split[i]);

			if (i == limit)
			{
				break;
			}
			else if (!(result instanceof Map))
			{
				result = null;
				break;
			}

			data = (Map<String, Object>) result;
		}

		return result;
	}

	public final Object get(String path)
	{
		if (path == null)
		{
			return null;
		}

		if ("".equals(path))
		{
			return this.data;
		}

		return this.breakdown(path);
	}

	public final boolean contains(String path)
	{
		return (this.get(path) != null);
	}

	public final Object get(String path, Object def)
	{
		Object got = this.get(path);

		return (got == null) ? def : got;
	}

	public final boolean isBoolean(String path)
	{
		return (this.get(path) instanceof Boolean);
	}

	public final boolean getBoolean(String path, boolean def)
	{
		Object got = this.get(path);

		return (got instanceof Boolean) ? (boolean) got : def;
	}

	public final boolean getBoolean(String path)
	{
		return this.getBoolean(path, false);
	}

	public final boolean isByte(String path)
	{
		return (this.get(path) instanceof Byte);
	}

	public final byte getByte(String path, byte def)
	{
		Object got = this.get(path);

		return (got instanceof Byte) ? (byte) got : def;
	}

	public final byte getByte(String path)
	{
		return this.getByte(path, (byte) 0);
	}

	public final boolean isCharacter(String path)
	{
		return (this.get(path) instanceof Character);
	}

	public final char getCharacter(String path, char def)
	{
		Object got = this.get(path);

		return (got instanceof Character) ? (char) got : def;
	}

	public final char getCharacter(String path)
	{
		return this.getCharacter(path, '\u0000');
	}

	public final boolean isDouble(String path)
	{
		return (this.get(path) instanceof Double);
	}

	public final double getDouble(String path, double def)
	{
		Object got = this.get(path);

		return (got instanceof Double) ? (double) got : def;
	}

	public final double getDouble(String path)
	{
		return this.getDouble(path, 0D);
	}

	public final boolean isFloat(String path)
	{
		return (this.get(path) instanceof Float);
	}

	public final float getFloat(String path, float def)
	{
		Object got = this.get(path);

		return (got instanceof Float) ? (float) got : def;
	}

	public final float getFloat(String path)
	{
		return this.getFloat(path, 0F);
	}

	public final boolean isInteger(String path)
	{
		return (this.get(path) instanceof Integer);
	}

	public final int getInteger(String path, int def)
	{
		Object got = this.get(path);

		return (got instanceof Integer) ? (int) got : def;
	}

	public final int getInteger(String path)
	{
		return this.getInteger(path, 0);
	}

	public final boolean isList(String path)
	{
		return (this.get(path) instanceof List);
	}

	public final List<?> getList(String path, List<?> def)
	{
		Object got = this.get(path);

		return (got instanceof List) ? (List<?>) got : def;
	}

	public final List<?> getList(String path)
	{
		return this.getList(path, null);
	}

	public final <T> List<T> getList(Class<T> clazz, String path)
	{
		List got = this.getList(path);

		if (got == null)
		{
			return null;
		}

		if (got.isEmpty())
		{
			return new LinkedList<>();
		}

		return (got.get(0).getClass().getTypeName().equals(clazz.getTypeName())) ? (List<T>) got : null;
	}

	public final boolean isLong(String path)
	{
		return (this.get(path) instanceof Long);
	}

	public final long getLong(String path, long def)
	{
		Object got = this.get(path);

		return (got instanceof Long) ? (long) got : def;
	}

	public final long getLong(String path)
	{
		return this.getLong(path, 0L);
	}

	public final boolean isShort(String path)
	{
		return (this.get(path) instanceof Short);
	}

	public final short getShort(String path, short def)
	{
		Object got = this.get(path);

		return (got instanceof Short) ? (short) got : def;
	}

	public final short getShort(String path)
	{
		return this.getShort(path, (short) 0);
	}

	public final boolean isString(String path)
	{
		return (this.get(path) instanceof String);
	}

	public final String getString(String path, String def)
	{
		Object got = this.get(path);

		return (got == null) ? def : String.valueOf(got);
	}

	public final String getString(String path)
	{
		return this.getString(path, null);
	}

	public final void set(String path, Object value)
	{
		if (path == null)
		{
			throw new IllegalArgumentException("Path cannot be null!");
		}

		if ("".equals(path))
		{
			if (!(value instanceof Map))
			{
				throw new IllegalArgumentException("Cannot set data to any Object besides java.util.Map!");
			}

			this.data.clear();
			this.data.putAll((Map<String, Object>) value);

			return;
		}

		if (path.contains("."))
		{
			Map<String, Object> level = this.data;
			String key;

			int index = path.lastIndexOf(".");
			key = path.substring(index + 1);
			path = path.substring(0, index);

			String[] split = (path.contains(".") ? path.split("\\.") : new String[] {path});

			Object current;
			for (String node : split)
			{
				current = level.get(node);

				if (!(current instanceof Map))
				{
					current = new LinkedHashMap<>();
					level.put(node, current);
				}

				level = (Map<String, Object>) current;
			}

			level.put(key, value);
		}
		else
		{
			this.data.put(path, value);
		}
	}

	@Override
	public PlayerData clone()
	{
		try
		{
			return (PlayerData) super.clone();
		}
		catch (CloneNotSupportedException ex)
		{
			throw new Error(ex);
		}
	}
}
