package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.rooms.Room;
import textadventure.scenario.Scenario;

public interface GameInterface
{
	void onInit(GameController controller);

	void onStart(GameController controller);

	void onEnd(GameController controller);

	void onQuit(GameController controller);

	void onTurnStart(GameController controller, Player player);

	void onActionRequest(GameController controller, Player player, Scenario scenario) throws ActionException;

	void onActionResponse(GameController controller, Player player, Action action);

	void onTurnEnd(GameController controller, Player player);

	void onEnter(GameController controller, Player player, Room room);

	void onExit(GameController controller, Player player, Room room);
}
