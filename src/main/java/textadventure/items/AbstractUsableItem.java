package textadventure.items;

/**
 * Abstract implementation of the {@link UsableItem} interface.
 */
public abstract class AbstractUsableItem extends AbstractItem implements UsableItem
{

	/**
	 * The number of uses the {@link UsableItem} have left.
	 */
	private int numberOfUsesLeft;

	/**
	 * Creates a new {@link AbstractUsableItem}.
	 *
	 * @param name         The name of the {@link UsableItem}.
	 * @param description  The description of the {@link UsableItem}.
	 * @param numberOfUses The number of times the {@link UsableItem} can be used.
	 */
	public AbstractUsableItem(String name, String description, int numberOfUses)
	{
		super(name, description);

		if (numberOfUses < 1)
			throw new IllegalArgumentException("numberOfUses must be positive.");

		this.numberOfUsesLeft = numberOfUses;
	}

	/**
	 * Creates a new {@link AbstractUsableItem}. The {@link UsableItem} can only be used once.
	 *
	 * @param name        The name of the {@link UsableItem}.
	 * @param description The description of the {@link UsableItem}.
	 */
	public AbstractUsableItem(String name, String description)
	{
		this(name, description, 1);
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
