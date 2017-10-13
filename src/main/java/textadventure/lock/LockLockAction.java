package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.items.Item;
import textadventure.ui.UI;

public class LockLockAction implements Action
{

	/**
	 * The {@link Lock} to lock.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link LockLockAction}.
	 *
	 * @param lock The {@link Lock} to lock.
	 */
	public LockLockAction(Lock lock)
	{
		this.lock = lock;
	}

	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	@Override public String getActionName()
	{
		return "lock";
	}

	/**
	 * Returns a description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getActionDescription()
	{
		return "Locks the lock.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		Lock.State state         = lock.getState();
		UI         userInterface = game.getUI();

		if (state == Lock.State.LOCKED) {
			userInterface.onLockAlreadyLocked(game, lock, player);
			return;
		}

		if (state == Lock.State.UNLOCKED) {
			game.getUI().select(player.getCharacter().getInventory(), player, this::callback);
			return;
		}

		throw new IllegalStateException();
	}

	private void callback(Item item)
	{
		if (!(item instanceof Key)) {
			System.out.println("You must select a key.");
			game.getUI().select(player.getCharacter().getInventory(), player, this::callback);
		}

		System.out.println("Received " + item.getOptionName());
	}
}
