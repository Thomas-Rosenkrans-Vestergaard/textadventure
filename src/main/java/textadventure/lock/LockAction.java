package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.items.Item;
import textadventure.ui.SelectException;

public class LockAction implements Action
{

	/**
	 * The {@link Lock} to lock.
	 */
	private Lock lock;

	/**
	 * The {@link Player} performing the {@link Action}.
	 */
	private Player player;

	/**
	 * The current {@link Game} instance.
	 */
	private Game game;

	/**
	 * Creates a new {@link LockAction}.
	 *
	 * @param lock The {@link Lock} to lock.
	 */
	LockAction(Lock lock)
	{
		this.lock = lock;
	}

	/**
	 * Instructs the {@link Player} to chose the {@link Key} to use to lock the {@link Lock}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		this.player = player;
		this.game = game;
		game.getUserInterface().select(player.getCharacter().getInventory(), player, this::callback);
	}

	/**
	 * Accepts the chosen {@link Item} to use when locking the {@link Lock}.
	 *
	 * @param item The {@link Item} to use when locking the {@link Lock}.
	 */
	private void callback(Item item) throws SelectException
	{
		if (!(item instanceof Key)) {
			game.getUserInterface().write("Selected item is not a key.");
			game.getUserInterface().select(player.getCharacter().getInventory(), player, this::callback);
			return;
		}

		try {
			Key key = (Key) item;
			lock.lock(key);
			game.getUserInterface().write("You successfully locked the lock using the key.");
		} catch (AlreadyLockedException e) {
			game.getUserInterface().write("You cannot lock the lock twice.");
		} catch (IncorrectKeyException e) {
			game.getUserInterface().write("You attempt to turn the key, but nothing happens. You clearly have the incorrect key.");
		}
	}
}
