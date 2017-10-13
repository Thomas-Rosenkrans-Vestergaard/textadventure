package textadventure.items.equipmentbox;

import textadventure.Action;
import textadventure.ActionException;
import textadventure.Game;
import textadventure.Player;

public class EquipmentBoxInspect implements Action
{
	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	@Override public String getActionName()
	{
		return "inspect";
	}

	/**
	 * Returns a description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	@Override public String getActionDescription()
	{
		return "Inspect the equipment box to see its contents.";
	}

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	@Override public void perform(Game game, Player player) throws ActionException
	{
		throw new UnsupportedOperationException();
	}
}
