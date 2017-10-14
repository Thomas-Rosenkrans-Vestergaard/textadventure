package textadventure.items;

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
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	@Override public String getItemName()
	{
		return name;
	}

	/**
	 * Returns a description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	@Override public String getItemDescription()
	{
		return description;
	}

	/**
	 * Returns the name that should be displayed in the {@link textadventure.ui.Select}.
	 *
	 * @return The name that should be displayed in the {@link textadventure.ui.Select}.
	 */
	@Override public String getOptionName()
	{
		return name;
	}

	/**
	 * Returns the description of the {@link textadventure.ui.Option}. Informs the user what selecting the
	 * {@link textadventure.ui.Option} will do.
	 *
	 * @return The description of the {@link textadventure.ui.Option}. Informs the user what selecting the
	 * {@link textadventure.ui.Option} will do.
	 */
	@Override public String getOptionDescription()
	{
		return description;
	}
}
