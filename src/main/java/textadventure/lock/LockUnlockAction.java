package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.ui.Option;
import textadventure.ui.UI;

public class LockUnlockAction implements Action
{

	/**
	 * The {@link Lock} to unlock.
	 */
	private Lock lock;

	/**
	 * Creates a new {@link LockUnlockAction}.
	 *
	 * @param lock The {@link Lock} to unlock.
	 */
	public LockUnlockAction(Lock lock)
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
		return "unlock";
	}

	/**
	 * Returns a description of the {@link LockUnlockAction}.
	 *
	 * @return The description of the {@link LockUnlockAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Unlock the lock.";
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

		if (state == Lock.State.UNLOCKED) {
			userInterface.onLockAlreadyUnlocked(game, lock, player);
			return;
		}

		if (state == Lock.State.LOCKED) {
			userInterface.select(player.getCharacter().getBackpack(), player, this::callback);
			userInterface.onLockUnlock(game, lock, player);
			return;
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Callback used by the {@link UI} to return the {@link Key} lock the {@link Lock} with.
	 *
	 * @param item The {@link Key}.
	 */
	private void callback(Option item)
	{
		System.out.println("Recieved callback: " + item.getOptionName());
	}
}
