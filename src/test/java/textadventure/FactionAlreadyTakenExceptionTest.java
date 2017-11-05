package textadventure;

import com.google.common.collect.ImmutableList;
import org.junit.Test;
import textadventure.characters.Character;
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

		@Override public boolean hasWon(Game state)
		{
			return false;
		}

		@Override public boolean hasLost(Game state)
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

		@Override public String getName()
		{
			return null;
		}

		@Override public ImmutableList<Character> getActiveCharacters()
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