package textadventure;

public class BaseRoom implements Room
{

	/**
	 * The name of the {@link Room}.
	 */
	private final String name;

	/**
	 * The description of the {@link Room}.
	 */
	private final String description;

	/**
	 * Creates a new {@link BaseRoom}.
	 *
	 * @param name        The name of the {@link Room}.
	 * @param description The description of the {@link Room}.
	 */
	BaseRoom(final String name, final String description)
	{
		this.name = name;
		this.description = description;
	}

	@Override public String getName()
	{
		return this.name;
	}

	@Override public String getDescription()
	{
		return description;
	}
}
