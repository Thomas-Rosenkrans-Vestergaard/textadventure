package textadventure;

import textadventure.actions.ActionException;
import textadventure.scenario.Scenario;

public class HumanPlayer extends AbstractPlayer
{

	/**
	 * Creates a new human controlled player.
	 *
	 * @param name The name of the {@link HumanPlayer}.
	 */
	public HumanPlayer(String name, Room currentLocation, int maxHealth)
	{
		super(name, currentLocation, maxHealth);
	}

	@Override public void takeTurn(GameController gameController, GameInterface gameInterface, Scenario scenario)
	{
		try {
			gameInterface.onActionRequest(gameController, this, scenario);
		} catch (ActionException e) {
			throw new IllegalStateException(e);
		}
	}
}
