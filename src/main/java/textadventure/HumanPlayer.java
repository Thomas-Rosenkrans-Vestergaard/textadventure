package textadventure;

import textadventure.actions.ActionRequestCallback;

/**
 * Human implementation of the {@link Player} interface. The
 */
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
	 * @param callback The select to use after having chosen an {@link textadventure.actions.Action}.
	 */
	@Override public void takeTurn(Game game, ActionRequestCallback callback)
	{
		game.getGameInterface().onActionRequest(game, this, callback::respond);
	}
}
