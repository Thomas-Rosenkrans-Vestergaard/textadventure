package textadventure.items.chest;

import textadventure.GameException;

/**
 * Thrown by {@link Chest} when some {@link textadventure.actions.Action} attempted to open an already opened {@link Chest}.
 */
public class ChestAlreadyOpenException extends GameException
{

}
