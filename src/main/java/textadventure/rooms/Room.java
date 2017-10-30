package textadventure.rooms;

import com.google.common.collect.ImmutableSet;
import textadventure.characters.Character;
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
	Floor getRoomFloor();

	/**
	 * Adds the provided {@link Character} to the {@link Room}.
	 *
	 * @param character The {@link Character} to add to the {@link Room}.
	 * @return True if the {@link Character} could be added.
	 */
	boolean addCharacter(Character character);

	/**
	 * Checks if the provided {@link Character} resides in the {@link Room}.
	 *
	 * @param character The {@link Character} to check for.
	 * @return True if the {@link Character} resides in the {@link Room}. False if the {@link Character} does not
	 * reside in the {@link Room}.
	 */
	boolean hasCharacter(Character character);

	/**
	 * Removes the provided {@link Character} from the {@link Room}.
	 *
	 * @param character The {@link Character} to remove from the {@link Room}.
	 * @return True if a {@link Character} was removed. False otherwise.
	 */
	boolean removeCharacter(Character character);

	/**
	 * Returns an {@link ImmutableSet} of the {@link Character}s in the {@link Room}.
	 *
	 * @return The {@link ImmutableSet} of the {@link Character}s in the {@link Room}.
	 */
	ImmutableSet<Character> getCharacters();
}
