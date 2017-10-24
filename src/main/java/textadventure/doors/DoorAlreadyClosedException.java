package textadventure.doors;

import textadventure.GameException;

/**
 * Thrown by {@link Door}s when a player attempted to close an already closed {@link Door}.
 */
class DoorAlreadyClosedException extends GameException
{

}
