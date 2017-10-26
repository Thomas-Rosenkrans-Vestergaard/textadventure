package textadventure.items;

/**
 * Abstract implementation of the {@link Item} interface.
 */
public abstract class AbstractItem implements ItemType
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
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Returns the description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	public String getDescription()
	{
		return this.description;
	}
}
