package textadventure.actions;

import textadventure.GameController;
import textadventure.Player;

public class GoBackAction implements Action
{
	
	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	@Override public String getName()
	{
		return null;
	}

	/**
	 * Returns the description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getDescription()
	{
		return null;
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param controller The {@link GameController}.
	 * @param player     The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(GameController controller, Player player) throws ActionException
	{

	}
}
