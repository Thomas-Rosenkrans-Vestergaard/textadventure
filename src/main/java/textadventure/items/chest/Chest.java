package textadventure.items.chest;

import com.google.common.collect.ImmutableMap;
import textadventure.Property;
import textadventure.PropertyContainer;
import textadventure.actions.Action;
import textadventure.items.BaseInventory;
import textadventure.lock.Lock;

import java.util.HashMap;
import java.util.stream.Stream;

/**
 * An {@link textadventure.items.Inventory}.
 */
public class Chest extends BaseInventory implements PropertyContainer
{

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
	 * The {@link Property}s registered on the {@link Property}.
	 */
	private HashMap<String, Property> properties = new HashMap<>();

	/**
	 * The {@link Action}s registered on the {@link Property}.
	 */
	private HashMap<String, Action> actions = new HashMap<>();

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
	 * @param slots The number of slots available in the {@link Chest}.
	 * @param state The {@link State} of the {@link Chest}.
	 * @param lock  The {@link Lock} on the {@link Chest}.
	 */
	public Chest(int slots, State state, Lock lock)
	{
		super(slots);
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
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param name   The name of the {@link Action}.
	 * @param action The {@link Action} to add to the {@link Property}.
	 */
	@Override public void addAction(String name, Action action)
	{
		actions.put(name, action);
	}

	/**
	 * Returns the {@link Action} with the provided name.
	 *
	 * @param name The <code>name</code> of the {@link Action} to return.
	 * @return The {@link Action} with the provided <code>name</code>. Returns <code>null</code>
	 */
	@Override public Action getAction(String name)
	{
		return actions.get(name);
	}

	/**
	 * Returns a stream of the {@link Action}s available on the {@link Property}.
	 *
	 * @return Returns the stream of the {@link Action}s available on the {@link Property}.
	 */
	@Override public Stream<Action> getActions()
	{
		return actions.values().stream();
	}

	/**
	 * Adds the {@link Property} to the {@link PropertyContainer}.
	 *
	 * @param propertyName The name of the {@link Property}.
	 * @param property     The {@link Property} to add to the {@link PropertyContainer}.
	 */
	@Override public void addProperty(String propertyName, Property property)
	{
		properties.put(propertyName, property);
	}

	/**
	 * Returns the {@link Property} with the provided <code>name</code>.
	 *
	 * @param name The <code>name</code> of the {@link Property} to get.
	 */
	@Override public Property getProperty(String name)
	{
		return properties.get(name);
	}

	/**
	 * Returns an {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 *
	 * @return The {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 */
	@Override public ImmutableMap<String, Property> getProperties()
	{
		return ImmutableMap.copyOf(properties);
	}
}
