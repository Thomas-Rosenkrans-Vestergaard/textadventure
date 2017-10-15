package textadventure.lock;

import textadventure.ActionException;
import textadventure.Game;
import textadventure.Player;
import textadventure.items.Item;
import textadventure.items.inventory.Backpack;
import textadventure.ui.UserInterface;

public class LockLockAction extends LockAction
{
	public enum Outcome
	{
		SUCCESS,
		ALREADY_LOCKED,
		INCORRECT_KEY,
	}

	private Key key;
	private Outcome outcome;
	private Game game;
	private String message;
	private UserInterface userInterface;
	private Backpack backpack;
	private Player player;

	public LockLockAction(Lock lock)
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

		if (state == Lock.State.LOCKED) {
			outcome = Outcome.ALREADY_LOCKED;
			userInterface.onLockLock(game, player, this);
			return;
		}

		if (state == Lock.State.UNLOCKED) {
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
			lock.lock(key);
			outcome = Outcome.SUCCESS;
			userInterface.onLockLock(game, player, this);
		} catch (AlreadyLockedException e) {
			outcome = Outcome.ALREADY_LOCKED;
			userInterface.onLockLock(game, player, this);
		} catch (IncorrectKeyException e) {
			outcome = Outcome.INCORRECT_KEY;
			userInterface.onLockLock(game, player, this);
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
