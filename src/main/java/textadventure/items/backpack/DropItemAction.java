package textadventure.items.backpack;

import textadventure.Game;
import textadventure.Player;

/**
 * {@link textadventure.actions.Action} that allows a player to discard {@link textadventure.items.Item}(s) from the
 * {@link Backpack}.
 */
public class DropItemAction extends BackpackAction
{

	/**
	 * The possible {@link Outcome}s of the {@link DropItemAction}.
	 */
	public enum Outcome
	{
		SUCCESS,
	}

	/**
	 * The {@link Outcome} of the {@link DropItemAction}.
	 */
	private Outcome outcome;

	/**
	 * Creates a new {@link DropItemAction}.
	 *
	 * @param backpack The {@link Backpack} to discard {@link textadventure.items.Item}s from.
	 */
	public DropItemAction(Backpack backpack)
	{
		super(backpack);
	}

	/**
	 * Performs the {@link DropItemAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link DropItemAction}.
	 * @param arguments The arguments provided to the {@link DropItemAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the {@link Outcome} of the {@link DropItemAction}.
	 *
	 * @return The {@link Outcome} of the {@link DropItemAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}
}
