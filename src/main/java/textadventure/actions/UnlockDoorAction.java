package textadventure.actions;

import textadventure.Game;
import textadventure.Player;
import textadventure.rooms.Room;
import textadventure.rooms.features.doors.Door;
import textadventure.scenario.Scenario;

public class UnlockDoorAction implements Action
{

	/**
	 * The {@link Door} to unlock.
	 */
	private Door door;

	/**
	 * Creates a new {@link UnlockDoorAction}.
	 *
	 * @param door The {@link Door} to unlock.
	 */
	public UnlockDoorAction(Door door)
	{
		this.door = door;
	}

	/**
	 * Returns the name of the {@link UnlockDoorAction}.
	 *
	 * @return The name of the {@link UnlockDoorAction}.
	 */
	@Override public String getIdentifier()
	{
		return "unlock_door";
	}

	/**
	 * Returns the description of the {@link UnlockDoorAction}.
	 *
	 * @return The description of the {@link UnlockDoorAction}.
	 */
	@Override public String getDescription()
	{
		return "Unlock the door in front of you. To unlock a door you must first collect a matching key.";
	}

	/**
	 * Performs the {@link UnlockDoorAction} using the provided parameters.
	 *
	 * @param game     The {@link Game} instance.
	 * @param scenario The {@link Scenario} that the {@link UnlockDoorAction} responds to.
	 * @param player   The {@link Player} performing the {@link UnlockDoorAction}.
	 */
	@Override public void perform(Game game, Scenario scenario, Player player) throws ActionException
	{
		Room currentLocation = player.getCurrentLocation();

		if (door.getSideA() != currentLocation && door.getSideB() != currentLocation) {
			throw new IllegalStateException("Attempted to unlock door outside of room.");
		}

		// TODO
	}
}
