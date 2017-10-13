package textadventure.openable.doors;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.openable.Openable;
import textadventure.rooms.Room;

/**
 * {@link Action} that lets the {@link Player} move through an {@link Door}. The
 * {@link Door} must have {@link Door.State}
 * OPEN before {@link DoorEnterAction} can be performed.
 */
public class DoorEnterAction implements Action
{

    /**
     * The {@link Door} to move through.
     */
    private Door door;

    /**
     * Creates a new {@link DoorEnterAction}.
     *
     * @param door The {@link Door} to move through.
     */
    public DoorEnterAction(Door door)
    {
        this.door = door;
    }

    /**
     * Returns the name of the {@link Action}.
     *
     * @return The name of the {@link Action}.
     */
    @Override
    public String getActionName()
    {
        return "enter";
    }

    /**
     * Returns a description of the {@link Action}.
     *
     * @return The description of the {@link Action}.
     */
    @Override
    public String getActionDescription()
    {
        return "Move through the door to the next room.";
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
        Room currentRoom = player.getCharacter().getCurrentLocation();
        Room targetRoom = door.getInverseRoom(currentRoom);

        if (targetRoom == null) {
            throw new IllegalStateException();
        }

        if (door.getOpenableState() == Openable.State.CLOSED) {
            System.out.println("Cannot move though closed door.");
            return;
        }

        if (door.getOpenableState() == Openable.State.OPEN) {
            player.getCharacter().setCurrentLocation(targetRoom);
            System.out.println(player.getCharacter().getName() + " moved through the door.");
            System.out.println("You are now in " + player.getCharacter().getCurrentLocation().getName() + "\n" +
                    player.getCharacter().getCurrentLocation().getDescription());
            return;
        }

        throw new IllegalStateException();
    }
}
