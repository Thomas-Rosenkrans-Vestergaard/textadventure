package textadventure.actions.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.actions.Focusable;
import textadventure.rooms.features.doors.Door;
import textadventure.ui.UI;
import textadventure.ui.UIMessage;

public class InspectDoorAction implements Action
{

	/**
	 * The {@link Door} to inspect.
	 */
	private Door door;

	/**
	 * Creates a new {@link InspectDoorAction}.
	 *
	 * @param door The {@link Door} to inspect.
	 */
	public InspectDoorAction(Door door)
	{
		this.door = door;
	}

	/**
	 * Returns the identifier of the {@link InspectDoorAction}.
	 *
	 * @return The identifier of the {@link InspectDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "inspect_door";
	}

	/**
	 * Returns the description of the {@link InspectDoorAction}.
	 *
	 * @return The description of the {@link InspectDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Inspect the door you're currently at.";
	}

	/**
	 * Performs the {@link InspectDoorAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param focus  The {@link Focusable} object.
	 * @param player The {@link Player} performing the {@link InspectDoorAction}.
	 */
	@Override public void perform(Game game, Focusable focus, Player player) throws ActionException
	{
		UI     ui      = game.getUI();
		String message = String.format("You notice that the door is %s.", door.getState().name().toLowerCase());
		ui.onMessage(message, UIMessage.INFORMATION, player);
	}
}
