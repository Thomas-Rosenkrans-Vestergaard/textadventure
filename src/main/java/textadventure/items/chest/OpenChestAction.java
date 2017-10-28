package textadventure.items.chest;

import textadventure.Character;
import textadventure.Game;
import textadventure.actions.ActionPerformCallback;

/**
 * {@link textadventure.actions.Action} that allow a {@link textadventure.Character} to open a {@link Chest}.
 */
public class OpenChestAction extends ChestAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link OpenChestAction}.
	 */
	private ActionPerformCallback<OpenChestAction> callback;

	/**
	 * Creates a new {@link OpenChestAction}.
	 *
	 * @param chest    The {@link Chest} to be opened.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link OpenChestAction}.
	 */
	OpenChestAction(Chest chest, ActionPerformCallback<OpenChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link OpenChestAction} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link OpenChestAction}.
	 * @param arguments The arguments provided to the {@link OpenChestAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		Chest.State state = chest.getState();

		if (state == Chest.State.OPEN) {
			setException(new ChestAlreadyOpenException(chest));
			callback.send(game, character, this);
			return;
		}

		if (state == Chest.State.CLOSED) {
			try {
				chest.open();
			} catch (Exception e) {
				setException(e);
			} finally {
				callback.send(game, character, this);
			}
		}
	}
}
