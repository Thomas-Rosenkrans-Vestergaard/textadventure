package textadventure;

import textadventure.actions.Action;
import textadventure.rooms.EndingRoom;
import textadventure.ui.GameInterface;

import java.util.ArrayList;
import java.util.List;

public class Game
{

	/**
	 * The {@link GameInterface} used for input-output.
	 */
	private GameInterface gameInterface;

	/**
	 * The {@link Player}s in the {@link Game}.
	 */
	private List<Player> players = new ArrayList<>();

	/**
	 * The {@link Maze} the game is played in.
	 */
	private Maze maze;

	/**
	 * The amount of moves per {@link Player} before the turn ends.
	 */
	private int movesPerTurn;

	/**
	 * The current {@link Player} waiting to send.
	 */
	private Player currentPlayer;

	/**
	 * The amount of moves made by the current {@link Player}.
	 */
	private int currentPlayerMoves;

	/**
	 * The {@link GameInterface} to use for input-output.
	 *
	 * @param gameInterface The {@link GameInterface} to use for input-output.
	 * @param movesPerTurn  The amount of moves per {@link Player} before the turn ends.
	 */
	public Game(GameInterface gameInterface, int movesPerTurn)
	{
		this.gameInterface = gameInterface;
		this.movesPerTurn = movesPerTurn;
		gameInterface.onInit(this);
	}

	/**
	 * Adds the provided {@link Player} to the {@link Game}.
	 *
	 * @param player The {@link Player} to put to the {@link Game}.
	 */
	public void addPlayer(Player player)
	{
		players.add(player);
		gameInterface.onPlayerJoin(this, player);
	}

	/**
	 * Starts the {@link Game}.
	 */
	public void start()
	{
		gameInterface.onGameStart(this);
		handleTurn(players.get(0));
	}

	/**
	 * Plays out the turn of the next {@link Player}.
	 */
	private void handleNext()
	{
		gameInterface.onTurnEnd(this, this.currentPlayer);
		int index = players.indexOf(this.currentPlayer);
		if (index + 1 == players.size()) {

			if (hasWinner()) {
				gameInterface.onGameEnd(this);
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
			if (player.getCharacter().getCurrentLocation() instanceof EndingRoom) {
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
		gameInterface.onTurnStart(this, player);
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
		player.takeTurn(this, this::handleActionResponse);
	}

	/**
	 * Callback by {@link Player}s that have decided on an {@link Action}.
	 *
	 * @param action    The chosen {@link Action}.
	 * @param arguments The arguments to pass to the {@link Action}.
	 */
	private void handleActionResponse(Action action, String[] arguments)
	{
		action.perform(this, this.currentPlayer, arguments);
		this.currentPlayerMoves++;

		if (currentPlayer.getCharacter().getCurrentLocation() instanceof EndingRoom) {
			gameInterface.onGameEnd(this);
			return;
		}

		if (this.currentPlayerMoves > movesPerTurn) {
			handleNext();
		}
	}

	/**
	 * Returns the {@link GameInterface} used as input-output for the game.
	 *
	 * @return The {@link GameInterface} used as input-output for the game.
	 */
	public GameInterface getGameInterface()
	{
		return this.gameInterface;
	}

	/**
	 * Returns the {@link Maze} the game is played in.
	 *
	 * @return The {@link Maze} the game is played in.
	 */
	public Maze getMaze()
	{
		return this.maze;
	}

	/**
	 * Sets the {@link Maze} the game is played in.
	 *
	 * @param maze The {@link Maze} instance to set.
	 */
	public void setMaze(Maze maze)
	{
		this.maze = maze;
	}
}
