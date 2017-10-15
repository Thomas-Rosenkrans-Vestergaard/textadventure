package textadventure.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.NamedAction;
import textadventure.rooms.Room;

public class UseDoorAction extends DoorAction implements NamedAction
{

	/**
	 * The possible {@link Outcome}s of the {@link UseDoorAction}.
	 */
	public enum Outcome
	{

		/**
		 * The player successfully moved through {@link Door}
		 */
		SUCCESS,

		/**
		 * The player couldn't move through the {@link Door}, since the {@link Door} was closed.
		 */
		CLOSED
	}

	/**
	 * The {@link Outcome} of the {@link UseDoorAction}.
	 */
	private Outcome outcome;

	/**
	 * Creates a new {@link UseDoorAction}.
	 *
	 * @param door The {@link Door} to use.
	 */
	public UseDoorAction(Door door)
	{
		super(door);
	}

	/**
	 * Performs the {@link UseDoorAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} executing the {@link  UseDoorAction}.
	 */
	@Override public void perform(Game game, Player player)
	{
		Room currentRoom = player.getCharacter().getCurrentLocation();
		Room targetRoom = getDoor().getInverseRoom(currentRoom);

		if (targetRoom == null) {
			throw new IllegalStateException();
		}

		Door.State state = getDoor().getState();

		if (state == Door.State.CLOSED) {
			outcome = Outcome.CLOSED;
			game.getUserInterface().onDoorUse(game, player, this);
			return;
		}

		if (state == Door.State.OPEN) {
			outcome = Outcome.SUCCESS;
			player.getCharacter().setCurrentLocation(targetRoom);
			game.getUserInterface().onDoorUse(game, player, this);
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the {@link Outcome} of the {@link UseDoorAction}.
	 *
	 * @return The {@link Outcome} of the {@link UseDoorAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}

	/**
	 * Returns the name of the {@link UseDoorAction}.
	 *
	 * @return The name of the {@link UseDoorAction}.
	 */
	@Override public String getActionName()
	{
		return "use";
	}

	/**
	 * Returns the description of the {@link UseDoorAction}.
	 *
	 * @return The description of the {@link UseDoorAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Use the door to move to the next room.";
	}
}