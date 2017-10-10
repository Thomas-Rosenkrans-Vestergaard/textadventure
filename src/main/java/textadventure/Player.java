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
     * Returns the {@link Player}'s current location.
     * @return currentLocation of the {@link Player}.
     */
    public Room getCurrentLocation();

    /**
     * Sets the {@link Player's}'s current location.
     * @param currentLocation The current location of the {@link Player}.
     */
    public void setCurrentLocation(Room currentLocation);

	/**
	 * Delegates a game decision to the {@link Player}.
	 *
	 * @param gameController The {@link GameController} to return any decisions to.
	 * @param scenario       The scenario the {@link Player} is in.
	 */
	void takeTurn(GameController gameController, GameInterface gameInterface, Scenario scenario);
}
