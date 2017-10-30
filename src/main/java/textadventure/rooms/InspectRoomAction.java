package textadventure.rooms;

import com.google.common.collect.ImmutableSet;
import textadventure.characters.Character;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionPerformCallback;
import textadventure.ui.GameInterface;

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
	 * The {@link ActionPerformCallback} to invoke after performing the {@link InspectRoomAction}.
	 */
	private ActionPerformCallback<InspectRoomAction> callback;

	/**
	 * Creates a new {@link InspectRoomAction}.
	 *
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link InspectRoomAction}.
	 */
	public InspectRoomAction(ActionPerformCallback<InspectRoomAction> callback)
	{
		this.callback = callback;
	}

	/**
	 * Performs the {@link InspectRoomAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link InspectRoomAction}.
	 * @param arguments     The arguments provided to the {@link InspectRoomAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Room                            room    = character.getCurrentLocation();
		ImmutableSet.Builder<Character> builder = new ImmutableSet.Builder<>();
		for (Character c : room.getCharacters())
			if (c != character)
				builder.add(c);
		this.characters = builder.build();
		callback.send(character, this);
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
