package textadventure.rooms;

import textadventure.PropertyContainer;

public interface Room extends PropertyContainer
{

	/**
	 * Returns the name of the {@link Room}.
	 *
	 * @return The name of the {@link Room}.
	 */
	String getRoomName();

	/**
	 * Returns the description of the {@link Room}.
	 *
	 * @return The description of the {@link Room}.
	 */
	String getRoomDescription();

	/**
	 * Returns the {@link Floor} of the {@link Room}.
	 *
	 * @return The {@link Floor} of the {@link Room}.
	 */
	Floor getFloor();

	/**
	 * Adds a new feature to the room.
	 *
	 * @param description The description of the feature.
	 */
	void addFeature(String description);
}
