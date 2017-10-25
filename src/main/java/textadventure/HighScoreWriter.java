package textadventure;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class HighScoreWriter
{
	/**
	 * The path of the file where the high-score is saved.
	 */
	private Path                     output;


	private HashMap<String, Integer> scores = new HashMap<>();

	public HighScoreWriter(Path output)
	{
		this.output = output;
	}
	/**
	 *
	 * @param name
	 * @param score
	 */
	public void write(String name, int score){
		scores.put(name, score);
	}
	/**
	 *
	 * @throws FileNotFoundException
	 * @throws IOException
	 * Reads the lines of the file containing the scores
	 * Splits each line into name and score
	 */
	public void save() throws FileNotFoundException, IOException{
		List<String> lines = Files.readAllLines(output, Charset.forName("UTF-8"));
		HashMap<String, Integer> saved = new HashMap<>();
		for(String line: lines){
			String[] split = line.split(",");
			if(split.length != 2) throw new IllegalStateException();
			saved.put(split[0].trim(), Integer.parseInt(split[1]));
		}
		/**
		 * gets the name and score from each line
		 */
		for(Map.Entry<String, Integer> entry : scores.entrySet()){
			saved.put(entry.getKey(), entry.getValue());
		}
		lines.clear();
		/**
		 * As long as there are lines in the file, a new line will be added.
		 */
		for(Map.Entry<String, Integer> entry : saved.entrySet())
			lines.add(String.format("%s,%d", entry.getKey(), entry.getValue()));

		Files.write(output, lines, Charset.forName("UTF-8"));
	}
}
