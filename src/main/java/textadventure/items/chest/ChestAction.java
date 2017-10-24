package textadventure.items.chest;

import textadventure.actions.Action;

/**
 * Base for {@link Action}s acting upon a {@link Chest}.
 */
public abstract class ChestAction implements Action
{

	/**
	 * The {@link Chest} to execute the {@link ChestAction} on.
	 */
	protected Chest chest;

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
