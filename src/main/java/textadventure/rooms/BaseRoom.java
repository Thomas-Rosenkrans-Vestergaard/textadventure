package textadventure.rooms;

import textadventure.AbstractPropertyContainer;

import java.util.ArrayList;
import java.util.List;

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
	 * List of {@link RoomFeature}s in the {@link Room}.
	 */
	private final List<RoomFeature> features = new ArrayList<>();

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
		features.forEach(feature -> {
			builder.append(feature.getRoomFeatureDescription());
			builder.append('\n');
		});

		return builder.toString();
	}
}
