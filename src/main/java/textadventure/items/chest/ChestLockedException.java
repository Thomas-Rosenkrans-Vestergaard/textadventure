package textadventure.items.chest;

import textadventure.GameException;

/**
 * Thrown by {@link Chest} when some {@link textadventure.actions.Action} attempted to open or close a locked {@link Chest}.
 */
public class ChestLockedException extends GameException
{

}
