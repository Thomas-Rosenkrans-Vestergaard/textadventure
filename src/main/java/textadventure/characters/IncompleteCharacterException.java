package textadventure.characters;

import textadventure.GameException;

/**
 * Thrown when calling {@link CharacterCreationCallback} with an incomplete {@link Character}.
 */
public class IncompleteCharacterException extends GameException
{

	/**
	 * The incomplete {@link CharacterCreationTemplate}.
	 */
	private CharacterCreationTemplate character;

	/**
	 * Creates a new {@link IncompleteCharacterException}.
	 *
	 * @param character The incomplete {@link CharacterCreationTemplate}.
	 */
	public IncompleteCharacterException(CharacterCreationTemplate character)
	{
		this.character = character;
	}

	/**
	 * Returns the incomplete {@link CharacterCreationTemplate}.
	 *
	 * @return The incomplete {@link CharacterCreationTemplate}.
	 */
	public CharacterCreationTemplate getCharacterTemplate()
	{
		return this.character;
	}
}
