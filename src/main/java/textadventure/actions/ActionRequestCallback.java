package textadventure.actions;

import textadventure.Property;
import textadventure.Relations;
import textadventure.UnknownActionException;
import textadventure.UnknownPropertyException;
import textadventure.characters.Character;

import java.util.List;

/**
 * Used to request the next {@link Action} for some {@link Character} by their {@link textadventure.Player} leader.
 * The {@link Action} chosen is returned asynchronous using the {@link ActionRequestCallback#respond(List, Class)}
 * method.
 */
@FunctionalInterface
public interface ActionRequestCallback
{

	/**
	 * Callback for the {@link textadventure.ui.GameInterface#onActionRequest(Character, Relations, ActionRequestCallback)}.
	 *
	 * @param properties The path of {@link Property} types leading to the provided {@link Action} type.
	 * @param action     The type of {@link Action} to perform on the last {@link Property}.
	 */
	void respond(List<Class<? extends Property>> properties, Class<? extends Action> action) throws UnknownPropertyException, UnknownActionException;
}
