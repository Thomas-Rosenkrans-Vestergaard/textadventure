package textadventure.rooms;

import textadventure.BasePropertyContainer;

import java.util.ArrayList;
import java.util.List;

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
	private final Floor floor = new Floor();

	/**
	 * The features in the room.
	 */
	private final List<String> features = new ArrayList<>();

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
		StringBuilder builder = new StringBuilder();
		builder.append(description);
		for (String feature : features) {
			builder.append('\n');
			builder.append(feature);
		}

		builder.append('\n');
		return builder.toString();
	}

	/**
	 * Returns the {@link Floor} of the {@link Room}.
	 *
	 * @return The {@link Floor} of the {@link Room}.
	 */
	@Override public Floor getFloor()
	{
		return floor;
	}

	/**
	 * Adds a new feature to the room.
	 *
	 * @param description The description of the feature.
	 */
	@Override public void addFeature(String description)
	{
		features.add(description);
	}
}
