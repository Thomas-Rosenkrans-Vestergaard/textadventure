package textadventure;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class HighScoreWriter
{
	/**
	 * The path of the file where the high-score is saved.
	 */
	private Path output;

	/**
	 * The scores saved using the {@link #put(String, int, boolean)} method.
	 */
	private Map<String, Integer> scores;

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
	 * @param name  The name of the player.
	 * @param score The score the player achieved.
	 */
	public boolean put(String name, int score, boolean force)
	{
		if (!force) {
			if (scores.containsKey(name))
				return false;
			scores.put(name, score);
			return true;
		}

		scores.put(name, score);
		return true;
	}

	/**
	 * Removes a record from the high score file.
	 *
	 * @param name The name of the high score to remove.
	 * @return True when a high score was removed. False otherwise.
	 */
	public boolean remove(String name)
	{
		if (scores.containsKey(name)) {
			scores.remove(name);
			return true;
		}

		return false;
	}

	/**
	 * Writes the changes to the file at the provided {@link Path}.
	 *
	 * @throws IOException When we could not put to the file.
	 */
	public void save() throws IOException
	{
		List<String> lines = new ArrayList<>();
		for (Map.Entry<String, Integer> entry : sort(scores).entrySet())
			lines.add(String.format("%s,%d", entry.getKey(), entry.getValue()));

		Files.write(output, lines, Charset.forName("UTF-8"));
	}

	/**
	 * Sorts the provided {@link HashMap} using the score in descending order.
	 *
	 * @param scores The scores to sort.
	 * @return The sorted scores.
	 */
	private LinkedHashMap<String, Integer> sort(Map<String, Integer> scores)
	{
		List list = new LinkedList(scores.entrySet());

		Collections.sort(list, (o1, o2) ->
				((Comparable) ((Map.Entry) (o2)).getValue()).compareTo(((Map.Entry) (o1)).getValue())
		);

		LinkedHashMap<String, Integer> sortedHashMap = new LinkedHashMap();
		for (Iterator it = list.iterator(); it.hasNext(); ) {
			Map.Entry entry = (Map.Entry) it.next();
			sortedHashMap.put((String) entry.getKey(), (Integer) entry.getValue());
		}

		return sortedHashMap;
	}
}
