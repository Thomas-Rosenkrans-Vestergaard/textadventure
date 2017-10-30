package textadventure.ui.characterSelection;

import textadventure.characters.Character;
import textadventure.Player;

@FunctionalInterface
public interface CharacterCreationCallback
{

	/**
	 * Allows a {@link Player} to create one or more {@link Character}s to control.
	 *
	 * @param character The {@link CharacterCreationTemplate} returned creates by the {@link Player}.
	 * @throws CharacterNameTakenException  When one of the names of the created {@link Character} was already taken
	 *                                      by another {@link Player}.
	 * @throws IncompleteCharacterException When a {@link Character} was not created correctly.
	 * @throws TooManyCharactersException   When the {@link textadventure.ui.GameInterface} provided one too many
	 *                                      {@link Character}s.
	 */
	void respond(CharacterCreationTemplate character) throws CharacterNameTakenException, IncompleteCharacterException, TooManyCharactersException;
}