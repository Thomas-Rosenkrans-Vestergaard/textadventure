package textadventure;

import org.junit.Test;
import textadventure.combat.AbstractFaction;
import textadventure.combat.Faction;

import static org.junit.Assert.assertSame;

public class FactionAlreadyTakenExceptionTest
{

	private class FactionImplementation extends AbstractFaction
	{
		public FactionImplementation()
		{
			super(null, null);
		}

		@Override public boolean hasWon(GameState state)
		{
			return false;
		}

		@Override public boolean hasLost(GameState state)
		{
			return false;
		}

		@Override public String getStartingMessage()
		{
			return null;
		}

		@Override public String getEndingMessageOnWin()
		{
			return null;
		}

		@Override public String getEndingMessageOnLoss()
		{
			return null;
		}
	}

	@Test
	public void getFaction() throws Exception
	{
		Faction                      expected  = new FactionImplementation();
		FactionAlreadyTakenException exception = new FactionAlreadyTakenException(expected);
		assertSame(expected, exception.getFaction());
	}
}