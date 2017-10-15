package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ImmutableActionRegistry;
import textadventure.actions.MutableActionRegistry;
import textadventure.doors.*;
import textadventure.items.chest.CloseChestAction;
import textadventure.items.chest.InspectChestAction;
import textadventure.items.chest.OpenChestAction;
import textadventure.lock.InspectLockAction;
import textadventure.lock.Lock;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;
import textadventure.rooms.EndingRoom;
import textadventure.ui.UserInterface;

import java.util.ArrayList;
import java.util.List;

public class Game
{

	/**
	 * The {@link UserInterface} used for input-output.
	 */
	private UserInterface userInterface;

	/**
	 * The {@link Player}s in the {@link Game}.
	 */
	private List<Player> players = new ArrayList<>();

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

	private MutableActionRegistry actionRegistry = new MutableActionRegistry();

	/**
	 * The {@link UserInterface} to use for input-output.
	 *
	 * @param userInterface The {@link UserInterface} to use for input-output.
	 * @param movesPerTurn  The amount of moves per {@link Player} before the turn ends.
	 */
	public Game(UserInterface userInterface, Maze maze, int movesPerTurn)
	{
		this.userInterface = userInterface;
		this.maze = maze;
		this.movesPerTurn = movesPerTurn;

		this.actionRegistry.addAction("door", new OpenDoorAction(null));
		this.actionRegistry.addAction("door", new CloseDoorAction(null));
		this.actionRegistry.addAction("door", new InspectDoorAction(null));
		this.actionRegistry.addAction("door", new UseDoorAction(null));
		this.actionRegistry.addAction("door", new LockLockAction(null));
		this.actionRegistry.addAction("door", new UnlockLockAction(null));

		this.actionRegistry.addAction("lock", new LockLockAction(null));
		this.actionRegistry.addAction("lock", new UnlockLockAction(null));
		this.actionRegistry.addAction("lock", new InspectLockAction(null));

		this.actionRegistry.addAction("chest", new OpenChestAction(null));
		this.actionRegistry.addAction("chest", new CloseChestAction(null));
		this.actionRegistry.addAction("chest", new InspectChestAction(null));

		userInterface.onInit(this);
	}

	/**
	 * Adds the provided {@link Player} to the {@link Game}.
	 *
	 * @param player The {@link Player} to add to the {@link Game}.
	 */
	public void addPlayer(Player player)
	{
		players.add(player);
		userInterface.onPlayerJoin(this, player);
	}

	/**
	 * Starts the {@link Game}.
	 */
	public void start()
	{
		userInterface.onGameStart(this);
		handleTurn(players.get(0));
	}

	/**
	 * Plays out the turn of the next {@link Player}.
	 */
	private void handleNext()
	{
		userInterface.onTurnEnd(this, this.currentPlayer);
		int index = players.indexOf(this.currentPlayer);
		if (index + 1 == players.size()) {

			if (hasWinner()) {
				userInterface.onGameEnd(this);
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
		userInterface.onTurnStart(this, player);
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
	 * @param action The chosen {@link Action}.
	 */
	private void handleActionResponse(Action action)
	{
		action.perform(this, this.currentPlayer);
		this.currentPlayerMoves++;
		if (this.currentPlayerMoves > movesPerTurn) {
			handleNext();
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
	 * Returns the {@link UserInterface} used as input-output for the game.
	 *
	 * @return The {@link UserInterface} used as input-output for the game.
	 */
	public UserInterface getUserInterface()
	{
		return this.userInterface;
	}

	public ImmutableActionRegistry getActionRegistry()
	{
		return this.actionRegistry.immutable();
	}
}
