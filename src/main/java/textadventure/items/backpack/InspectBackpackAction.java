package textadventure.items.backpack;

import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to inspect the
 * {@link textadventure.items.Item}s in their {@link Backpack}.
 */
public class InspectBackpackAction extends BackpackAction
{

	/**
	 * Creates a new {@link InspectBackpackAction}.
	 *
	 * @param backpack The {@link Backpack} to be inspected.
	 */
	public InspectBackpackAction(Backpack backpack)
	{
		super(backpack);
	}

	/**
	 * Performs the {@link InspectBackpackAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link InspectBackpackAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link InspectBackpackAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		responses.onInspectBackpackAction(character, this);
	}
}
