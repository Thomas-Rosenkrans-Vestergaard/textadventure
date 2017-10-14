package textadventure.lock;

import textadventure.AbstractProperty;
import textadventure.Game;
import textadventure.Player;
import textadventure.items.inventory.Backpack;
import textadventure.ui.SelectException;
import textadventure.ui.UserInterface;

import static textadventure.lock.Lock.State.LOCKED;
import static textadventure.lock.Lock.State.UNLOCKED;

public class Lock extends AbstractProperty
{

	/**
	 * Represents the {@link State} of a {@link Lock}.
	 */
	public enum State
	{
		LOCKED,
		UNLOCKED,
	}

	/**
	 * The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with matching
	 * codes.
	 */
	private final String code;

	/**
	 * The {@link State} of the {@link Lock}.
	 */
	private State state;

	/**
	 * Creates a new {@link Lock}.
	 *
	 * @param code  The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 *              matching codes.
	 * @param state The state of the {@link Lock}.
	 */
	public Lock(String code, State state)
	{
		this.code = code;
		this.state = state;

		addAction("lock", "Attempt to lock the lock, provided you have a matching key.", this::lock);
		addAction("unlock", "Attempt to unlock the lock, provided you have a matching key.", this::unlock);
		addAction("inspect", "Inspect the lock to gather new information.", this::inspect);
	}

	/**
	 * Returns The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 * matching codes.
	 *
	 * @return The code representing the {@link Lock}. The {@link Lock} can only be opened by {@link Lock}s with
	 * matching codes.
	 */
	public String getCode()
	{
		return this.code;
	}

	/**
	 * Returns the {@link State} of the {@link Lock}.
	 *
	 * @return The {@link State} of the {@link Lock}.
	 */
	public State getState()
	{
		return this.state;
	}

	/**
	 * Locks the {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who performed the {@link textadventure.Action}.
	 * @throws IncorrectKeyException  When the {@link Key} doesn't fit the lock.
	 * @throws AlreadyLockedException When the {@link Lock} is {@link Lock.State#LOCKED}.
	 */
	public void lock(Game game, Player player) throws IncorrectKeyException, AlreadyLockedException
	{
		if (state == LOCKED) {
			throw new AlreadyLockedException(this, this::lock, player);
		}

		UserInterface userInterface = game.getUserInterface();
		String message = "Select the key to use to lock the lock.";
		Backpack backpack = player.getCharacter().getBackpack();

		userInterface.select(message, backpack, player, selection -> {
			if (!(selection instanceof Key))
				throw new SelectException(this, this::lock, player);

			Key key = (Key) selection;

			if (!code.equals(key.getCode())) {
				throw new IncorrectKeyException(this, this::lock, player, key);
			}

			state = LOCKED;
			userInterface.onLockLock(game, player, this);
		});
	}

	/**
	 * Unlocks the {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who performed the {@link textadventure.Action}.
	 * @throws IncorrectKeyException    When the {@link Key} doesn't fit the lock.
	 * @throws AlreadyUnlockedException When the {@link Lock} is {@link Lock.State#UNLOCKED}.
	 */
	public void unlock(Game game, Player player) throws AlreadyUnlockedException, IncorrectKeyException
	{
		if (state == UNLOCKED) {
			throw new AlreadyUnlockedException(this, this::lock, player);
		}

		UserInterface userInterface = game.getUserInterface();
		String message = "Select the key to use to unlock the door.";
		Backpack backpack = player.getCharacter().getBackpack();

		userInterface.select(message, backpack, player, selection -> {

			if (!(selection instanceof Key)) {
				throw new SelectException(this, this::lock, player);
			}

			Key key = (Key) selection;

			if (!code.equals(key.getCode())) {
				throw new IncorrectKeyException(this, this::lock, player, key);
			}

			state = UNLOCKED;
			userInterface.onLockUnlock(game, player, this);
		});
	}

	/**
	 * Inspects the {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who performed the {@link textadventure.Action}.
	 */
	public void inspect(Game game, Player player)
	{
		UserInterface userInterface = game.getUserInterface();

		userInterface.onLockInspect(game, player, this);
	}
}
