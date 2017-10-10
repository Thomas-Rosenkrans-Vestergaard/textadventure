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
	 * Tracks the location of the {@link Player}s in the {@link GameController}.
	 */
	private PlayerLocationTracker locationTracker = new PlayerLocationTracker();

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
		this.locationTracker.setLocation(player, maze.getStartingRoom());
		gameInterface.onInit(this);
	}

	public void start() throws UnknownPlayerException
	{
		gameInterface.onStart(this);
		while (!locationTracker.hasEnd()) {
			for (Player player : players.values()) {
				handleTurn(player);
			}
		}
	}

	/**
	 * Handles the turn of the provided {@link Player}.
	 *
	 * @param player The {@link Player}
	 */
	private void handleTurn(Player player) throws UnknownPlayerException
	{
		Room currentLocation = locationTracker.getLocation(player);
		gameInterface.onTurnStart(this, player);
		player.takeTurn(this, gameInterface, new EmptyRoomScenario(currentLocation));
		gameInterface.onTurnEnd(this, player);
	}

	public void respond(Player player, Scenario scenario, Action action) throws ActionException
	{
		try {
			Room currentLocation = locationTracker.getLocation(player);
			if (scenario.canPerform(action)) {
				action.perform(this, player, currentLocation);
			}
		} catch (UnknownPlayerException e) {
			throw new IllegalStateException();
		}
	}

	public PlayerLocationTracker getLocationTracker()
	{
		return this.locationTracker;
	}

	public Maze getMaze()
	{
		return this.maze;
	}
}
