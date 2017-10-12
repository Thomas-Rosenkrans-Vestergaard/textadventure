package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;
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
	 * Returns the current focus of the {@link Player}.
	 *
	 * @return The current focus of the {@link Player}.
	 */
	Focusable getFocus();

	/**
	 * Sets the focus of the {@link Player} on the provided {@link Focusable} object.
	 *
	 * @param focus The {@link Focusable} object.
	 */
	void setFocus(Focusable focus);

	/**
	 * Requests an {@link Action} to the provided {@link Scenario}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} to respond to.
	 * @param callback The {@link Consumer} to use when responding to the request for {@link Action}.
	 */
	void takeTurn(Game game, Scenario scenario, Consumer<Action> callback);
}
