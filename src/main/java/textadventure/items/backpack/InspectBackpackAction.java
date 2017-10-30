package textadventure.items.backpack;

import textadventure.characters.Character;
import textadventure.actions.ActionPerformCallback;
import textadventure.ui.GameInterface;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to inspect the
 * {@link textadventure.items.Item}s in their {@link Backpack}.
 */
public class InspectBackpackAction extends BackpackAction
{

	/**
	 * The {@link ActionPerformCallback} to invoke after performing the {@link InspectBackpackAction}.
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
	 * Performs the {@link InspectBackpackAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link InspectBackpackAction}.
	 * @param arguments     The arguments provided to the {@link InspectBackpackAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		callback.send(character, this);
	}
}
