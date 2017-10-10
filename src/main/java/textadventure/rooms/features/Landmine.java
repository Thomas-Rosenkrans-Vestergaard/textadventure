package textadventure.rooms.features;

public class Landmine implements RoomFeature
{

	/**
	 * The {@link Location} of the {@link Landmine} in the {@link textadventure.rooms.Room}.
	 */
	public enum Location
	{
		NORTH,
		SOUTH,
		EAST,
		WEST,
	}

	/**
	 * The {@link State} of the {@link Landmine}.
	 */
	public enum State
	{
		ACTIVE,
		INACTIVE,
	}

	/**
	 * The {@link Location} of the {@link Landmine} in the {@link textadventure.rooms.Room}.
	 */
	private Location location;

	/**
	 * Creates a new {@link Landmine}.
	 *
	 * @param location The {@link Location} of the {@link Landmine} in the {@link textadventure.rooms.Room}.
	 */
	public Landmine(Location location)
	{
		this.location = location;
	}

	/**
	 * Returns the description of the {@link Landmine}.
	 *
	 * @return The description of the {@link Landmine}.
	 */
	@Override public String getDescription()
	{
		return "You spot a landmine in the " + location + " side of the room";
	}
}
