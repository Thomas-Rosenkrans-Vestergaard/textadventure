package textadventure.characters;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.rooms.Room;

/**
 * {@link textadventure.actions.Action} allowing a {@link Character} to see the other {@link Character}s in the
 * {@link Room}.
 */
public class InspectRoomAction extends AbstractAction
{

	/**
	 * The {@link Character}s in the {@link Room}. The {@link Character} passed to
	 * {@link InspectRoomAction::perform()} is not included in the {@link ImmutableSet}.
	 */
	private ImmutableSet<Character> characters;

	/**
	 * Performs the {@link InspectRoomAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link InspectRoomAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link InspectRoomAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		Room                            room    = character.getCurrentLocation();
		ImmutableSet.Builder<Character> builder = new ImmutableSet.Builder<>();
		for (Character c : room.getCharacters())
			if (c != character)
				builder.add(c);
		this.characters = builder.build();
		responses.onInspectRoomAction(character, this);
	}

	/**
	 * Returns an {@link ImmutableSet} {@link Character}s in the {@link Room}. The {@link Character} passed to
	 * {@link InspectRoomAction::perform()} is not included in the {@link ImmutableSet}.
	 *
	 * @return The {@link ImmutableSet} {@link Character}s in the {@link Room}. The {@link Character} passed to
	 * {@link InspectRoomAction::perform()} is not included in the {@link ImmutableSet}.
	 */
	public ImmutableSet<Character> getCharacters()
	{
		return characters;
	}
}
