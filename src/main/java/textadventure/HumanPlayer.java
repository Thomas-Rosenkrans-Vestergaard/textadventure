package textadventure;

import textadventure.actions.ActionResponse;

public class HumanPlayer extends AbstractPlayer
{

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
		game.getUserInterface().onActionRequest(game, this, (action, arguments) -> response.respond(action, arguments));
	}
}
