package textadventure.ui.characterSelection;

import textadventure.Player;

/**
 * Used when creating new {@link textadventure.Character}s using the {@link textadventure.ui.GameInterface#onCharacterCreation(Player, int, int, CharacterCreationCallback, FinishCharacterCreationCallback)} method.
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
