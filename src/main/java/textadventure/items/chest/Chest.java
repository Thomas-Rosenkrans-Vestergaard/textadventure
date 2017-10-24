package textadventure.items.chest;

import com.google.common.collect.ImmutableMap;
import textadventure.PropertyContainer;
import textadventure.actions.Action;
import textadventure.Property;
import textadventure.items.BaseInventory;
import textadventure.lock.Lock;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;

import java.util.HashMap;
import java.util.stream.Stream;

public class Chest extends BaseInventory implements PropertyContainer
{

	/**
	 * The {@link State} of the {@link Chest}.
	 */
	public enum State
	{

		/**
		 * The {@link Chest} is open.
		 */
		OPEN,

		/**
		 * The {@link Chest} is closed.
		 */
		CLOSED
	}

	/**
	 * The instances of {@link Property} in the {@link Chest}.
	 */
	private HashMap<String, Property> properties = new HashMap<>();

	/**
	 * The {@link Action}s available on the {@link Property}.
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
	 * @param countSlots The number of slots available in the {@link Chest}.
	 * @param state      The {@link State} of the {@link Chest}.
	 * @param lock       The {@link Lock} on the {@link Chest}.
	 */
	public Chest(int countSlots, State state, Lock lock)
	{
		super(countSlots);
		this.state = state;
		this.lock = lock;

		addAction("open", new OpenChestAction(this));
		addAction("close", new CloseChestAction(this));
		addAction("inspect", new InspectChestAction(this));
		addAction("take", new TakeItemFromChestAction(this));
		addAction("lock", new LockLockAction(lock));
		addAction("unlock", new UnlockLockAction(lock));

		addProperty("lock", lock);
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
			throw new ChestAlreadyOpenException();
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new ChestLockedException();
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
			throw new ChestAlreadyClosedException();
		}

		if (lock.getState() == Lock.State.LOCKED) {
			throw new ChestLockedException();
		}

		this.state = State.CLOSED;
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
		return new ImmutableMap.Builder<String, Property>().putAll(properties).build();
	}
}
