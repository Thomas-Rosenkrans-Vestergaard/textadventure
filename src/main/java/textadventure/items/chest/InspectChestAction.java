package textadventure.items.chest;

import textadventure.Character;
import textadventure.actions.ActionPerformCallback;
import textadventure.ui.GameInterface;

/**
 * {@link textadventure.actions.Action} that allows a {@link textadventure.Character} to inspect a {@link Chest}.
 */
public class InspectChestAction extends ChestAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link InspectChestAction}.
	 */
	private ActionPerformCallback<InspectChestAction> callback;

	/**
	 * Creates a new {@link InspectChestAction}.
	 *
	 * @param chest    The {@link Chest} to be inspected.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link InspectChestAction}.
	 */
	public InspectChestAction(Chest chest, ActionPerformCallback<InspectChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link InspectChestAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link InspectChestAction}.
	 * @param arguments     The arguments provided to the {@link InspectChestAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Chest.State state = chest.getState();

		if (state == Chest.State.CLOSED) {
			setException(new ChestClosedException(chest));
			callback.send(character, this);
			return;
		}

		if (state == Chest.State.OPEN) {
			callback.send(character, this);
			return;
		}

		throw new UnsupportedOperationException();
	}
}
