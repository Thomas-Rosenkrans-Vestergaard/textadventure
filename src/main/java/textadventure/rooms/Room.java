package textadventure.rooms;

import textadventure.rooms.features.RoomFeature;

import java.util.List;

public interface Room
{

	/**
	 * Returns the name of the {@link Room}.
	 *
	 * @return The name of the {@link Room}.
	 */
	String getName();

	/**
	 * Returns the description of the {@link Room}.
	 *
	 * @return The description of the {@link Room}.
	 */
	String getDescription();

	/**
	 * Returns the {@link RoomFeature}s of the {@link Room}.
	 *
	 * @return The {@link RoomFeature}s of the {@link Room}.
	 */
	List<RoomFeature> getFeatures();
}
