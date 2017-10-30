package textadventure.characters;

import textadventure.GameException;

/**
 * Thrown when a {@link Character} attempts to {@link EscapeAction} from the {@link textadventure.Game}. The reasons
 * for the exception could be:
 * - that the {@link Character} doesn't belong to the {@link textadventure.combat.Escapees} or
 * - that the {@link Character} from the {@link textadventure.combat.Escapees} is not in the correct
 * {@link textadventure.rooms.Room}.
 */
public class CharacterCantEscapeException extends GameException
{

}
