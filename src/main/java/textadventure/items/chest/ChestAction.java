package textadventure.items.chest;

import textadventure.actions.AbstractAction;
import textadventure.actions.Action;

/**
 * Abstract {@link Action} for acting upon {@link Chest}s.
 */
public abstract class ChestAction extends AbstractAction
{

	/**
	 * The {@link Chest} to execute the {@link ChestAction} on.
	 */
	protected Chest chest;

	/**
	 * Resets the {@link Action} to its default state.
	 */
	@Override public void reset()
	{
		this.exception = null;
		this.chest = null;
	}

	/**
	 * Creates a new {@link ChestAction}.
	 *
	 * @param chest The {@link Chest} to execute the {@link ChestAction} on.
	 */
	ChestAction(Chest chest)
	{
		this.chest = chest;
	}

	/**
	 * Returns the {@link Chest} to execute the {@link ChestAction} on.
	 *
	 * @return The {@link Chest} to execute the {@link ChestAction} on.
	 */
	public Chest getChest()
	{
		return this.chest;
	}
}
