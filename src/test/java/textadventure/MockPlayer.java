package textadventure;

import textadventure.scenario.Scenario;
import textadventure.ui.UI;

public class MockPlayer extends AbstractPlayer
{

	/**
	 * Creates a new {@link MockPlayer}.
	 */
	public MockPlayer()
	{
		super("MockPlayer", new MockRoom());
	}

	/**
	 * Delegates a game decision to the {@link Player}.
	 *
	 * @param gameController The {@link Game} to return any decisions to.
	 * @param gameInterface
	 * @param scenario       The scenario the {@link Player} is in.
	 */
	@Override public void takeTurn(Game gameController, UI gameInterface, Scenario scenario)
	{
		throw new UnsupportedOperationException();
	}
}
