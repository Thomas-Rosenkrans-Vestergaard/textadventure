package textadventure.ui.characterSelection;

import textadventure.Player;
import textadventure.combat.Faction;

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
	 * The {@link Faction} the {@link CharacterCreationTemplate} belongs to.
	 */
	private Faction faction;

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
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * Returns the {@link Faction} the {@link CharacterCreationTemplate} belongs to.
	 *
	 * @return The {@link Faction} the {@link CharacterCreationTemplate} belongs to.
	 */
	public Faction getFaction()
	{
		return this.faction;
	}

	/**
	 * Sets the {@link Faction} the {@link CharacterCreationTemplate} belongs to.
	 *
	 * @param faction The {@link Faction} the {@link CharacterCreationTemplate} belongs to.
	 */
	public void setFaction(Faction faction)
	{
		this.faction = faction;
	}
}
