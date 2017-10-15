package textadventure.lock;

import textadventure.ActionException;
import textadventure.Game;
import textadventure.Player;
import textadventure.items.Item;
import textadventure.items.inventory.Backpack;
import textadventure.ui.UserInterface;

public class UnlockLockAction extends LockAction
{
	public enum Outcome
	{
		SUCCESS,
		ALREADY_UNLOCKED,
		INCORRECT_KEY,
	}

	private Key key;
	private Outcome outcome;
	private Game game;
	private String message;
	private UserInterface userInterface;
	private Backpack backpack;
	private Player player;

	public UnlockLockAction(Lock lock)
	{
		super(lock);
	}

	/**
	 * Performs the {@link LockLockAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link LockLockAction}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		UserInterface userInterface = game.getUserInterface();
		Lock.State state = lock.getState();

		if (state == Lock.State.UNLOCKED) {
			outcome = Outcome.ALREADY_UNLOCKED;
			userInterface.onLockUnlock(game, player, this);
			return;
		}

		if (state == Lock.State.LOCKED) {
			this.game = game;
			this.message = "Select the key to use to lock the lock.";
			this.userInterface = userInterface;
			this.backpack = player.getCharacter().getBackpack();
			this.player = player;
			userInterface.select(message, backpack, player, this::onResponse);
			return;
		}

		throw new UnsupportedOperationException();
	}

	private void onResponse(Item item)
	{
		if (!(item instanceof Key)) {
			userInterface.select(message, backpack, player, this::onResponse);
			return;
		}

		try {
			Key key = (Key) item;
			this.key = key;
			lock.unlock(key);
			outcome = Outcome.SUCCESS;
			userInterface.onLockUnlock(game, player, this);
		} catch (AlreadyUnlockedException e) {
			outcome = Outcome.ALREADY_UNLOCKED;
			userInterface.onLockUnlock(game, player, this);
		} catch (IncorrectKeyException e) {
			outcome = Outcome.INCORRECT_KEY;
			userInterface.onLockUnlock(game, player, this);
		}
	}

	public Key getKey()
	{
		return this.key;
	}

	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
