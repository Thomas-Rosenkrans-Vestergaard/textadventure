package textadventure.characters;

import textadventure.GameException;

/**
 * Thrown when a {@link CharacterCreationTemplate} with a take name provided to the {@link CharacterCreationCallback} function.
 */
public class CharacterNameTakenException extends GameException
{

	/**
	 * The taken name.
	 */
	private String name;

	/**
	 * The {@link Character} with the taken name.
	 */
	private CharacterCreationTemplate character;

	/**
	 * Creates a new {@link CharacterNameTakenException}.
	 *
	 * @param character The {@link CharacterCreationTemplate} with the taken name.
	 */
	public CharacterNameTakenException(CharacterCreationTemplate character)
	{
		this.name = character.getName();
		this.character = character;
	}

	/**
	 * Returns the taken name.
	 *
	 * @return The taken name.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Returns the {@link CharacterCreationTemplate} with the taken name.
	 *
	 * @return The {@link CharacterCreationTemplate} with the taken name.
	 */
	public CharacterCreationTemplate getCharacter()
	{
		return this.character;
	}
}
