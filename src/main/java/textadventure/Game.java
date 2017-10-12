package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionException;
import textadventure.rooms.EndingRoom;
import textadventure.scenario.Scenario;
import textadventure.ui.UI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Game
{

	/**
	 * The {@link UI} used for input-output.
	 */
	private UI ui;

	/**
	 * The {@link Player}s in the {@link Game}.
	 */
	private List<Player> players = new ArrayList<>();

	/**
	 * The {@link Player}s in the {@link Game} mapped by their name.
	 */
	private Map<String, Player> names = new HashMap<>();

	/**
	 * The {@link Player} in the {@link Game} mapped to their current {@link Scenario}s.
	 */
	private Map<Player, Scenario> scenarios = new HashMap<>();

	/**
	 * The {@link Maze} the {@link Game} is played within.
	 */
	private Maze maze;

	/**
	 * The amount of moves per {@link Player} before the turn ends.
	 */
	private int movesPerTurn;

	/**
	 * The current {@link Player} waiting to respond.
	 */
	private Player currentPlayer;

	/**
	 * The amount of moves made by the current {@link Player}.
	 */
	private int currentPlayerMoves;

	/**
	 * The {@link UI} to use for input-output.
	 *
	 * @param ui           The {@link UI} to use for input-output.
	 * @param movesPerTurn The amount of moves per {@link Player} before the turn ends.
	 */
	public Game(UI ui, Maze maze, int movesPerTurn)
	{
		this.ui = ui;
		this.maze = maze;
		this.movesPerTurn = movesPerTurn;
		ui.onInit(this);
	}

	/**
	 * Adds the provided {@link Player} to the {@link Game}.
	 *
	 * @param player The {@link Player} to add to the {@link Game}.
	 */
	public void addPlayer(Player player)
	{
		players.add(player);
		names.put(player.getName(), player);
		ui.onPlayer(this, player);
	}

	/**
	 * Starts the {@link Game}.
	 */
	public void start()
	{
		ui.onGameStart(this);
		handleTurn(players.get(0));
	}

	/**
	 * Plays out the turn of the next {@link Player}.
	 */
	private void handleNext()
	{
		ui.onTurnEnd(this, this.currentPlayer);
		int index = players.indexOf(this.currentPlayer);
		if (index + 1 == players.size()) {

			if (hasWinner()) {
				ui.onGameEnd(this);
				return;
			}

			handleTurn(players.get(0));
			return;
		}

		handleTurn(players.get(index + 1));
	}

	/**
	 * Checks if a {@link Player} has reached the {@link EndingRoom}.
	 *
	 * @return <code>True</code> if a {@link Player} has reached the {@link EndingRoom}, otherwise <code>false</code>.
	 */
	private boolean hasWinner()
	{
		for (Player player : players) {
			if (player.getCurrentLocation() instanceof EndingRoom) {
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
	private void handleTurn(Player player)
	{
		this.movesPerTurn = 0;
		ui.onTurnStart(this, player);
		handleActionRequest(player);
	}

	/**
	 * Requests an {@link Action} from the provided {@link Player}.
	 *
	 * @param player The {@link Player} to request an {@link Action} from.
	 */
	private void handleActionRequest(Player player)
	{
		this.currentPlayer = player;
		player.takeTurn(this, scenarios.get(player), this::handleActionResponse);
	}

	/**
	 * Callback by {@link Player}s that have decided on an {@link Action}.
	 *
	 * @param action The chosen {@link Action}.
	 */
	private void handleActionResponse(Action action)
	{
		try {
			action.perform(this, this.currentPlayer.getFocus(), this.currentPlayer);
			this.currentPlayerMoves++;
			if (this.currentPlayerMoves > movesPerTurn) {
				handleNext();
			}
		} catch (ActionException e) {
			ui.onException(e);
			handleActionRequest(this.currentPlayer);
		}
	}

	/**
	 * Returns the {@link Maze} that the {@link Game} is played within.
	 *
	 * @return The {@link Maze} that the {@link Game} is played within.
	 */
	public Maze getMaze()
	{
		return this.maze;
	}

	/**
	 * Returns the {@link UI} used as input-output for the game.
	 *
	 * @return The {@link UI} used as input-output for the game.
	 */
	public UI getUI()
	{
		return this.ui;
	}
}