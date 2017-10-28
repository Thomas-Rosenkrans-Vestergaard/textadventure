package textadventure.items.chest;

import textadventure.Character;
import textadventure.Game;
import textadventure.actions.ActionPerformCallback;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to close a {@link Chest}.
 */
public class CloseChestAction extends ChestAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link CloseChestAction}.
	 */
	private ActionPerformCallback<CloseChestAction> callback;

	/**
	 * Creates a new {@link CloseChestAction}.
	 *
	 * @param chest    The {@link Chest} to be closed.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link CloseChestAction}.
	 */
	CloseChestAction(Chest chest, ActionPerformCallback<CloseChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link CloseChestAction} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link CloseChestAction}.
	 * @param arguments The arguments provided to the {@link CloseChestAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		Chest       chest = getChest();
		Chest.State state = chest.getState();

		if (state == Chest.State.CLOSED) {
			setException(new ChestAlreadyClosedException(chest));
			callback.send(game, character, this);
			return;
		}

		if (state == Chest.State.OPEN) {
			try {
				chest.close();
			} catch (Exception e) {
				setException(e);
			} finally {
				callback.send(game, character, this);
			}
		}
	}
}
