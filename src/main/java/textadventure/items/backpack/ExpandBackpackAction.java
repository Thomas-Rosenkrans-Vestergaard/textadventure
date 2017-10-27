package textadventure.items.backpack;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.Item;
import textadventure.items.NotEnoughItemsException;
import textadventure.items.SlotOutOfRangeException;
import textadventure.ui.BaseSelect;
import textadventure.ui.GameInterface;

/**
 * The {@link ExpandBackpackAction} allows a {@link Character} to expand the number of slots of a {@link Backpack}.
 */
public class ExpandBackpackAction extends BackpackAction
{

	/**
	 * The possible {@link Outcome}s of the {@link ExpandBackpackAction}.
	 */
	public enum Outcome
	{
		/**
		 * The {@link Backpack} was expanded.
		 */
		SUCCESS,

		/**
		 * The provided argument could not be parsed to an integer.
		 */
		ARGUMENT_NOT_INT,

		/**
		 * The provided argument pointed to an empty backpack stack.
		 */
		EMPTY_BACKPACK_SLOT,

		/**
		 * The {@link textadventure.items.Item} selected by the {@link Player} is not a {@link BackpackExpansion}.
		 */
		SELECTED_NOT_EXPANSION,
	}

	/**
	 * The {@link Outcome} of the {@link ExpandBackpackAction}.
	 */
	private Outcome outcome;

	/**
	 * The {@link BackpackExpansion} used to expand the {@link Backpack}.
	 */
	private BackpackExpansion backpackExpansion;

	/**
	 * The {@link ActionPerformCallback} to invoke after performing the {@link ExpandBackpackAction}.
	 */
	private ActionPerformCallback<ExpandBackpackAction> callback;

	/**
	 * Creates a new {@link ExpandBackpackAction}.
	 *
	 * @param backpack The {@link Backpack} to expand.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link ExpandBackpackAction}.
	 */
	public ExpandBackpackAction(Backpack backpack, ActionPerformCallback<ExpandBackpackAction> callback)
	{
		super(backpack);

		this.callback = callback;
	}

	/**
	 * Performs the {@link ExpandBackpackAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link ExpandBackpackAction}.
	 * @param arguments The arguments provided to the {@link ExpandBackpackAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		GameInterface gameInterface = game.getGameInterface();
		Backpack      backpack      = player.getCharacter().getBackpack();

		if (arguments.length == 1) {
			withArguments(game, player, backpack, arguments[0]);
			return;
		}

		gameInterface.select(game, player, new BaseSelect<>(backpack.asOptions(), 1, selection -> {

			try {
				Item item = backpack.takeItem(selection.get(0).getOptionIndex());

				if (!(item instanceof BackpackExpansion)) {
					outcome = Outcome.SELECTED_NOT_EXPANSION;
					callback.send(game, player, this);
					return;
				}

				this.backpackExpansion = (BackpackExpansion) item;
				backpack.expand(backpackExpansion.getUpgradeAmount());
				outcome = Outcome.SUCCESS;
				callback.send(game, player, this);

			} catch (SlotOutOfRangeException | NotEnoughItemsException e) {
				throw new IllegalStateException(e);
			}
		}));
	}

	/**
	 * Performs the {@link ExpandBackpackAction} using the provided argument.
	 *
	 * @param game     The {@link Game} instance.
	 * @param player   The {@link Player} performing the {@link ExpandBackpackAction}.
	 * @param backpack The {@link Backpack} the key is used from.
	 * @param argument The arguments provided to the {@link ExpandBackpackAction}.
	 */
	private void withArguments(Game game, Player player, Backpack backpack, String argument)
	{
		try {
			Item item = backpack.takeItem(Integer.parseInt(argument));

			if (!(item instanceof BackpackExpansion)) {
				outcome = Outcome.SELECTED_NOT_EXPANSION;
				callback.send(game, player, this);
				return;
			}

			BackpackExpansion backpackExpansion = (BackpackExpansion) item;
			backpack.expand(backpackExpansion.getUpgradeAmount());
			outcome = Outcome.SUCCESS;
			callback.send(game, player, this);

		} catch (NumberFormatException e) {
			outcome = Outcome.ARGUMENT_NOT_INT;
			callback.send(game, player, this);
		} catch (SlotOutOfRangeException | NotEnoughItemsException e) {
			outcome = Outcome.EMPTY_BACKPACK_SLOT;
			callback.send(game, player, this);
		}
	}

	/**
	 * Returns the {@link Outcome} of the {@link ExpandBackpackAction}.
	 *
	 * @return The {@link Outcome} of the {@link ExpandBackpackAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}

	/**
	 * Returns the {@link BackpackExpansion} used to expand the {@link Backpack}.
	 *
	 * @return The {@link BackpackExpansion} used to expand the {@link Backpack}.
	 */
	public BackpackExpansion getBackpackExpansion()
	{
		return this.backpackExpansion;
	}
}
