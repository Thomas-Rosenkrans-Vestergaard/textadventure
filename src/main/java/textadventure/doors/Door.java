package textadventure.doors;

import textadventure.characters.Character;
import textadventure.lock.Lock;
import textadventure.rooms.Room;

/**
 * The {@link Door} represents a portal from one {@link Room} to another.
 */
public interface Door {

    /**
     * The {@link State} of the {@link Door}.
     */
    enum State {

        /**
         * The {@link Door} is open. {@link Character}s can therefor move through the {@link Door}.
         */
        OPEN,

        /**
         * The {@link Door} is open. {@link Character}s can therefor not move through the {@link Door}.
         */
        CLOSED,
    }

    /**
     * Opens the {@link Door}.
     *
     * @throws DoorAlreadyOpenException When the {@link Door} is already {@link State#OPEN}.
     * @throws DoorLockedException      When the {@link Door} is {@link Lock.State#LOCKED}.
     */
    void open() throws DoorAlreadyOpenException, DoorLockedException;

    /**
     * Closes the {@link Door}.
     *
     * @throws DoorAlreadyClosedException When the {@link Door} is already {@link State#CLOSED}.
     * @throws DoorLockedException        When the {@link Door} is {@link Lock.State#LOCKED}.
     */
    void close() throws DoorAlreadyClosedException, DoorLockedException;

    /**
     * Returns the {@link State} of the {@link Door}.
     *
     * @return The {@link State} of the {@link Door}.
     */
    State getState();

    /**
     * Returns the {@link Lock} on the {@link Door}.
     *
     * @return The {@link Lock} on the {@link Door}.
     */
    Lock getLock();

    /**
     * Returns one of the {@link Room}s the {@link Door} connects.
     *
     * @return One of the {@link Room}s the {@link Door} connects.
     */
    Room getRoomA();

    /**
     * Returns the other {@link Room}s the {@link Door} connects.
     *
     * @return The other {@link Room}s the {@link Door} connects.
     */
    Room getRoomB();

    /**
     * Sets one of the {@link Room}s the {@link Door} connects.
     *
     * @param a One of the {@link Room}s the {@link Door} connects.
     */
    void setRoomA(Room a);

    /**
     * Sets the other {@link Room}s the {@link Door} connects.
     *
     * @param b The other {@link Room}s the {@link Door} connects.
     */
    void setRoomB(Room b);

    /**
     * Returns the {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}.
     *
     * @param room The opposite {@link Room}.
     * @return The {@link Room} on the other side of {@link Door} in relation to the provided {@link Room}. Returns
     * <code>null</code> if the provided room is unknown.
     */
    Room getInverseRoom(Room room);
}
