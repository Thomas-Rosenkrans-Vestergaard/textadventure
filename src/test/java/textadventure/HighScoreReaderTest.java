package textadventure;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HighScoreReaderTest
{

	private String input = "src/test/java/textadventure/scores.txt";
	private Path   path  = Paths.get(input);

	@Before
	public void setUp() throws Exception
	{
		PrintWriter writer = new PrintWriter(input);
		writer.println("Thomas,10");
		writer.println("Kasper,7");
		writer.println("Albert,0");
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
		HighScoreReader      reader = new HighScoreReader(path);
		Map<String, Integer> result = reader.read();

		assertEquals(3, result.size());

		assertTrue(result.containsKey("Thomas"));
		assertTrue(result.containsKey("Kasper"));
		assertTrue(result.containsKey("Albert"));

		assertEquals((long) 10, (long) result.get("Thomas"));
		assertEquals((long) 7, (long) result.get("Kasper"));
		assertEquals((long) 0, (long) result.get("Albert"));
	}
}