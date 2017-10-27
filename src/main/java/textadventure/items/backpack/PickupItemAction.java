package textadventure.items.backpack;

import com.google.common.collect.ImmutableList;
import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.InventoryFullException;
import textadventure.items.Item;
import textadventure.items.NotEnoughItemsException;
import textadventure.items.SlotOutOfRangeException;
import textadventure.items.chest.TakeItemFromChestAction;
import textadventure.rooms.Floor;
import textadventure.ui.BaseSelect;
import textadventure.ui.GameInterface;
import textadventure.ui.Option;

/**
 * {@link textadventure.actions.Action} that allows a player to pickup items {@link textadventure.items.Item}(s) from
 * the {@link Floor}.
 */
public class PickupItemAction extends BackpackAction
{

	/**
	 * The possible {@link Outcome}s of the {@link PickupItemAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Item}(s) were correctly picked up.
		 */
		SUCCESS,

		/**
		 * One or more {@link Item} could not be picked up, since the {@link Backpack} of the {@link Character} was
		 * full.
		 */
		INVENTORY_FULL,
	}

	/**
	 * The {@link Outcome} of the {@link PickupItemAction}.
	 */
	private Outcome outcome;

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	private ActionPerformCallback<PickupItemAction> callback;

	/**
	 * The {@link Item}(s) that were picked up.
	 */
	private ImmutableList.Builder<Item> items = new ImmutableList.Builder<>();

	/**
	 * Creates a new {@link PickupItemAction}.
	 *
	 * @param backpack The {@link Backpack} to discard {@link textadventure.items.Item}s from.
	 */
	public PickupItemAction(Backpack backpack, ActionPerformCallback<PickupItemAction> callback)
	{
		super(backpack);

		this.callback = callback;
	}

	/**
	 * Performs the {@link PickupItemAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link PickupItemAction}.
	 * @param arguments The arguments provided to the {@link PickupItemAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		GameInterface gameInterface = game.getGameInterface();
		Floor         floor         = player.getCharacter().getCurrentLocation().getFloor();
		Backpack      backpack      = getBackpack();
		gameInterface.select(game, player, new BaseSelect<>(floor.asOptions(), selection -> {
			try {
				for (Option option : selection) {
					Item item = floor.takeItem(option.getOptionIdentifier());
					backpack.addItem(item);
					this.items.add(item);
				}

				this.outcome = Outcome.SUCCESS;
				callback.send(game, player, this);
			} catch (SlotOutOfRangeException | NotEnoughItemsException | InventoryFullException e) {
				throw new IllegalStateException();
			}
		}));
	}

	/**
	 * Returns the {@link Outcome} of the {@link PickupItemAction}.
	 *
	 * @return The {@link Outcome} of the {@link PickupItemAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}

	/**
	 * Returns the {@link Item}(s) that were picked up.
	 *
	 * @return The {@link Item}(s) that were picked up.
	 */
	public ImmutableList<Item> getItems()
	{
		return items.build();
	}
}
