package textadventure.items.chest;

import textadventure.GameException;

/**
 * Thrown by {@link Chest} when some {@link textadventure.actions.Action} attempted to close an already closed {@link Chest}.
 */
public class ChestAlreadyClosedException extends GameException
{

}
