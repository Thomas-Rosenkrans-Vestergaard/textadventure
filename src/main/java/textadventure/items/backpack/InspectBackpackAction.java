package textadventure.items.backpack;

import textadventure.Character;
import textadventure.Game;
import textadventure.actions.ActionPerformCallback;

/**
 * {@link textadventure.actions.Action} that allows a {@link textadventure.Character} to inspect the
 * {@link textadventure.items.Item}s in their {@link Backpack}.
 */
public class InspectBackpackAction extends BackpackAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link InspectBackpackAction}.
	 */
	private ActionPerformCallback<InspectBackpackAction> callback;

	/**
	 * Creates a new {@link InspectBackpackAction}.
	 *
	 * @param door     The {@link Backpack} to be inspected.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link InspectBackpackAction}.
	 */
	InspectBackpackAction(Backpack door, ActionPerformCallback<InspectBackpackAction> callback)
	{
		super(door);

		this.callback = callback;
	}

	/**
	 * Performs the {@link PickupItemAction} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link PickupItemAction}.
	 * @param arguments The arguments provided to the {@link PickupItemAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		callback.send(game, character, this);
	}
}
