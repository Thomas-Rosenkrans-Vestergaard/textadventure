package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionResponse;
import textadventure.ui.UserInterface;

public class HumanPlayer extends AbstractPlayer
{

	/**
	 * The {@link Game} instance.
	 */
	private Game game;

	private ActionResponse response;

	/**
	 * Creates a new human controlled player.
	 *
	 * @param character The {@link Character} that the {@link HumanPlayer} controls.
	 */
	public HumanPlayer(Character character)
	{
		super(character);
	}

	/**
	 * Delegates a turn to the {@link Player}.
	 *
	 * @param game     The {@link Game} instance.
	 */
	@Override
	public void takeTurn(Game game, ActionResponse response)
	{
		this.response = response;
		this.game = game;
		game.getUserInterface().onActionRequest(game, this, this::handleAction);
	}

	/**
	 * Handles the {@link Action} response from the {@link UserInterface}.
	 *
	 * @param action The {@link Action} response.
	 */
	private void handleAction(Action action)
	{
		response.respond(action);
	}
}
