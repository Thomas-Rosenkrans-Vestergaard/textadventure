package textadventure.combat;

import textadventure.GameException;

/**
 * Thrown when a {@link textadventure.characters.Character} attempts to perform {@link AttackAction} while no target
 * {@link textadventure.characters.Character} could be found.
 */
public class NoTargetsException extends GameException
{

}
