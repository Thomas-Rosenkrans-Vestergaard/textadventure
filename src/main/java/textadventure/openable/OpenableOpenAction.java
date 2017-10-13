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
			userInterface.write("The door is now open.");
		} catch (OpenableAlreadyOpenException e) {
			userInterface.write("The door is already open.");
		} catch (CannotOpenException e) {
			userInterface.write("Cannot open door.");
		}
	}
}
