package textadventure.lock;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.items.Item;
import textadventure.ui.SelectException;
import textadventure.ui.UI;

public class LockLockAction implements Action {

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
     * Creates a new {@link LockLockAction}.
     *
     * @param lock The {@link Lock} to lock.
     */
    public LockLockAction(Lock lock) {
        this.lock = lock;
    }

    /**
     * Returns the name of the {@link Action}.
     *
     * @return The name of the {@link Action}.
     */
    @Override
    public String getActionName() {
        return "lock";
    }

    /**
     * Returns a description of the {@link Action}.
     *
     * @return The description of the {@link Action}.
     */
    @Override
    public String getActionDescription() {
        return "Locks the lock.";
    }

    /**
     * Performs the {@link Action} using the provided parameters.
     *
     * @param game   The {@link Game} instance.
     * @param player The {@link Player} performing the {@link Action}.
     */
    @Override
    public void perform(Game game, Player player) throws ActionException {
        Lock.State state = lock.getState();
        UI userInterface = game.getUI();

        if (state == Lock.State.LOCKED) {
            userInterface.onLockAlreadyLocked(game, lock, player);
            return;
        }

        if (state == Lock.State.UNLOCKED) {
            this.game = game;
            this.player = player;
            game.getUI().select(player.getCharacter().getInventory(), player, this::callback);
            return;
        }

        throw new IllegalStateException();
    }

    /**
     * Accepts the chosen {@link Item} to use when locking the {@link Lock}.
     *
     * @param item The {@link Item} to use when locking the {@link Lock}.
     */
    private void callback(Item item) throws SelectException {
        if (!(item instanceof Key)) {
            game.getUI().write("Selected item is not a key.");
            game.getUI().select(player.getCharacter().getInventory(), player, this::callback);
            return;
        }

        try {
            Key key = (Key) item;
            lock.lock(key);
            game.getUI().write("The door is now locked.");
        } catch (LockAlreadyLockedException e) {
            game.getUI().write("The lock is already locked.");
            return;
        } catch (IncorrectKeyException e) {
            game.getUI().write("The key doesn't fit into the lock.");
            return;
        }
    }
}
