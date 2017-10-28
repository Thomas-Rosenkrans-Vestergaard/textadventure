package textadventure.items.chest;

/**
 * Thrown when {@link Chest#close()} is called on a {@link Chest.State#CLOSED} {@link Chest}.
 */
public class ChestAlreadyClosedException extends ChestException
{

	/**
	 * Creates a new {@link ChestAlreadyClosedException}.
	 *
	 * @param chest The {@link Chest} that was {@link Chest.State#CLOSED}.
	 */
	public ChestAlreadyClosedException(Chest chest)
	{
		super(chest);
	}
}
