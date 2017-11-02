package textadventure.highscore;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ScoreTest
{
	@Test
	public void getName() throws Exception
	{
		String expected = "Name";
		Score  score    = new Score(expected, 0, null);
		assertSame(expected, score.getName());
	}

	@Test
	public void getScore() throws Exception
	{
		int   expected = 1;
		Score score    = new Score(null, expected, null);
		assertSame(expected, score.getScore());
	}

	@Test
	public void getOutcome() throws Exception
	{
		Outcome outcome = Outcome.WIN;
		Score   score   = new Score(null, 1, outcome);
		assertSame(outcome, score.getOutcome());
	}

	@Test
	public void testToString() throws Exception
	{
		assertEquals("Name,50,WIN", new Score("Name", 50, Outcome.WIN).toString());
		assertEquals("Name,0,LOSS", new Score("Name", 0, Outcome.LOSS).toString());
	}
}