package textadventure.items.chest;

import textadventure.Property;
import textadventure.UnknownPropertyException;
import textadventure.items.BaseInventory;
import textadventure.lock.Lock;
import textadventure.rooms.Floor;

import java.util.HashMap;
import java.util.Map;

/**
 * An {@link textadventure.items.Inventory}.
 */
public class Chest extends BaseInventory implements Property
{

	/**
	 * The instances of {@link Property} in the {@link Property}.
	 */
	private Map<Class<? extends Property>, Property> properties = new HashMap<>();

	/**
	 * The {@link State} of the {@link Chest}.
	 */
	public enum State
	{

		/**
		 * The {@link Chest} is open. {@link Character}s can therefor perform {@link DepositItemsIntoChestAction},
		 * {@link TakeItemFromChestAction} and {@link InspectChestAction} on the {@link Chest}.
		 */
		OPEN,

		/**
		 * The {@link Chest} is closed. @link Character}s can therefor not perform {@link DepositItemsIntoChestAction},
		 * {@link TakeItemFromChestAction} and {@link InspectChestAction} on the {@link Chest}.
		 */
		CLOSED
	}

	/**
	 * The {@link State} of the {@link Chest}.
	 */
	private State state;

	/**
	 * The {@link Lock} on the {@link Chest}.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link Chest}.
	 *
	 * @param positions The number of positions available in the {@link Chest}.
	 * @param state     The {@link State} of the {@link Chest}.
	 * @param lock      The {@link Lock} on the {@link Chest}.
	 */
	public Chest(int positions, State state, Lock lock)
	{
		super(positions);
		this.state = state;
		this.lock = lock;
	}

	/**
	 * Open the {@link Chest}.
	 *
	 * @throws ChestAlreadyOpenException When the {@link Chest} is {@link Chest.State#OPEN}.
	 * @throws ChestLockedException      When the {@link Chest} is {@link Lock.State#LOCKED}.
	 */
	void open() throws ChestAlreadyOpenException, ChestLockedException
	{
		if (state == State.OPEN) {
			throw new ChestAlreadyOpenException(this);
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new ChestLockedException(this);
		}

		this.state = State.OPEN;
	}

	/**
	 * Close the {@link Chest}.
	 *
	 * @throws ChestAlreadyClosedException When the {@link Chest} is {@link Chest.State#CLOSED}.
	 * @throws ChestLockedException        When the {@link Chest} is {@link Lock.State#LOCKED}.
	 */
	void close() throws ChestAlreadyClosedException, ChestLockedException
	{
		if (state == State.CLOSED) {
			throw new ChestAlreadyClosedException(this);
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new ChestLockedException(this);
		}

		this.state = State.CLOSED;
	}

	/**
	 * Returns the {@link State} of the {@link Chest}.
	 *
	 * @return The {@link State} of the {@link Chest}.
	 */
	public State getState()
	{
		return this.state;
	}

	/**
	 * Returns the {@link Lock} on the {@link Chest}.
	 *
	 * @return The {@link Lock} on the {@link Chest}.
	 */
	public Lock getLock()
	{
		return this.lock;
	}

	/**
	 * Inserts the provided {@link Property}.
	 *
	 * @param property The {@link Property}.
	 */
	@Override public void putProperty(Property property)
	{
		properties.put(property.getClass(), property);
	}

	/**
	 * Checks if the {@link Property} has a child {@link Property} of the provided type.
	 *
	 * @param type The type of the child property to check for.
	 * @return True if the child property of the provided type exists. Returns false otherwise.
	 */
	@Override public boolean hasProperty(Class<? extends Property> type)
	{
		return properties.containsKey(type);
	}

	/**
	 * Returns the {@link Property} of the provided type.
	 *
	 * @param type The type of the {@link Property} to return.
	 */
	@Override public <T extends Property> T getProperty(Class<T> type) throws UnknownPropertyException
	{
		if (!properties.containsKey(type))
			throw new UnknownPropertyException(Floor.class, type);

		return type.cast(properties.get(type));
	}
}
