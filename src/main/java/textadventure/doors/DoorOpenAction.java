package textadventure.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.lock.Lock;

/**
 * {@link Action} that opens a closed {@link Door}.
 */
public class DoorOpenAction implements Action
{

	/**
	 * The {@link Door} to open.
	 */
	private Door door;

	/**
	 * Creates a new {@link DoorOpenAction}.
	 *
	 * @param door The {@link Door} to open.
	 */
	public DoorOpenAction(Door door)
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
		return "open";
	}

	/**
	 * Returns a description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getActionDescription()
	{
		return "Opens the door.";
	}

	/**
	 * Performs the {@link DoorOpenAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link DoorOpenAction}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		Lock.State state = door.getLock().getState();

		if (door.getState() == Door.State.OPEN) {
			game.getUI().onDoorAlreadyOpen(game, door, player);
			return;
		}

		if (state == Lock.State.LOCKED) {
			throw new UnsupportedOperationException();
		}

		if (state == Lock.State.UNLOCKED) {
			door.setState(Door.State.OPEN);
			game.getUI().onOpenDoor(game, door, player);
			return;
		}

		throw new IllegalStateException();
	}
}
