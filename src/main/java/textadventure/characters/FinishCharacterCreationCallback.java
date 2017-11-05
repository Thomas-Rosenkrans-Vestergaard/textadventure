package textadventure.characters;

import textadventure.GameInterface;

@FunctionalInterface
public interface FinishCharacterCreationCallback
{

	/**
	 * Allows the {@link GameInterface} to finish creating {@link Character}s for a
	 * {@link textadventure.Player}.
	 *
	 * @throws TooFewCharactersException When too few {@link Character} we provided to the
	 *                                   {@link CharacterCreationCallback}.
	 */
	void respond() throws TooFewCharactersException;
}
