package textadventure.rooms;

import textadventure.BasePropertyContainer;

/**
 * An implementation of the {@link Room} interface.
 */
public class BaseRoom extends BasePropertyContainer implements Room
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
	 * The {@link Floor} of the {@link Room}. This object is an {@link textadventure.items.Inventory}
	 * {@link textadventure.Character}s can drop {@link textadventure.items.Item}s onto.
	 */
	private final Floor floor;

	/**
	 * Creates a new {@link BaseRoom}.
	 *
	 * @param name        The name of the {@link Room}.
	 * @param description The description of the {@link Room}.
	 * @param floor       The {@link Floor} of the {@link Room}.
	 */
	public BaseRoom(String name, String description, Floor floor)
	{
		this.name = name;
		this.description = description;
		this.floor = floor;
	}

	/**
	 * Creates a new {@link BaseRoom}.
	 *
	 * @param name        The name of the {@link Room}.
	 * @param description The description of the {@link Room}.
	 */
	public BaseRoom(String name, String description)
	{
		this(name, description, new Floor());
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

	/**
	 * Returns the {@link Floor} of the {@link Room}.
	 *
	 * @return The {@link Floor} of the {@link Room}.
	 */
	@Override public Floor getRoomFloor()
	{
		return floor;
	}
}
