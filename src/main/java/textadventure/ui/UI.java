package textadventure.ui;

import textadventure.Game;
import textadventure.GameException;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.scenario.Scenario;

import java.util.function.Consumer;

public interface UI
{

	/**
	 * Called when the {@link Game} is constructed.
	 *
	 * @param game The newly constructed {@link Game}.
	 */
	void onInit(Game game);

	/**
	 * Called when a new {@link Player} joins the {@link Game}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The newly joined {@link Player}.
	 */
	void onPlayer(Game game, Player player);

	/**
	 * Called when the {@link Game} starts.
	 *
	 * @param game The {@link Game} instance.
	 */
	void onGameStart(Game game);

	/**
	 * Called when the {@link Game} ends.
	 *
	 * @param game The {@link Game} instance.
	 */
	void onGameEnd(Game game);

	/**
	 * Called when the {@link Game} is prompted to quit.
	 *
	 * @param game The {@link Game} instance.
	 */
	void onQuit(Game game);

	/**
	 * Called when the turn rotates.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn it is.
	 */
	void onTurnStart(Game game, Player player);

	/**
	 * Called when a {@link Player} requests an {@link Action} from the {@link UI}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} who requests the {@link Action}.
	 * @param scenario The {@link Scenario} to respond to.
	 * @param callback The callback to respond with.
	 */
	void onActionRequest(Game game, Player player, Scenario scenario, Consumer<Action> callback);

	/**
	 * Called when a {@link Player} responds to a request for {@link Action}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who responded to a request for {@link Action}.
	 * @param action The chosen {@link Action}.
	 */
	void onActionResponse(Game game, Player player, Action action);

	/**
	 * Called when a {@link Player} loses their turn.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn ended.
	 */
	void onTurnEnd(Game game, Player player);

	/**
	 * Called when the {@link Game} wants to pass a {@link GameException} to the {@link UI}.
	 *
	 * @param e The {@link GameException} to pass to the {@link UI}.
	 */
	void onException(GameException e);

	/**
	 * Sends a message to the {@link Player}s in the {@link Game}.
	 *
	 * @param message The message to send.
	 * @param type    The type of message to send.
	 */
	void onMessage(String message, UIMessage type);

	/**
	 * Sends a message to the provided {@link Player}.
	 *
	 * @param message The message to send.
	 * @param type    The type of message to send.
	 * @param player  The {@link Player} to send the message to.
	 */
	void onMessage(String message, UIMessage type, Player player);
}
