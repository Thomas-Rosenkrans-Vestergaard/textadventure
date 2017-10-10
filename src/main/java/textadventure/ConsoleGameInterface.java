package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionException;
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
		System.out.println("init");
	}

	@Override public void onStart(GameController controller)
	{
		System.out.println("The game has begun.");
	}

	@Override public void onEnd(GameController controller)
	{
		System.out.println("The game has ended.");
	}

	@Override public void onQuit(GameController controller)
	{
		System.out.println("quit");
	}

	@Override public void onTurnStart(GameController controller, Player player)
	{
		System.out.println("Turn start: " + player.getName());
	}

	@Override public void onTurnEnd(GameController controller, Player player)
	{
		System.out.println("Turn end: " + player.getName());
	}

	@Override public void onEnter(GameController controller, Player player, Room room)
	{
		System.out.println(player.getName() + " entered: " + room.getName());
	}

	@Override public void onExit(GameController controller, Player player, Room room)
	{
		System.out.println(player.getName() + " exited: " + room.getName());
	}

	@Override public void onActionRequest(GameController controller, Player player, Scenario scenario) throws ActionException
	{
		System.out.println("Current scenario " + scenario.getClass().getSimpleName() + " in room " + scenario.getRoom().getName());

		List<Action> actions = scenario.getActions();
		List<String> choices = actions.stream().map(action -> action.getName()).collect(Collectors.toList());
		int          choice  = io.select("What action do you wish to perform?", choices, "");
		controller.respond(player, scenario, actions.get(choice));
	}

	@Override public void onActionResponse(GameController controller, Player player, Action action)
	{
		System.out.println(player.getName() + " chose to" + action.getName());
	}
}
