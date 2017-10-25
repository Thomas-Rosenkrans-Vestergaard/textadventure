package textadventure;

import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class HighScoreWriterTest
{

	private String input = "src/test/java/textadventure/scores.txt";
	private Path   path  = Paths.get(input);

	@Before
	public void setUp() throws Exception
	{
		PrintWriter writer = new PrintWriter(input);
		writer.println("Thomas,10");
		writer.println("Albert,0");
		writer.println("Kasper,7");
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

		assertTrue(writer.put("Mark", 19, false));
		assertFalse(writer.put("Mark", 20, false));

		assertTrue(writer.put("Thomas", 100, true));
		assertTrue(writer.put("Jens", 200, true));

		writer.save();

		HighScoreReader      reader = new HighScoreReader(path);
		Map<String, Integer> result = reader.read();

		assertEquals(7, (long) result.get("Kasper"));
		assertEquals(0, (long) result.get("Albert"));
		assertEquals(0, (long) result.get("Albert"));
		assertEquals(19, (long) result.get("Mark"));
		assertEquals(100, (long) result.get("Thomas"));
		assertEquals(200, (long) result.get("Jens"));
	}

	@Test
	public void remove() throws Exception
	{
		HighScoreWriter writer = new HighScoreWriter(path);

		assertTrue(writer.remove("Albert"));
		assertFalse(writer.remove("SOME_STRING"));

		writer.save();

		HighScoreReader reader = new HighScoreReader(path);
		assertEquals(2, reader.read().size());
	}

	@Test
	public void save() throws Exception
	{
		HighScoreReader reader = new HighScoreReader(path);
		HighScoreWriter writer = new HighScoreWriter(path);

		assertEquals(3, reader.read().size());
		writer.put("Jens", 32, true);
		assertEquals(3, reader.read().size());
		writer.save();
		assertEquals(4, reader.read().size());

		Map<String, Integer> saved         = reader.read();
		int                  index         = 0;
		String[]             expectedNames = {"Jens", "Thomas", "Kasper", "Albert"};
		for (Map.Entry<String, Integer> entry : saved.entrySet()) {
			assertEquals(expectedNames[index], entry.getKey());
			index++;
		}
	}
}