package textadventure.highscore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HighScoreWriterTest
{

	private String input = "src/test/java/textadventure/highscore/scores.txt";
	private Path   path  = Paths.get(input);

	@Before
	public void setUp() throws Exception
	{
		PrintWriter writer = new PrintWriter(input);
		writer.println("Thomas,10,WIN");
		writer.println("Albert,0,LOSS");
		writer.println("Kasper,7,WIN");
		writer.close();
	}

	@After
	public void tearDown() throws Exception
	{
		PrintWriter writer = new PrintWriter(input);
		writer.print("");
		writer.close();
	}

	@Test
	public void put() throws Exception
	{
		HighScoreWriter writer = new HighScoreWriter(path);
		writer.put(new Score("Mark", 9, Outcome.WIN));
		writer.save();

		HighScoreReader reader = new HighScoreReader(path);
		List<Score>     scores = reader.read();

		assertEquals(4, scores.size());

		assertEquals("Thomas", scores.get(0).getName());
		assertEquals(10, scores.get(0).getScore());
		assertEquals(Outcome.WIN, scores.get(0).getOutcome());

		assertEquals("Mark", scores.get(1).getName());
		assertEquals(9, scores.get(1).getScore());
		assertEquals(Outcome.WIN, scores.get(1).getOutcome());

		assertEquals("Kasper", scores.get(2).getName());
		assertEquals(7, scores.get(2).getScore());
		assertEquals(Outcome.WIN, scores.get(2).getOutcome());

		assertEquals("Albert", scores.get(3).getName());
		assertEquals(0, scores.get(3).getScore());
		assertEquals(Outcome.LOSS, scores.get(3).getOutcome());
	}

	@Test
	public void save() throws Exception
	{
		HighScoreReader reader = new HighScoreReader(path);
		HighScoreWriter writer = new HighScoreWriter(path);

		writer.put(new Score("Jens", 100, Outcome.WIN));
		writer.save();

		List<Score> saved         = reader.read();
		int         index         = 0;
		String[]    expectedNames = {"Jens", "Thomas", "Kasper", "Albert"};
		for (Score entry : saved) {
			assertEquals(expectedNames[index], entry.getName());
			index++;
		}
	}
}