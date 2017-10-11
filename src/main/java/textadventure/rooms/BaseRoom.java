package textadventure.rooms;

import com.sun.javafx.UnmodifiableArrayList;
import textadventure.rooms.features.RoomFeature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

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
	 */
	public BaseRoom(String name, String description)
	{
		this.name = name;
		this.description = description;
		this.features = new ArrayList<>();
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
	@Override public Stream<RoomFeature> getFeatures()
	{
		return features.stream();
	}

	/**
	 * Adds the provided {@link RoomFeature} to the {@link Room}.
	 *
	 * @param feature The {@link RoomFeature} to add to the {@link Room}.
	 */
	@Override public void addFeature(RoomFeature feature)
	{
		this.features.add(feature);
	}
}
