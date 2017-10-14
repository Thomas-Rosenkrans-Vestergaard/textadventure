package textadventure.rooms;

import textadventure.AbstractPropertyContainer;

/**
 * An implementation of the {@link Room} interface.
 */
public class BaseRoom extends AbstractPropertyContainer implements Room
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
	public BaseRoom(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	/**
	 * Returns the name of the {@link Room}.
	 *
	 * @return The name of the {@link Room}.
	 */
	@Override public String getRoomName()
	{
		return name;
	}

	/**
	 * Returns the description of the {@link Room}.
	 *
	 * @return The description of the {@link Room}.
	 */
	@Override public String getRoomDescription()
	{
		return description;
	}
}
