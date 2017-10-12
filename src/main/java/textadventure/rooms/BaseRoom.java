package textadventure.rooms;

import com.google.common.collect.ImmutableMap;
import textadventure.actions.Focusable;
import textadventure.rooms.features.RoomFeature;

import java.util.*;
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
	 * The {@link Focusable} objects in the {@link Room}.
	 */
	private final Map<String, Focusable> focusable;

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
		this.focusable = new HashMap<>();
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
		if (feature instanceof Focusable) {
			Focusable focusable = (Focusable) feature;
			this.focusable.put(focusable.getIdentifier(), focusable);
		}
	}

	/**
	 * Returns an {@link ImmutableMap} of the {@link Focusable} objects in the {@link Room}.
	 *
	 * @return The {@link ImmutableMap} of the {@link Focusable} objects in the {@link Room}.
	 */
	@Override public ImmutableMap<String, Focusable> getFocusable()
	{
		return new ImmutableMap.Builder<String, Focusable>().putAll(focusable).build();
	}
}
