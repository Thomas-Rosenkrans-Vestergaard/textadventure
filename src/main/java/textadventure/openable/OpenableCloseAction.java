package textadventure.openable;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.openable.doors.Door;

public class OpenableCloseAction implements Action
{

	/**
	 * The {@link Door} to close.
	 */
	private Door door;

	/**
	 * Creates a new {@link OpenableCloseAction}.
	 *
	 * @param door The {@link Door} to close.
	 */
	public OpenableCloseAction(Door door)
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
		Door.State state = door.getOpenableState();

		if (state == Door.State.CLOSED) {
			game.getUserInterface().write("The door is already closed.");
			return;
		}

		if (state == Door.State.OPEN) {
			try {
				door.close();
				game.getUserInterface().write("You closed the door.");
			} catch (OpenableAlreadyClosedException e) {
				game.getUserInterface().write("The door is already closed.");
			} catch (CannotCloseException e) {
				game.getUserInterface().write("Cannot close the door.");
			}
		}
	}
}
