package textadventure.rooms;

import textadventure.rooms.features.RoomFeature;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * An implementation of the {@link Room} interface.
 */
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
	 * The {@link RoomFeature}s of the {@link Room}.
	 */
	private final List<RoomFeature> features;

	/**
	 * Creates a new {@link BaseRoom}.
	 *
	 * @param name        The name of the {@link Room}.
	 * @param description The description of the {@link Room}.
	 * @param features    The {@link RoomFeature}s of the {@link Room}.
	 */
	public BaseRoom(String name, String description, RoomFeature... features)
	{
		this(name, description, Arrays.asList(features));
	}

	/**
	 * Creates a new {@link BaseRoom}.
	 *
	 * @param name        The name of the {@link Room}.
	 * @param description The description of the {@link Room}.
	 * @param features    The {@link RoomFeature}s of the {@link Room}.
	 */
	public BaseRoom(String name, String description, List<RoomFeature> features)
	{
		this.name = name;
		this.description = description;
		this.features = features;
	}

	/**
	 * Returns the name of the {@link Room}.
	 *
	 * @return The name of the {@link Room}.
	 */
	@Override public String getName()
	{
		return name;
	}

	/**
	 * Returns the description of the {@link Room}.
	 *
	 * @return The description of the {@link Room}.
	 */
	@Override public String getDescription()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(description);
		builder.append('\n');
		for (RoomFeature feature : features) {
			builder.append(feature.getDescription());
			builder.append('\n');
		}

		return builder.toString();
	}

	/**
	 * Returns the {@link RoomFeature}s of the {@link Room}.
	 *
	 * @return The {@link RoomFeature}s of the {@link Room}.
	 */
	@Override public List<RoomFeature> getFeatures()
	{
		return Collections.unmodifiableList(features);
	}
}
