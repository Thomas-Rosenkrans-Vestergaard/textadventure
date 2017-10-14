package textadventure.doors;

import textadventure.*;
import textadventure.lock.*;
import textadventure.rooms.Room;

/**
 * The default implementation of the {@link Door} interface.
 */
public class DefaultDoor extends AbstractPropertyContainer implements Door
{

	/**
	 * The {@link Door.State} of the {@link Door}.
	 */
	private State state;

	/**
	 * The {@link Lock} on the {@link Door}.
	 */
	private Lock lock;

	/**
	 * The {@link Room} on one side of the {@link Door}.
	 */
	private Room roomA;

	/**
	 * The {@link Room} on the other side of the {@link Door}.
	 */
	private Room roomB;

	/**
	 * Creates a new {@link DefaultDoor}.
	 *
	 * @param state The {@link textadventure.doors.Door.State} that the {@link Door} is in.
	 * @param lock  The {@link Lock} placed on the {@link Door}.
	 * @param roomA The first room (<code>roomA</code>).
	 * @param roomB The second room (<code>roomB</code>).
	 */
	public DefaultDoor(State state, Lock lock, Room roomA, Room roomB)
	{
		this.lock = lock;
		this.state = state;
		this.roomA = roomA;
		this.roomB = roomB;

		addAction("open", "Open the door.", this::open);
		addAction("close", "Close the door.", this::close);
		addAction("use", "Move through the door to the other side.", this::use);
		addAction("inspect", "Inspect the door to learn new information.", this::inspect);
		addAction("lock", "Lock the door.", lock::lock);
		addAction("unlock", "Unlock the door.", lock::unlock);

		addProperty("lock", "The lock on the door.", lock);
	}

	/**
	 * Open the {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 * @throws DoorAlreadyOpenException When the {@link Door} is {@link Door.State#OPEN}.
	 * @throws DoorLockedException      When the {@link Door} is {@link Lock.State#LOCKED}.
	 */
	@Override public void open(Game game, Player player) throws DoorAlreadyOpenException, DoorLockedException
	{
		if (state == State.OPEN) {
			throw new DoorAlreadyOpenException(this, this::open, player);
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new DoorLockedException(this, this::open, player);
		}

		this.state = State.OPEN;
		game.getUserInterface().onDoorOpen(game, player, this);
	}

	/**
	 * Closes the {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 * @throws DoorAlreadyClosedException When the {@link Door} is {@link Door.State#CLOSED}.
	 * @throws DoorLockedException        When the {@link Door} is {@link Lock.State#LOCKED}.
	 */
	@Override public void close(Game game, Player player) throws DoorAlreadyClosedException, DoorLockedException
	{
		if (state == State.CLOSED) {
			throw new DoorAlreadyClosedException(this, this::close, player);
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new DoorLockedException(this, this::close, player);
		}

		this.state = State.CLOSED;
		game.getUserInterface().onDoorClose(game, player, this);
	}

	/**
	 * Use the {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 * @throws DoorClosedException When the {@link Door} is {@link Door.State#CLOSED}. The {@link Door} must first be
	 *                             {@link Door#open(Game, Player)}ed.
	 */
	@Override public void use(Game game, Player player) throws DoorClosedException
	{
		Room currentRoom = player.getCharacter().getCurrentLocation();
		Room targetRoom = this.getInverseRoom(currentRoom);

		if (targetRoom == null) {
			throw new IllegalStateException();
		}

		if (state == State.CLOSED) {
			throw new DoorClosedException(this, this::use, player);
		}

		if (state == State.OPEN) {
			player.getCharacter().setCurrentLocation(targetRoom);
			game.getUserInterface().onDoorEnter(game, player, this, targetRoom);
			return;
		}

		throw new IllegalStateException();
	}

	/**
	 * Inspect the {@link Door}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void inspect(Game game, Player player)
	{
		game.getUserInterface().onDoorInspect(game, player, this);
	}

	/**
	 * Returns the {@link State} of the {@link Door}.
	 *
	 * @return The {@link State} of the {@link Door}.
	 */
	@Override public State getState()
	{
		return state;
	}

	/**
	 * Returns the {@link Lock} on the {@link Door}.
	 *
	 * @return The {@link Lock} on the {@link Door}.
	 */
	@Override public Lock getLock()
	{
		return lock;
	}

	/**
	 * Returns the {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}.
	 *
	 * @param room The opposite {@link Room}.
	 * @return The {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}. Returns
	 * <code>null</code> if the provided room is unknown.
	 */
	@Override public Room getInverseRoom(Room room)
	{
		if (room == roomA) return roomB;
		if (room == roomB) return roomA;

		return null;
	}
}
