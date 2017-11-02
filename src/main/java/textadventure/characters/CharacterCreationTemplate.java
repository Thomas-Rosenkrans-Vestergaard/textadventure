package textadventure.characters;

/**
 * Used when creating new {@link Character}s using the
 * <p>
 * {@link textadventure.ui.GameInterface#onCharacterCreation(Character)} method.
 */
public class CharacterCreationTemplate
{

	/**
	 * The name of the {@link CharacterCreationTemplate}.
	 */
	private String name;

	/**
	 * Returns the name of the {@link CharacterCreationTemplate}.
	 *
	 * @return The name of the {@link CharacterCreationTemplate}.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Sets the name of the {@link CharacterCreationTemplate}.
	 *
	 * @param name The name of the {@link CharacterCreationTemplate}.
	 * @return <code>this</code>
	 */
	public CharacterCreationTemplate setName(String name)
	{
		this.name = name;

		return this;
	}
}
