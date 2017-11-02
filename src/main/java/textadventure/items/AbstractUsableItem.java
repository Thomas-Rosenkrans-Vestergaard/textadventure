package textadventure.items;

/**
 * Abstract implementation of the {@link UsableItem} interface.
 */
public abstract class AbstractUsableItem implements UsableItem
{

	/**
	 * The number of uses the {@link UsableItem} have left.
	 */
	protected int numberOfUsesLeft;

	/**
	 * Creates a new {@link AbstractUsableItem}.
	 *
	 * @param numberOfUses The number of times the {@link UsableItem} can be used.
	 */
	public AbstractUsableItem(int numberOfUses)
	{
		this.numberOfUsesLeft = numberOfUses;

		if (numberOfUses < 1)
			throw new IllegalArgumentException("numberOfUses must be positive.");
	}

	/**
	 * Creates a new {@link AbstractUsableItem}. The {@link UsableItem} can only be used once.
	 */
	public AbstractUsableItem(String name, String description)
	{
		this(1);
	}

	/**
	 * Returns the number of times the {@link UsableItem} can be used.
	 *
	 * @return The number of times the {@link UsableItem} can be used.
	 */
	@Override public int getNumberOfUsesLeft()
	{
		return numberOfUsesLeft;
	}
}
