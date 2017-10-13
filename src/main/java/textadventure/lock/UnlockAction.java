package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.items.Item;
import textadventure.ui.SelectException;
import textadventure.ui.UserInterface;

public class UnlockAction implements Action
{

	/**
	 * The {@link Lock} to unlock.
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
	 * Creates a new {@link UnlockAction}.
	 *
	 * @param lock The {@link Lock} to unlock.
	 */
	public UnlockAction(Lock lock)
	{
		this.lock = lock;
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override
	public void perform(Game game, Player player) throws ActionException
	{
		this.game = game;
		this.player = player;
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
			lock.unlock(key);
			game.getUserInterface().write("You successfully unlocked the door.");
		} catch (AlreadyUnlockedException e) {
			game.getUserInterface().write("The lock is already unlocked.");
			return;
		} catch (IncorrectKeyException e) {
			game.getUserInterface().write("The key doesn't fit into the lock.");
			return;
		}
	}
}
