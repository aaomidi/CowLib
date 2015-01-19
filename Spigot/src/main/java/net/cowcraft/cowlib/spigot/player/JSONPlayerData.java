package net.cowcraft.cowlib.spigot.player;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Map;

public final class JSONPlayerData extends PlayerData
{

	private static final JSONParser JSON_PARSER = new JSONParser();

	public JSONPlayerData()
	{
		super();
	}

	public JSONPlayerData(Map<String, Object> data)
	{
		super(data);
	}

	public JSONPlayerData(File file) throws IOException
	{
		super(file);
	}

	public JSONPlayerData(Reader reader) throws IOException
	{
		super(reader);
	}

	@Override
	public final Map<String, Object> load(File file) throws IOException
	{
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		Map<String, Object> result = this.load(bufferedReader);

		bufferedReader.close();
		fileReader.close();

		return result;
	}

	@Override
	@SuppressWarnings("unchecked")
	public final Map<String, Object> load(Reader reader) throws IOException
	{
		try
		{
			return (Map<String, Object>) JSON_PARSER.parse(reader);
		}
		catch (ParseException ex)
		{
			throw new Error(ex);
		}
	}

	@Override
	public final void save(File file) throws IOException
	{
		FileWriter fileWriter = new FileWriter(file, false);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		bufferedWriter.write(JSONObject.toJSONString(this.data));
		bufferedWriter.close();
		fileWriter.close();
	}
}
