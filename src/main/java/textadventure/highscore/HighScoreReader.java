package textadventure.highscore;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Object that can read a file of scores into a {@link Map}.
 */
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
	public List<Score> read() throws IOException
	{
		try {
			List<Score>  scores = new ArrayList<>();
			List<String> lines  = Files.readAllLines(input, Charset.forName("UTF-8"));
			for (String line : lines) {
				String[] split = line.split(",");
				if (split.length != 3) throw new IllegalStateException();
				scores.add(new Score(split[0].trim(), Integer.parseInt(split[1]), Outcome.valueOf(split[2])));
			}

			scores.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));

			return scores;
		} catch (NoSuchFileException e) {
			return new ArrayList<>();
		}
	}
}
