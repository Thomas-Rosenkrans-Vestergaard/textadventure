package textadventure.scenario;

import textadventure.actions.movement.MoveToEasternDoorAction;
import textadventure.actions.movement.MoveToNorthernDoorAction;
import textadventure.actions.movement.MoveToSouthernDoorAction;
import textadventure.actions.movement.MoveToWesternDoorAction;
import textadventure.rooms.Room;

public class EnteredRoomScenario extends AbstractScenario
{

	/**
	 * Creates a new {@link EnteredRoomScenario}.
	 *
	 * @param room The {@link Room} that was just entered.
	 */
	public EnteredRoomScenario(Room room)
	{
		super(room);

		this.addAction(new MoveToNorthernDoorAction());
		this.addAction(new MoveToSouthernDoorAction());
		this.addAction(new MoveToEasternDoorAction());
		this.addAction(new MoveToWesternDoorAction());
	}

	/**
	 * Returns the description of the {@link EnteredRoomScenario}.
	 *
	 * @return The description of the {@link EnteredRoomScenario}.
	 */
	@Override public String getDescription()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("You just entered a room.");
		builder.append(room.getDescription());
		return builder.toString();
	}
}
