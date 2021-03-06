package textadventure.items;

import textadventure.characters.Character;
import textadventure.combat.HealingSource;

public class BandAid extends AbstractUsableItem implements HealingSource
{
	/**
	 * Creates a new {@link BandAid}.
	 */
	public BandAid()
	{
		super(1);
	}

	/**
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	@Override public String getItemTypeName()
	{
		return "band-aid";
	}

	/**
	 * Returns the description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Can be used to stop bleeding from small wounds.";
	}

	/**
	 * Returns the HealingAmount of the {@link Item}
	 *
	 * @return The HealingAmount of the {@link Item}
	 */
	@Override public int getHealingAmount()
	{
		return 15;
	}

	/**
	 * Uses the {@link UsableItem} on the provided {@link Character}.
	 *
	 * @param character The {@link Character} using the
	 *                  {@link Character}.
	 * @throws NoUsesLeftException When the {@link UsableItem} has no more uses left.
	 * @throws UseTimeException    When an unrelated exception occurs during the use of the {@link UsableItem}.
	 */
	@Override public void use(Character character) throws NoUsesLeftException, UseTimeException
	{
		if (numberOfUsesLeft == 0)
			throw new NoUsesLeftException(this, character);

		character.takeHealing(this);
		numberOfUsesLeft--;
	}
}
