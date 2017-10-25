package textadventure;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class HighScoreReader
{

	/**
	 * The path to use when reading high scores.
	 */
	private Path input;

	/**
	 * Creates a new {@link HighScoreReader}.
	 *
	 * @param input The path to use when reading high scores.
	 */
	public HighScoreReader(Path input)
	{
		this.input = input;
	}

	/**
	 * Returns the high scores in the high score file.
	 *
	 * @return A map containing the players and their score.
	 */
	public LinkedHashMap<String, Integer> read() throws IOException
	{
		List<String>                   lines  = Files.readAllLines(input, Charset.forName("UTF-8"));
		LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
		for (String line : lines) {
			String[] split = line.split(",");
			if (split.length != 2) throw new IllegalStateException();
			result.put(split[0].trim(), Integer.parseInt(split[1]));
		}

		return result;
	}
}
