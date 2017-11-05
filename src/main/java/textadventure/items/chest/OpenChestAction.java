package textadventure.items.chest;

import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

/**
 * {@link textadventure.actions.Action} that allow a {@link Character} to open a {@link Chest}.
 */
public class OpenChestAction extends ChestAction
{

	/**
	 * Creates a new {@link OpenChestAction}.
	 *
	 * @param chest The {@link Chest} to be opened.
	 */
	public OpenChestAction(Chest chest)
	{
		super(chest);
	}

	/**
	 * Performs the {@link OpenChestAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link OpenChestAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link OpenChestAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		Chest.State state = chest.getState();

		if (state == Chest.State.OPEN) {
			setException(new ChestAlreadyOpenException(chest));
			responses.onOpenChestAction(character, this);
			return;
		}

		if (state == Chest.State.CLOSED) {
			try {
				chest.open();
			} catch (Exception e) {
				setException(e);
			} finally {
				responses.onOpenChestAction(character, this);
				alertOtherPlayers(character.getCurrentLocation(), character.getFaction().getLeader(),
						secondaryResponses -> responses.onOpenChestAction(character, this));
				return;
			}
		}

		throw new UnsupportedOperationException();
	}
}
