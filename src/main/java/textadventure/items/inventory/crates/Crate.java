package textadventure.items.inventory.crates;

import textadventure.AbstractPropertyContainer;
import textadventure.items.inventory.Inventory;
import textadventure.lock.Lock;

public class Crate extends AbstractPropertyContainer
{

	/**
	 * The {@link Inventory} of the {@link Crate}.
	 */
	private Inventory inventory;

	/**
	 * The {@link Lock} on the {@link Crate}.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link Crate} with the provided {@link Inventory}.
	 *
	 * @param inventory The {@link Inventory} of the {@link Crate}.
	 * @param lock      The {@link Lock} on the {@link Crate}.
	 */
	public Crate(Inventory inventory, Lock lock)
	{
		this.inventory = inventory;
		this.lock = lock;
	}
}
