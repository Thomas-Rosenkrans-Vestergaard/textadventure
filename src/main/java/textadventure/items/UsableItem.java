package textadventure.items;

import textadventure.characters.Character;

public interface UsableItem extends Item
{

	/**
	 * Uses the {@link UsableItem} on the provided {@link textadventure.characters.Character}.
	 *
	 * @param character The {@link textadventure.characters.Character} using the
	 *                  {@link textadventure.characters.Character}.
	 * @throws Exception When something goes wrong while using the {@link UsableItem}.
	 */
	void use(Character character) throws Exception;

	/**
	 * Returns the number of times the {@link UsableItem} can be used.
	 *
	 * @return The number of times the {@link UsableItem} can be used.
	 */
	int getNumberOfUsesLeft();
}
