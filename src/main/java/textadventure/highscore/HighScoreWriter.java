package textadventure.highscore;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Object that can write a {@link Map} of scores to file.
 */
public class HighScoreWriter
{
	/**
	 * The path of the file where the high-score is saved.
	 */
	private Path output;

	/**
	 * The scores saved using the method.
	 */
	private List<Score> scores;

	/**
	 * Creates a new {@link HighScoreWriter} at the provided {@link Path}.
	 *
	 * @param output The {@link Path} to the high score file.
	 * @throws IOException When the scores on file could not be read.
	 */
	public HighScoreWriter(Path output) throws IOException
	{
		this.output = output;
		HighScoreReader reader = new HighScoreReader(output);
		scores = reader.read();
	}

	/**
	 * Inserts a new high score record.
	 *
	 * @param score The score the player achieved.
	 */
	public void put(Score score)
	{
		this.scores.add(score);
	}

	/**
	 * Writes the changes to the file at the provided {@link Path}.
	 *
	 * @throws IOException When we could not put to the file.
	 */
	public void save() throws IOException
	{
		this.scores.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
		List<String> lines = new ArrayList<>();
		for (Score score : scores)
			lines.add(score.toString());


		try {
			write(lines);
		} catch (NoSuchFileException e) {
			new File(output.toString());
			write(lines);
		}
	}

	private void write(List<String> lines) throws IOException
	{
		Files.write(output, lines, Charset.forName("UTF-8"));
	}
}
