package textadventure.items.chest;

/**
 * Thrown when a {@link Character} attempts to use a {@link Chest.State#CLOSED} {@link Chest}.
 */
public class ChestClosedException extends ChestException
{

	/**
	 * Creates a new {@link ChestClosedException}.
	 *
	 * @param chest The {@link Chest} that is {@link Chest.State#CLOSED}.
	 */
	public ChestClosedException(Chest chest)
	{
		super(chest);
	}
}
