package textadventure.items.chest;

import com.google.common.collect.ImmutableMap;
import textadventure.BaseProperty;
import textadventure.Property;
import textadventure.UnknownActionException;
import textadventure.UnknownPropertyException;
import textadventure.actions.Action;
import textadventure.actions.ActionFactory;
import textadventure.items.BaseInventory;
import textadventure.lock.Lock;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;

/**
 * An {@link textadventure.items.Inventory}.
 */
public class Chest extends BaseInventory implements Property
{

	/**
	 * The {@link State} of the {@link Chest}.
	 */
	private State state;
	/**
	 * The {@link Lock} on the {@link Chest}.
	 */
	private Lock lock;
	/**
	 * The {@link Property} the {@link Chest} delegates to.
	 */
	private BaseProperty property = new BaseProperty();

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

		putProperty(lock);

		putActionFactory(OpenChestAction.class, () -> new OpenChestAction(this));
		putActionFactory(CloseChestAction.class, () -> new CloseChestAction(this));
		putActionFactory(DepositItemsIntoChestAction.class, () -> new DepositItemsIntoChestAction(this));
		putActionFactory(TakeItemFromChestAction.class, () -> new TakeItemFromChestAction(this));
		putActionFactory(InspectChestAction.class, () -> new InspectChestAction(this));

		putActionFactory(LockLockAction.class, () -> new LockLockAction(lock));
		putActionFactory(UnlockLockAction.class, () -> new UnlockLockAction(lock));
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
	 * Inserts a new {@link ActionFactory} for a provided {@link Action} type.
	 *
	 * @param action  The type of {@link Action} to insert the {@link ActionFactory} for.
	 * @param factory The {@link ActionFactory}.
	 */
	@Override public void putActionFactory(Class<? extends Action> action, ActionFactory factory)
	{
		property.putActionFactory(action, factory);
	}

	/**
	 * Checks whether or not the {@link Property} contains an {@link ActionFactory} that can produce instances of the
	 * provided type of {@link Action}.
	 *
	 * @param action The type of {@link Action} to check for.
	 * @return True if the {@link Property} has an {@link ActionFactory} that can produce instance of the provided
	 * type of {@link Action}. Returns false if no such {@link ActionFactory} exists.
	 */
	@Override public boolean hasActionFactory(Class<? extends Action> action)
	{
		return property.hasActionFactory(action);
	}

	/**
	 * Creates a new {@link Action} from the provided type.
	 *
	 * @param action The type of {@link Action} to create.
	 * @return The newly created {@link Action}.
	 * @throws UnknownActionException When no {@link ActionFactory} exists that can create the {@link Action} of the provided type.
	 */
	@Override public Action getAction(Class<? extends Action> action) throws UnknownActionException
	{
		return property.getAction(action);
	}

	/**
	 * Returns the {@link ActionFactory} instances in the {@link Property} mapped to the type of {@link Action} the
	 * {@link ActionFactory} produces.
	 *
	 * @return The {@link ActionFactory} instances in the {@link Property} mapped to the type of {@link Action} the
	 * {@link ActionFactory} produces.
	 */
	@Override public ImmutableMap<Class<? extends Action>, ActionFactory> getActions()
	{
		return property.getActions();
	}

	/**
	 * Inserts the provided {@link Property}.
	 *
	 * @param property The {@link Property}.
	 */
	@Override public void putProperty(Property property)
	{
		this.property.putProperty(property);
	}

	/**
	 * Returns the {@link Property} of the provided type.
	 *
	 * @param type The type of the {@link Property} to return.
	 */
	@Override public <T extends Property> T getProperty(Class<T> type) throws UnknownPropertyException
	{
		return property.getProperty(type);
	}

	/**
	 * Returns an {@link ImmutableMap} of {@link Property} instances inside the {@link Property}.
	 *
	 * @return The {@link ImmutableMap} of {@link Property} instances inside the {@link Property}.
	 */
	@Override public ImmutableMap<Class<? extends Property>, Property> getProperties()
	{
		return property.getProperties();
	}

	/**
	 * Checks if the {@link Property} has a child {@link Property} of the provided type.
	 *
	 * @param type The type of the child property to check for.
	 * @return True if the child property of the provided type exists. Returns false otherwise.
	 */
	@Override public boolean hasProperty(Class<? extends Property> type)
	{
		return property.hasProperty(type);
	}

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
}
