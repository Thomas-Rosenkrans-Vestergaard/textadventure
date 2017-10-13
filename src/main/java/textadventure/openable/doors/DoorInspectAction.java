package textadventure.openable.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.ui.UserInterface;

public class DoorInspectAction implements Action
{

	/**
	 * The {@link Door} to inspect.
	 */
	private Door door;

	/**
	 * Creates a new {@link DoorInspectAction}.
	 *
	 * @param door The {@link Door} to inspect.
	 */
	public DoorInspectAction(Door door)
	{
		this.door = door;
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override
	public void perform(Game game, Player player) throws ActionException
	{
		UserInterface userInterface = game.getUserInterface();

		userInterface.write("You inspect the door, learning that the door is " + door.getOpenableState().name() + ".");
	}
}
