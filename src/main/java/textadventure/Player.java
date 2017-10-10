package textadventure;

import textadventure.scenario.Scenario;

public interface Player extends LivingBeing
{

	/**
	 * Returns the name of the {@link Player}.
	 *
	 * @return The name of the {@link Player}.
	 */
	String getName();

	/**
	 * Delegates a game decision to the {@link Player}.
	 *
	 * @param gameController The {@link GameController} to return any decisions to.
	 * @param scenario       The scenario the {@link Player} is in.
	 */
	void takeTurn(GameController gameController, GameInterface gameInterface, Scenario scenario);
}
