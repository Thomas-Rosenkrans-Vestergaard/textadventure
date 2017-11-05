package textadventure.characters;

import com.google.common.collect.ImmutableSet;
import textadventure.Property;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.rooms.Room;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * {@link textadventure.actions.Action} allowing a {@link Character} to see the other {@link Character}s in the
 * {@link Room}.
 */
public class InspectRoomAction extends AbstractAction
{

	/**
	 * The {@link Character}s in the {@link Room}. The {@link Character} passed to
	 * {@link InspectRoomAction#perform(Character, ActionResponses)} is not included in the {@link Set}.
	 */
	private Set<Character> characters = new HashSet<>();

	/**
	 * The {@link Property} instances inside the {@link Room} the {@link InspectRoomAction} was performed on.
	 */
	private Set<Class<? extends Property>> properties = new HashSet<>();

	/**
	 * Performs the {@link InspectRoomAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link InspectRoomAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link InspectRoomAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		for (Character c : character.getCurrentLocation().getCharacters())
			if (c != character)
				characters.add(c);

		for (Map.Entry<Class<? extends Property>, Property> entry : character.getCurrentLocation().getProperties().entrySet())
			properties.add(entry.getKey());

		responses.onInspectRoomAction(character, this);
	}

	/**
	 * Returns an {@link ImmutableSet} of the {@link Character}s in the {@link Room}. The {@link Character} passed to
	 * {@link InspectRoomAction#perform(Character, ActionResponses)} is not included in the {@link ImmutableSet}.
	 *
	 * @return The {@link ImmutableSet} of the {@link Character}s in the {@link Room}. The {@link Character} passed to
	 * {@link InspectRoomAction#perform(Character, ActionResponses)} is not included in the {@link ImmutableSet}.
	 */
	public ImmutableSet<Character> getCharacters()
	{
		return ImmutableSet.copyOf(characters);
	}

	/**
	 * Returns the {@link Property} instances inside the {@link Room} the {@link InspectRoomAction} was performed on.
	 *
	 * @return The {@link Property} instances inside the {@link Room} the {@link InspectRoomAction} was performed on.
	 */
	public ImmutableSet<Class<? extends Property>> getProperties()
	{
		return ImmutableSet.copyOf(properties);
	}
}
