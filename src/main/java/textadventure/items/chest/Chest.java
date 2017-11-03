package textadventure.items.chest;

import com.google.common.collect.ImmutableMap;
import textadventure.Property;
import textadventure.PropertyContainer;
import textadventure.UnknownActionException;
import textadventure.UnknownPropertyException;
import textadventure.actions.Action;
import textadventure.items.BaseInventory;
import textadventure.lock.Lock;

import java.util.HashMap;
import java.util.Map;

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
	 * The {@link Action}s available on the {@link Property}.
	 */
	private Map<Class<? extends Action>, Action> actions = new HashMap<>();

	/**
	 * Adds a new {@link Action} to the {@link Property}.
	 *
	 * @param action The {@link Action} to add to the {@link Property}.
	 */
	@Override public void putAction(Action action)
	{
		actions.put(action.getClass(), action);
	}

	/**
	 * Returns the {@link Action} with the provided name.
	 *
	 * @param type
	 * @return The {@link Action} with the provided name.
	 */
	@Override public <T extends Action> T getAction(Class<T> type) throws UnknownActionException
	{
		if (!actions.containsKey(type))
			throw new UnknownActionException(this, type);

		return type.cast(actions.get(type));
	}

	@Override public <T extends Action> boolean hasAction(Class<T> type)
	{
		return actions.containsKey(type);
	}

	/**
	 * Returns an {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 *
	 * @return The {@link ImmutableMap} of the {@link Action}s available on the {@link Property} mapped to their name.
	 */
	@Override public ImmutableMap<Class<? extends Action>, Action> getActions()
	{
		return ImmutableMap.copyOf(actions);
	}

	/**
	 * The instances of {@link Property} in the {@link PropertyContainer}.
	 */
	private Map<Class<? extends Property>, Property> properties = new HashMap<>();

	/**
	 * Adds the {@link Property} to the {@link PropertyContainer}.
	 *
	 * @param property The {@link Property} to add to the {@link PropertyContainer}.
	 */
	@Override public void putProperty(Property property)
	{
		properties.put(property.getClass(), property);
	}

	/**
	 * Returns the {@link Property} of the provided type.
	 *
	 * @param type The type of the {@link Property} to return.
	 */
	@Override public <T extends Property> T getProperty(Class<T> type) throws UnknownPropertyException
	{
		if (!properties.containsKey(type))
			throw new UnknownPropertyException(this, type);

		return type.cast(properties.get(type));
	}

	@Override public <T extends Property> boolean hasProperty(Class<T> type)
	{
		return properties.containsKey(type);
	}

	/**
	 * Returns an {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 *
	 * @return The {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 */
	@Override public ImmutableMap<Class<? extends Property>, Property> getProperties()
	{
		return ImmutableMap.copyOf(properties);
	}
}
