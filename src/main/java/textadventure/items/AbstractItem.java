package textadventure.items;

/**
 * Abstract implementation of the {@link Item} interface.
 */
public abstract class AbstractItem implements Item
{

	/**
	 * The name of the {@link Item}.
	 */
	private String name;

	/**
	 * The description of the {@link Item}.
	 */
	private String description;

	/**
	 * Creates a new {@link AbstractItem}.
	 *
	 * @param name        The name of the {@link Item}.
	 * @param description The description of the {@link Item}.
	 */
	public AbstractItem(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	/**
	 * Returns the type of {@link Class} of the {@link ItemType}.
	 *
	 * @return The type of {@link Class} of the {@link ItemType}.
	 */
	@Override public Class<?> getItemTypeClass()
	{
		return getClass();
	}

	/**
	 * Returns the name of the {@link ItemType}.
	 *
	 * @return The name of the {@link ItemType}.
	 */
	@Override public String getItemTypeName()
	{
		return name;
	}

	/**
	 * Returns the description of the {@link ItemType}.
	 *
	 * @return The description of the {@link ItemType}.
	 */
	@Override public String getItemTypeDescription()
	{
		return description;
	}

	@Override public int hashCode()
	{
		return getClass().hashCode();
	}
}
