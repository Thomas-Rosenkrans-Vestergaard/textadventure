package textadventure;

import textadventure.actions.ActionException;
import textadventure.rooms.Room;
import textadventure.scenario.Scenario;

public class HumanPlayer extends AbstractPlayer
{

	/**
	 * Creates a new human controlled player.
	 *
	 * @param name            The name of the {@link HumanPlayer}.
	 * @param currentLocation The {@link Room} the {@link HumanPlayer} is in.
	 */
	public HumanPlayer(String name, Room currentLocation)
	{
		super(name, currentLocation);
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
