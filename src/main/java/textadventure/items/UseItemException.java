package textadventure.items;

/**
 * Represents an error that happened while calling the {@link Item#use} method.
 */
public class UseItemException extends Exception
{

	/**
	 * The {@link Item} that was used.
	 */
	private Item item;

	/**
	 * The {@link Object} that the {@link Item} was used on.
	 */
	private Object object;

	/**
	 * Creates a new {@link UseItemException}.
	 *
	 * @param item   The {@link Item} that was used.
	 * @param object The {@link Object} that the {@link Item} was used on.
	 */
	public UseItemException(Item item, Object object)
	{
		this.item = item;
		this.object = object;
	}

	/**
	 * The {@link Item} that was used.
	 *
	 * @return the {@link Item} that was used.
	 */
	public Item getItem()
	{
		return this.item;
	}

	/**
	 * The {@link Object} that the {@link Item} was used on.
	 *
	 * @return the {@link Object} that the {@link Item} was used on.
	 */
	public Object getObject()
	{
		return this.object;
	}
}
