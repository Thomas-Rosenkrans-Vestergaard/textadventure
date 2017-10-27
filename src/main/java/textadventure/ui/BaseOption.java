package textadventure.ui;

import java.util.HashMap;

/**
 * The default implementation of the {@link Option} interface.
 */
public class BaseOption implements Option
{

	/**
	 * The identifier of the {@link Option}.
	 */
	private int identifier;

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
	 * @param identifier  The identifier of the {@link Option}.
	 * @param name        The name of the {@link Option}.
	 * @param description The description of the {@link Option}.
	 */
	public BaseOption(int identifier, String name, String description)
	{
		this.identifier = identifier;
		this.name = name;
		this.description = description;
	}

	/**
	 * Returns the identifier of the {@link Option}. The identifier also serves as the {@link Option#hashCode()}.
	 *
	 * @return The identifier of the {@link Option}. The identifier also serves as the {@link Option#hashCode()}.
	 */
	@Override public Integer getOptionIdentifier()
	{
		return identifier;
	}

	/**
	 * Returns the name that should be displayed in the {@link Select}.
	 *
	 * @return The name that should be displayed in the {@link Select}.
	 */
	@Override public String getOptionName()
	{
		return name;
	}

	/**
	 * Returns the name that should be displayed in the {@link Select}.
	 *
	 * @return The name that should be displayed in the {@link Select}.
	 */
	@Override public String getOptionDescription()
	{
		return description;
	}

	@Override public int hashCode()
	{
		return identifier;
	}

	@Override public boolean equals(Object obj)
	{
		if (obj == null || !(obj instanceof Option))
			return false;

		return identifier == ((Option) obj).getOptionIdentifier();
	}
}
