package textadventure;

import org.junit.Test;

import java.nio.file.Paths;

import static org.junit.Assert.*;

public class HighScoreWriterTest
{
	@Test
	public void save() throws Exception
	{
		HighScoreWriter writer = new HighScoreWriter(Paths.get("C:\\Users\\Albert Scheel " +
				"Vandel\\Documents\\IdeaProjects\\textadventure\\src\\test\\java\\textadventure\\scores.txt"));
		writer.write("Abe", 1);
		writer.save();
	}

}