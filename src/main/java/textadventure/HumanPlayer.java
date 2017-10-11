package textadventure;

import textadventure.actions.Action;
import textadventure.rooms.Room;
import textadventure.scenario.Scenario;
import textadventure.ui.UI;

import java.util.function.Consumer;

public class HumanPlayer extends AbstractPlayer
{

	/**
	 * The callback to use when reporting back to the {@link Game} instance.
	 */
	private Consumer<Action> callback;

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

	/**
	 * Requests an {@link Action} to the provided {@link Scenario}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} to respond to.
	 * @param callback The {@link Consumer} to use when responding to the request for {@link Action}.
	 */
	@Override public void takeTurn(Game game, Scenario scenario, Consumer<Action> callback)
	{
		this.callback = callback;
		game.getUI().onActionRequest(game, this, scenario, this::handleAction);
	}

	/**
	 * Handles the {@link Action} response from the {@link UI}.
	 *
	 * @param action The {@link Action} response.
	 */
	private void handleAction(Action action)
	{
		callback.accept(action);
	}
}
