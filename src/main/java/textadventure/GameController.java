package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.exception.UnknownPlayerException;
import textadventure.scenario.EmptyRoomScenario;
import textadventure.scenario.Scenario;

import java.util.HashMap;
import java.util.Map;

public class GameController
{

	/**
	 * The {@link GameInterface} used for input-output.
	 */
	private GameInterface gameInterface;

	/**
	 * The players that exists in the game.
	 */
	private Map<String, Player> players;

	/**
	 * The {@link Maze}.
	 */
	private Maze maze;

	/**
	 * The {@link GameInterface} to use for input-output.
	 *
	 * @param gameInterface The {@link GameInterface} to use for input-output.
	 */
	public GameController(GameInterface gameInterface, Maze maze, Player player)
	{
		this.gameInterface = gameInterface;
		this.maze = maze;
		this.players = new HashMap<>();
		this.players.put(player.getName(), player);
		gameInterface.onInit(this);
	}

	public void start() throws UnknownPlayerException
	{
		gameInterface.onStart(this);
		while (!hasEnd()) {
			for (Player player : players.values()) {
				handleTurn(player);
			}
		}
	}

	private boolean hasEnd(){
	    for(Player player : players.values()){
	        if(player.getCurrentLocation() instanceof EndingRoom){
	            return true;
            }
        }

        return false;
    }

	/**
	 * Handles the turn of the provided {@link Player}.
	 *
	 * @param player The {@link Player}
	 */
	private void handleTurn(Player player) throws UnknownPlayerException
	{
		gameInterface.onTurnStart(this, player);
		player.takeTurn(this, gameInterface, new EmptyRoomScenario(player.getCurrentLocation()));
		gameInterface.onTurnEnd(this, player);
	}

	public void respond(Player player, Scenario scenario, Action action) throws ActionException {
        if (scenario.canPerform(action)) {
            action.perform(this, player);
        }
    }

	public Maze getMaze()
	{
		return this.maze;
	}
}
