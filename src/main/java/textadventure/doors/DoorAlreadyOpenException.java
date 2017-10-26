package textadventure.doors;

import textadventure.GameException;

/**
 * Thrown by {@link Door}s when a player attempted to open an already open {@link Door}.
 */
class DoorAlreadyOpenException extends GameException
{

}
