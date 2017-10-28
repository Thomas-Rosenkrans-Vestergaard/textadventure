package textadventure.items.chest;

import textadventure.GameException;

/**
 * Thrown when exception occurs related to {@link Chest}s.
 */
public class ChestException extends GameException
{

	/**
	 * The {@link Chest} that caused the {@link ChestException}.
	 */
	private Chest chest;

	/**
	 * Creates a new {@link Chest}.
	 *
	 * @param chest The {@link Chest} that caused the {@link ChestException}.
	 */
	public ChestException(Chest chest)
	{
		this.chest = chest;
	}

	/**
	 * Returns the {@link Chest} that caused the {@link ChestException}.
	 *
	 * @return The {@link Chest} that caused the {@link ChestException}.
	 */
	public Chest getChest()
	{
		return this.chest;
	}
}
