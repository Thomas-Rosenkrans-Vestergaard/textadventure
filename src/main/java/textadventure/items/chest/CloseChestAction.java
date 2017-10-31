package textadventure.items.chest;

import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to close a {@link Chest}.
 */
public class CloseChestAction extends ChestAction
{

	/**
	 * Creates a new {@link CloseChestAction}.
	 *
	 * @param chest The {@link Chest} to be closed.
	 */
	public CloseChestAction(Chest chest)
	{
		super(chest);
	}

	/**
	 * Performs the {@link CloseChestAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link CloseChestAction}.
	 * @param arguments The arguments provided to the {@link CloseChestAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link CloseChestAction}.
	 */
	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		Chest       chest = getChest();
		Chest.State state = chest.getState();

		if (state == Chest.State.CLOSED) {
			setException(new ChestAlreadyClosedException(chest));
			responses.onCloseChestAction(character, this);
			return;
		}

		if (state == Chest.State.OPEN) {
			try {
				chest.close();
			} catch (Exception e) {
				setException(e);
			} finally {
				responses.onCloseChestAction(character, this);
				return;
			}
		}

		throw new UnsupportedOperationException();
	}
}
