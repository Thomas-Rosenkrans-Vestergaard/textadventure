package textadventure.console;

import textadventure.GameController;
import textadventure.GameInterface;
import textadventure.Player;
import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.rooms.Room;
import textadventure.scenario.Scenario;
import textio.SysTextIO;
import textio.TextIO;

import java.util.List;
import java.util.stream.Collectors;

public class ConsoleGameInterface implements GameInterface
{
	private TextIO io = new TextIO(new SysTextIO());

	@Override public void onInit(GameController controller)
	{
		io.put("");
	}

	@Override public void onStart(GameController controller)
	{
		io.put(controller.getMaze().getStartingRoom().getStartingMessage());
	}

	@Override public void onEnd(GameController controller)
	{
		io.put("You enter the " + controller.getMaze().getEndingRoom().getName() + "\n" + controller.getMaze()
																									.getEndingRoom()
																									.getEndingMessage
																											());
	}

	@Override public void onQuit(GameController controller)
	{
		io.put("onQuit");
	}

	@Override public void onTurnStart(GameController controller, Player player)
	{
		io.put("It's " + player.getName() + "'s turn. \n");
	}

	@Override public void onTurnEnd(GameController controller, Player player)
	{
		io.put(player.getName() + "'s turn " +
			   "is over.\n" + "------------------------------------------------------------\n");
	}

	@Override public void onEnter(GameController controller, Player player, Room room)
	{
		io.put("onEnter " + player.getName() + " " + room.getName());
	}

	@Override public void onExit(GameController controller, Player player, Room room)
	{
		io.put("onExit " + player.getName() + " " + room.getName());
	}

	@Override public void onActionRequest(
			GameController controller, Player player, Scenario scenario) throws ActionException
	{
		System.out.println(scenario.getDescription());

		List<Action> actions = scenario.getActions();
		List<String> choices = actions.stream().map(action -> action.getName()).collect(Collectors.toList());
		io.put("What would you like to do?");

		String choice = io.get();
		int    index  = choices.indexOf(choice);

		while (index == -1) {
			io.put("You cant do that.");
			choice = io.get();
			index = choices.indexOf(choice);
		}

		controller.respond(player, scenario, actions.get(choices.indexOf(choice)));
	}

	@Override public void onActionResponse(GameController controller, Player player, Action action)
	{
		System.out.println(player.getName() + " chose to" + action.getName());
	}
}
