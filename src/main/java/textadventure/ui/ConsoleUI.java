package textadventure.ui;

import com.google.common.collect.ImmutableMap;
import textadventure.Game;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.scenario.Scenario;

import java.util.Arrays;
import java.util.function.Consumer;

public class ConsoleUI implements UI
{
	/**
	 * Called when the {@link Game} is constructed.
	 *
	 * @param game The newly constructed {@link Game}.
	 */
	@Override public void onInit(Game game)
	{

	}

	/**
	 * Called when a new {@link Player} joins the {@link Game}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The newly joined {@link Player}.
	 */
	@Override public void onPlayer(Game game, Player player)
	{

	}

	/**
	 * Called when the {@link Game} starts.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override public void onGameStart(Game game)
	{

	}

	/**
	 * Called when the {@link Game} ends.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override public void onGameEnd(Game game)
	{

	}

	/**
	 * Called when the {@link Game} is prompted to quit.
	 *
	 * @param game The {@link Game} instance.
	 */
	@Override public void onQuit(Game game)
	{

	}

<<<<<<< HEAD
	@Override public void onTurnStart(Game game, Player player){}

	@Override public void onTurnEnd(Game game, Player player){}
=======
	/**
	 * Called when the turn rotates.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn it is.
	 */
	@Override public void onTurnStart(Game game, Player player)
	{

	}

	/**
	 * Called when a {@link Player} loses their turn.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} whose turn ended.
	 */
	@Override public void onTurnEnd(Game game, Player player)
	{

	}
>>>>>>> ui

	/**
	 * Called when a {@link Player} requests an {@link Action} from the {@link UI}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} who requests the {@link Action}.
	 * @param scenario The {@link Scenario} to respond to.
	 * @param callback The callback to respond with.
	 */
	@Override public void onActionRequest(Game game, Player player, Scenario scenario, Consumer<Action> callback)
	{
<<<<<<< HEAD
		ImmutableMap<String, Focusable> focusable = player.getCurrentLocation().getFocusable();

		while (true) {
			Focusable focus = player.getFocus();
			io.put("What would you like to do?");
			String choice = io.get();

			if (choice.startsWith("focus")) {
				choice = choice.substring(choice.indexOf(" ")).trim();
				if(!focusable.containsKey(choice)){
					io.put("Invalid action.\n");
					continue;
				}
				player.setFocus(focusable.get(choice));
				io.put("You focused on " + focusable.get(choice).getIdentifier() + "\n");
				continue;
			}

			if (focus == null) {
				io.put("You must first focus something.\n");
				continue;
			}

			ImmutableMap<String, Action> actions = focus.getActions();

			if (!actions.containsKey(choice)) {
				io.put("You cant do that!\n");
				continue;
			}

			callback.accept(actions.get(choice));
			break;
		}
	}
=======
>>>>>>> ui

	}

	/**
	 * Called when a {@link Player} responds to a request for {@link Action}.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} who responded to a request for {@link Action}.
	 * @param action The chosen {@link Action}.
	 */
	@Override public void onActionResponse(Game game, Player player, Action action)
	{

	}

	/**
	 * Requests a {@link Selectable} option from the {@link UI}.
	 *
	 * @param selectable The {@link Selectable} options.
	 * @param player     The {@link Player} to request a {@link Selectable} element from.
	 * @param callback   The callback to use when responding to the select request.
	 */
	@Override public <T extends Selectable> void select(
			ImmutableMap<String, T> selectable, Player player, Consumer<T> callback)
	{

	}

	/**
	 * Sends a message to the {@link Player}s in the {@link Game}.
	 *
	 * @param message The {@link UIMessage} to send.
	 * @param player  The {@link Player} to send the {@link UIMessage} to.
	 */
	@Override public void onMessage(UIMessage message, Player player)
	{

	}
}
