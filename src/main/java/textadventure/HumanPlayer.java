package textadventure;

import textadventure.ui.UI;

import java.util.function.Consumer;

public class HumanPlayer extends AbstractPlayer {

    /**
     * The callback to use when reporting back to the {@link Game} instance.
     */
    private Consumer<Action> callback;

    /**
     * The {@link Game} instance.
     */
    private Game game;

    /**
     * Creates a new human controlled player.
     *
     * @param character The {@link Character} that the {@link HumanPlayer} controls.
     */
    public HumanPlayer(Character character) {
        super(character);
    }

    /**
     * Delegates a turn to the {@link Player}.
     *
     * @param game     The {@link Game} instance.
     * @param callback The callback to use for returning an appropriate {@link Action}.
     */
    @Override
    public void takeTurn(Game game, Consumer<Action> callback) {
        this.callback = callback;
        this.game = game;
        game.getUI().onActionRequest(game, this, this::handleAction);
    }

    /**
     * Handles the {@link Action} response from the {@link UI}.
     *
     * @param action The {@link Action} response.
     */
    private void handleAction(Action action) {
        callback.accept(action);
    }
}
