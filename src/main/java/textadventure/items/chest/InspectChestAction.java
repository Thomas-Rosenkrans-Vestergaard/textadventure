package textadventure.items.chest;

import textadventure.Character;
import textadventure.Game;
import textadventure.actions.ActionPerformCallback;

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
	InspectChestAction(Chest chest, ActionPerformCallback<InspectChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link InspectChestAction} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link InspectChestAction}.
	 * @param arguments The arguments provided to the {@link InspectChestAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		Chest.State state = chest.getState();

		if (state == Chest.State.CLOSED) {
			setException(new ChestClosedException(chest));
			callback.send(game, character, this);
			return;
		}

		if (state == Chest.State.OPEN) {
			callback.send(game, character, this);
			return;
		}

		throw new UnsupportedOperationException();
	}
}
