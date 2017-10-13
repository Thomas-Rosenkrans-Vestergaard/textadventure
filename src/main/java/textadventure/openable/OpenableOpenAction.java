package textadventure.openable;

import textadventure.Game;
import textadventure.Player;
import textadventure.Action;
import textadventure.ActionException;
import textadventure.ui.UserInterface;

/**
 * Represents an {@link Action} that opens th
 */
public class OpenableOpenAction implements Action
{

	/**
	 * The {@link Openable} object to open.
	 */
	private Openable openable;

	/**
	 * Creates a new {@link OpenableOpenAction}.
	 *
	 * @param openable The {@link Openable} object to open.
	 */
	public OpenableOpenAction(Openable openable)
	{
		this.openable = openable;
	}

	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	@Override
	public String getActionName()
	{
		return "open";
	}

	/**
	 * Returns a description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override
	public String getActionDescription()
	{
		return "Opens the door.";
	}

	/**
	 * Performs the {@link OpenableOpenAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link OpenableOpenAction}.
	 */
	@Override
	public void perform(Game game, Player player) throws ActionException
	{
		UserInterface userInterface = game.getUserInterface();

		try {
			openable.open();
			userInterface.onOpen(game, openable, player);
		} catch (OpenableAlreadyOpenException e) {

		} catch (CannotOpenException e) {
			game.getUserInterface().write("Cannot open door.");
		}
	}
}
