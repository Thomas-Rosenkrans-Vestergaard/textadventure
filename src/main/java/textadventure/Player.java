package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.rooms.Room;
import textadventure.scenario.Scenario;

import java.util.function.Consumer;

public interface Player extends Person
{

	/**
	 * Returns the name of the {@link Player}.
	 *
	 * @return The name of the {@link Player}.
	 */
	String getName();

	/**
	 * Returns the current location of the {@link Player}.
	 *
	 * @return The current location of the {@link Player}.
	 */
	Room getCurrentLocation();

	/**
	 * Updates the current location {@link Player}.
	 *
	 * @param currentLocation The current location of the {@link Player}.
	 */
	void setCurrentLocation(Room currentLocation);

	/**
	 * Requests an {@link Action} to the provided {@link Scenario}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} to respond to.
	 * @param callback The {@link Consumer} to use when responding to the request for {@link Action}.
	 */
	void takeTurn(Game game, Scenario scenario, Consumer<Action> callback);
}
