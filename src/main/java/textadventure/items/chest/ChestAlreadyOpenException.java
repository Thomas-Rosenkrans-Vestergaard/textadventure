package textadventure.items.chest;

/**
 * Thrown when {@link Chest#open()} is called on a {@link Chest.State#OPEN} {@link Chest}.
 */
public class ChestAlreadyOpenException extends ChestException
{

	/**
	 * Creates a new {@link ChestAlreadyOpenException}.
	 *
	 * @param chest The {@link Chest} that was {@link Chest.State#OPEN}.
	 */
	public ChestAlreadyOpenException(Chest chest)
	{
		super(chest);
	}
}
