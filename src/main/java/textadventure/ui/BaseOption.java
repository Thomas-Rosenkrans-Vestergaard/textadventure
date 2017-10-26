package textadventure.ui;

/**
 * The default implementation of the {@link Option} interface.
 */
public class BaseOption implements Option
{

	/**
	 * The name of the {@link Option}.
	 */
	private String name;

	/**
	 * The description of the {@link Option}.
	 */
	private String description;

	/**
	 * Creates a new {@link BaseOption}.
	 *
	 * @param name        The name of the {@link Option}.
	 * @param description The description of the {@link Option}.
	 */
	public BaseOption(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	/**
	 * Returns the name that should be displayed in the {@link Select} or {@link MultiSelect}.
	 *
	 * @return The name that should be displayed in the {@link Select} or {@link MultiSelect}.
	 */
	@Override public String getOptionName()
	{
		return name;
	}

	/**
	 * Returns the name that should be displayed in the {@link Select} or {@link MultiSelect}.
	 *
	 * @return The name that should be displayed in the {@link Select} or {@link MultiSelect}.
	 */
	@Override public String getOptionDescription()
	{
		return description;
	}
}
