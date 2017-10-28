package textadventure.items.chest;

/**
 * Thrown when {@link Chest#open()} or {@link Chest#close()} is called on a
 * {@link textadventure.lock.Lock.State#LOCKED} {@link Chest}.
 */
public class ChestLockedException extends ChestException
{

	/**
	 * Creates a new {@link ChestLockedException}.
	 *
	 * @param chest The {@link Chest} that was {@link textadventure.lock.Lock.State#LOCKED}.
	 */
	public ChestLockedException(Chest chest)
	{
		super(chest);
	}
}
