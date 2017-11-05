package textadventure.rooms;

import com.google.common.collect.ImmutableSet;
import textadventure.BaseProperty;
import textadventure.characters.Character;
import textadventure.characters.InspectRoomAction;

import java.util.HashSet;
import java.util.Set;

/**
 * An implementation of the {@link Room} interface.
 */
public class BaseRoom extends BaseProperty implements Room
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
	 * {@link Character}s can drop {@link textadventure.items.Item}s onto.
	 */
	private final Floor floor;

	/**
	 * The {@link Character}s contained currently residing in the {@link Room}.
	 */
	private final Set<Character> characters = new HashSet<>();

	/**
	 * Creates a new {@link BaseRoom}.
	 *
	 * @param name        The name of the {@link Room}.
	 * @param description The description of the {@link Room}.
	 * @param floor       The {@link Floor} of the {@link Room}.
	 */
	public BaseRoom(String name, String description, Floor floor)
	{
		this.name = name;
		this.description = description;
		this.floor = floor;

		putProperty(floor);
		putActionFactory(InspectRoomAction.class, InspectRoomAction::new);
	}

	/**
	 * Creates a new {@link BaseRoom}.
	 *
	 * @param name        The name of the {@link Room}.
	 * @param description The description of the {@link Room}.
	 */
	public BaseRoom(String name, String description)
	{
		this(name, description, new Floor());
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
		return description;
	}

	/**
	 * Returns the {@link Floor} of the {@link Room}.
	 *
	 * @return The {@link Floor} of the {@link Room}.
	 */
	@Override public Floor getRoomFloor()
	{
		return floor;
	}

	/**
	 * Adds the provided {@link Character} to the {@link Room}.
	 *
	 * @param character The {@link Character} to add to the {@link Room}.
	 * @return True if the {@link Character} could be added.
	 */
	public boolean addCharacter(Character character)
	{
		return this.characters.add(character);
	}

	/**
	 * Checks if the provided {@link Character} resides in the {@link Room}.
	 *
	 * @param character The {@link Character} to check for.
	 * @return True if the {@link Character} resides in the {@link Room}. False if the {@link Character} does not
	 * reside in the {@link Room}.
	 */
	public boolean hasCharacter(Character character)
	{
		return this.characters.contains(character);
	}

	/**
	 * Removes the provided {@link Character} from the {@link Room}.
	 *
	 * @param character The {@link Character} to remove from the {@link Room}.
	 * @return True if a {@link Character} was removed. False otherwise.
	 */
	public boolean removeCharacter(Character character)
	{
		return characters.remove(character);
	}

	/**
	 * Returns an {@link ImmutableSet} of the {@link Character}s in the {@link Room}.
	 *
	 * @return The {@link ImmutableSet} of the {@link Character}s in the {@link Room}.
	 */
	public ImmutableSet<Character> getCharacters()
	{
		return ImmutableSet.copyOf(characters);
	}
}
