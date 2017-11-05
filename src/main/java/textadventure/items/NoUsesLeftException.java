package textadventure.items;

import textadventure.GameException;
import textadventure.characters.Character;

/**
 * Thrown when attempting to use an {@link UsableItem} with no uses left.
 */
public class NoUsesLeftException extends GameException
{

	/**
	 * The {@link UsableItem} that was attempted used.
	 */
	private UsableItem item;

	/**
	 * The {@link Character} the {@link UsableItem} was used on.
	 */
	private Character character;

	/**
	 * Creates a new {@link NoUsesLeftException}.
	 *
	 * @param item      The {@link UsableItem} that was attempted used.
	 * @param character The {@link Character} the {@link UsableItem} was used on.
	 */
	public NoUsesLeftException(UsableItem item, Character character)
	{
		this.item = item;
		this.character = character;
	}

	/**
	 * Returns the {@link UsableItem} that was attempted used.
	 *
	 * @return The {@link UsableItem} that was attempted used.
	 */
	public UsableItem getItem()
	{
		return this.item;
	}

	/**
	 * Returns the {@link Character} the {@link UsableItem} was used on.
	 *
	 * @return The {@link Character} the {@link UsableItem} was used on.
	 */
	public Character getCharacter()
	{
		return this.character;
	}
}
