package textadventure.lock;

import textadventure.GameException;
import textadventure.characters.Character;

/**
 * Thrown when a {@link Character} must provide a {@link Key}, but the provided value is of a differing
 * type.
 */
public class SelectionNotKeyException extends GameException
{

}
