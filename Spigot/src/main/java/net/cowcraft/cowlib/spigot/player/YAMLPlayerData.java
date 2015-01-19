package net.cowcraft.cowlib.spigot.player;

import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.Map;

public final class YAMLPlayerData extends PlayerData
{

	private static final Yaml YAML = new Yaml();

	public YAMLPlayerData()
	{
		super();
	}

	public YAMLPlayerData(Map<String, Object> data)
	{
		super(data);
	}

	public YAMLPlayerData(File file) throws IOException
	{
		super(file);
	}

	public YAMLPlayerData(Reader reader) throws IOException
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
		return (Map<String, Object>) YAML.load(reader);
	}

	@Override
	public final void save(File file) throws IOException
	{
		FileWriter fileWriter = new FileWriter(file, false);
		BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

		bufferedWriter.write(YAML.dump(this.data));
		bufferedWriter.close();
		fileWriter.close();
	}
}
