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
		io.put("onInit");
	}

	@Override public void onStart(GameController controller)
	{
		io.put("onStart");
	}

	@Override public void onEnd(GameController controller)
	{
		io.put("onEnd");
	}

	@Override public void onQuit(GameController controller)
	{
		io.put("onQuit");
	}

	@Override public void onTurnStart(GameController controller, Player player)
	{
		io.put("onTurnStart " + player.getName());
	}

	@Override public void onTurnEnd(GameController controller, Player player)
	{
		io.put("onTurnEnd " + player.getName());
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
		System.out.println("Current scenario " + scenario.getClass().getSimpleName() + " in room " + scenario.getRoom
				().getName());

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
