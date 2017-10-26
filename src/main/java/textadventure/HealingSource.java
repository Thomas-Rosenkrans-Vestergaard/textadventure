package textadventure;

import textadventure.items.Item;

/**
 * Represents an {@link Item} that can heal a {@link textadventure.Character}.
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
