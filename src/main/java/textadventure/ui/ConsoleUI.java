package textadventure.ui;

import textadventure.Game;
import textadventure.GameException;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.scenario.Scenario;
import textio.SysTextIO;
import textio.TextIO;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class ConsoleUI implements UI
{
	private TextIO io = new TextIO(new SysTextIO());

	@Override public void onInit(Game game)
	{
		io.put("");
	}

	/**
	 * Called when a new {@link Player} joins the {@link Game}.
	 *
	 * @param player The newly joined {@link Player}.
	 */
	@Override public void onPlayer(Game game, Player player)
	{
		System.out.println(player.getName() + " joined.");
	}

	@Override public void onGameStart(Game game)
	{
		io.put(game.getMaze().getStartingRoom().getStartingMessage());
	}

	@Override public void onGameEnd(Game game)
	{
		io.put("You enter the " + game.getMaze().getEndingRoom().getName() + "\n" + game.getMaze()
																						.getEndingRoom()
																						.getEndingMessage
																								());
	}

	@Override public void onQuit(Game game)
	{
		io.put("onQuit");
	}

	@Override public void onTurnStart(Game game, Player player)
	{
		io.put("It's " + player.getName() + "'s turn. \n");
	}

	@Override public void onTurnEnd(Game game, Player player)
	{
		io.put(player.getName() + "'s turn " +
			   "is over.\n" + "------------------------------------------------------------\n");
	}

	/**
	 * Called when a {@link Player} requests an {@link Action} from the {@link UI}.
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} who requests the {@link Action}.
	 * @param scenario The {@link Scenario} to respond to.
	 * @param callback The callback to respond with.
	 */
	@Override public void onActionRequest(
			Game game, Player player, Scenario scenario, Consumer<Action> callback)
	{
		System.out.println(scenario.getDescription());

		List<Action> actions = scenario.getActions().collect(Collectors.toList());
		List<String> choices = actions.stream().map(action -> action.getIdentifier()).collect(Collectors.toList());
		io.put("What would you like to do?");

		String choice = io.get();
		int    index  = choices.indexOf(choice);

		while (index == -1) {
			io.put("You cant do that.");
			choice = io.get();
			index = choices.indexOf(choice);
		}

		callback.accept(actions.get(choices.indexOf(choice)));
	}

	@Override public void onActionResponse(Game game, Player player, Action action)
	{
		System.out.println(player.getName() + " chose to" + action.getIdentifier());
	}

	@Override public void onException(GameException e)
	{
		e.printStackTrace();
	}

	/**
	 * Sends a message to the {@link Player}s in the {@link Game}.
	 *
	 * @param message The message to send.
	 * @param type    The type of message to send.
	 */
	@Override public void onMessage(String message, UIMessage type)
	{
		System.out.println(message);
	}

	/**
	 * Sends a message to the provided {@link Player}.
	 *
	 * @param message The message to send.
	 * @param type    The type of message to send.
	 * @param player  The {@link Player} to send the message to.
	 */
	@Override public void onMessage(String message, UIMessage type, Player player)
	{
		System.out.println(message);
	}
}
