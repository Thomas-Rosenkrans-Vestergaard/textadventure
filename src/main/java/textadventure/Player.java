package textadventure;

import java.util.function.Consumer;

public interface Player
{

	/**
	 * Returns the {@link Character} being played by the {@link Player}.
	 *
	 * @return The {@link Character} being played by the {@link Player}.
	 */
	Character getCharacter();

	/**
	 * Delegates a turn to the {@link Player}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param callback The callback to use for returning an appropriate {@link Action}.
	 */
	void takeTurn(Game game, Consumer<Action> callback);
}
