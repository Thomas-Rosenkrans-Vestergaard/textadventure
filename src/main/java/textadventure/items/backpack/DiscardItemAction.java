package textadventure.items.backpack;

import textadventure.Game;
import textadventure.Player;

/**
 * {@link textadventure.actions.Action} that allows a player to discard {@link textadventure.items.Item}(s) from the
 * {@link Backpack}.
 */
public class DiscardItemAction extends BackpackAction
{

	/**
	 * Creates a new {@link DiscardItemAction}.
	 *
	 * @param backpack The {@link Backpack} to discard {@link textadventure.items.Item}s from.
	 */
	public DiscardItemAction(Backpack backpack)
	{
		super(backpack);
	}

	/**
	 * Performs the {@link DiscardItemAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link DiscardItemAction}.
	 * @param arguments The arguments provided to the {@link DiscardItemAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		throw new UnsupportedOperationException();
	}
}
