package textadventure.highscore;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import textadventure.highscore.HighScoreReader;
import textadventure.highscore.Outcome;
import textadventure.highscore.Score;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.Assert.*;

public class HighScoreReaderTest
{

	private String input = "src/test/java/textadventure/highscore/scores.txt";
	private Path   path  = Paths.get(input);

	@Before
	public void setUp() throws Exception
	{
		PrintWriter writer = new PrintWriter(input);
		writer.println("Thomas,10,WIN");
		writer.println("Kasper,7,LOSS");
		writer.println("Albert,0,WIN");
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
	public void read() throws Exception
	{
		HighScoreReader reader = new HighScoreReader(path);
		List<Score>     scores = reader.read();

		assertEquals(3, scores.size());

		assertTrue(scores.get(0).getName().equals("Thomas"));
		assertTrue(scores.get(1).getName().equals("Kasper"));
		assertTrue(scores.get(2).getName().equals("Albert"));

		assertEquals((long) 10, scores.get(0).getScore());
		assertEquals((long) 7, scores.get(1).getScore());
		assertEquals((long) 0, scores.get(2).getScore());

		assertSame(Outcome.WIN, scores.get(0).getOutcome());
		assertSame(Outcome.LOSS, scores.get(1).getOutcome());
		assertSame(Outcome.WIN, scores.get(2).getOutcome());
	}
}