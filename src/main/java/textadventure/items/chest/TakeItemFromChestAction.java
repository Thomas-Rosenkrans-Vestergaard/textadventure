package textadventure.items.chest;

import com.google.common.collect.ImmutableList;
import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.InventoryFullException;
import textadventure.items.Item;
import textadventure.items.NotEnoughItemsException;
import textadventure.items.SlotOutOfRangeException;
import textadventure.items.backpack.Backpack;
import textadventure.ui.BaseSelect;
import textadventure.ui.GameInterface;
import textadventure.ui.Option;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link textadventure.actions.Action} that allows a player to take item(s) from the {@link Chest}.
 */
public class TakeItemFromChestAction extends ChestAction
{
	/**
	 * The possible {@link Outcome}s of the {@link TakeItemFromChestAction}.
	 */
	public enum Outcome
	{


		/**
		 * The {@link Item}(s) were correctly taken.
		 */
		SUCCESS,

		/**
		 * One or more {@link Item}(s) could not fit in the {@link Backpack}.
		 */
		BACKPACK_FULL,

		/**
		 * No {@link Item}(s) could be taken, since the {@link Chest} is closed.
		 */
		CLOSED,
	}

	/**
	 * The {@link Outcome} of the {@link TakeItemFromChestAction}.
	 */
	private Outcome outcome;

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	private ActionPerformCallback<TakeItemFromChestAction> callback;

	/**
	 * The {@link Item}(s) being taken from the {@link Chest}.
	 */
	private List<Item> items = new ArrayList<>();

	/**
	 * Creates a new {@link OpenChestAction}.
	 *
	 * @param chest    The {@link Chest} where the {@link Item} is taken from.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	TakeItemFromChestAction(Chest chest, ActionPerformCallback<TakeItemFromChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link TakeItemFromChestAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link TakeItemFromChestAction}.
	 * @param arguments The arguments provided to the {@link TakeItemFromChestAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		Chest.State   state         = chest.getState();
		GameInterface gameInterface = game.getGameInterface();

		if (state == Chest.State.CLOSED) {
			outcome = Outcome.CLOSED;
			callback.send(game, player, this);
			return;
		}

		Backpack backpack = player.getCharacter().getBackpack();
		gameInterface.select(game, player, new BaseSelect<>(chest.asOptions(), selection -> {

			Item currentItem = null;

			try {
				for (Option option : selection) {
					currentItem = chest.takeItem(option.getOptionIdentifier());
					this.items.add(currentItem);
					backpack.addItem(currentItem);
				}

				outcome = Outcome.SUCCESS;
				callback.send(game, player, this);
			} catch (SlotOutOfRangeException | NotEnoughItemsException e) {
				throw new IllegalStateException();
			} catch (InventoryFullException e) {
				returnItem(backpack, currentItem);
				outcome = Outcome.BACKPACK_FULL;
				callback.send(game, player, this);
			}
		}));

	}

	/**
	 * Returns the provided {@link Item} to the provided {@link Backpack}.
	 *
	 * @param backpack The {@link Backpack} to return the provided {@link Item} to.
	 * @param item     The {@link Item} to return.
	 */
	private void returnItem(Backpack backpack, Item item)
	{
		if (item == null) return;

		try {
			backpack.addItem(item);
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	/**
	 * Returns the {@link Outcome} of the {@link TakeItemFromChestAction}.
	 *
	 * @return The {@link Outcome} of the {@link TakeItemFromChestAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}

	/**
	 * Returns the {@link Item}(s) that were correctly taken.
	 *
	 * @return The {@link Item}(s) that were correctly taken.
	 */
	public ImmutableList<Item> getItems()
	{
		return new ImmutableList.Builder<Item>().addAll(items).build();
	}
}
