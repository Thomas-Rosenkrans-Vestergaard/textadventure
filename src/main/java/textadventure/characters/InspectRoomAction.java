package textadventure.characters;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.rooms.Room;

import java.util.HashSet;
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
}
