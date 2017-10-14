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
}
