package textadventure;

import textadventure.characters.Character;
import textadventure.items.Item;

/**
 * Represents an {@link Item} that can heal a {@link Character}.
 */
public interface HealingSource
{

	/**
	 * Returns the amount of HP the {@link HealingSource} provides.
	 *
	 * @return The amount of HP the {@link HealingSource} provides.
	 */
	int getHealingAmount();
}
