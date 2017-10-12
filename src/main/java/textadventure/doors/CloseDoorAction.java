package textadventure.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;

public class CloseDoorAction implements Action
{

	/**
	 * The {@link Door} to close.
	 */
	private Door door;

	/**
	 * Creates a new {@link CloseDoorAction}.
	 *
	 * @param door The {@link Door} to close.
	 */
	public CloseDoorAction(Door door)
	{
		this.door = door;
	}

	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	@Override public String getActionName()
	{
		return "close";
	}

	/**
	 * Returns a description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getActionDescription()
	{
		return "Closes the door.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		Door.State state = door.getState();

		if (state == Door.State.CLOSED) {
			game.getUI().onDoorAlreadyClosed(game, door, player);
			return;
		}

		if (state == Door.State.OPEN) {
			door.setState(Door.State.CLOSED);
			game.getUI().onCloseDoor(game, door, player);
			return;
		}

		throw new IllegalStateException();
	}
}
