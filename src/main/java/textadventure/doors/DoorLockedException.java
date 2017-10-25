package textadventure.doors;

import textadventure.*;

/**
 * Thrown by {@link Door}s when some {@link textadventure.actions.Action} attempted to open or close a locked
 * {@link Door}.
 */
class DoorLockedException extends GameException
{

}
