package textadventure.rooms;

import textadventure.rooms.features.RoomFeature;
import textadventure.rooms.features.doors.Door;

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

	/**
	 * Adds the provided {@link RoomFeature} to the {@link Room}.
	 *
	 * @param feature The {@link RoomFeature} to add to the {@link Room}.
	 */
	void addFeature(RoomFeature feature);
}
