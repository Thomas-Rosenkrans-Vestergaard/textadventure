package textadventure.items.chest;

import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to inspect a {@link Chest}.
 */
public class InspectChestAction extends ChestAction
{

	/**
	 * Creates a new {@link InspectChestAction}.
	 *
	 * @param chest The {@link Chest} to be inspected.
	 */
	public InspectChestAction(Chest chest)
	{
		super(chest);
	}

	/**
	 * Performs the {@link InspectChestAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link InspectChestAction}.
	 * @param arguments The arguments provided to the {@link InspectChestAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link InspectChestAction}.
	 */
	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		Chest.State state = chest.getState();

		if (state == Chest.State.CLOSED) {
			setException(new ChestClosedException(chest));
			responses.onInspectChestAction(character, this);
			return;
		}

		if (state == Chest.State.OPEN) {
			responses.onInspectChestAction(character, this);
			return;
		}

		throw new UnsupportedOperationException();
	}
}
