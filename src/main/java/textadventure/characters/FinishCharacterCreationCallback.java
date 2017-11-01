package textadventure.characters;

@FunctionalInterface
public interface FinishCharacterCreationCallback
{

	/**
	 * Allows the {@link textadventure.ui.GameInterface} to finish creating {@link Character}s for a
	 * {@link textadventure.Player}.
	 *
	 * @throws TooFewCharactersException When too few {@link Character} we provided to the
	 *                                   {@link CharacterCreationCallback}.
	 */
	void respond() throws TooFewCharactersException;
}
