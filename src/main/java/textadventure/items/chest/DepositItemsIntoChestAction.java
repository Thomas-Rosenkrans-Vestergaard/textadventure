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
 * {@link textadventure.actions.Action} allowing {@link textadventure.Character}s to deposit {@link Item}s from their
 * {@link textadventure.items.Inventory} to a provided {@link Chest}.
 */
public class DepositItemsIntoChestAction extends ChestAction
{

	/**
	 * The possible {@link Outcome}s of the {@link DepositItemsIntoChestAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Item}(s) were correctly deposited.
		 */
		SUCCESS,

		/**
		 * One or more {@link Item}(s) could not fit in the {@link Chest}.
		 */
		CHEST_FULL,

		/**
		 * No {@link Item}(s) could be deposited, since the {@link Chest} is closed.
		 */
		CLOSED,

		/**
		 * The provided argument could not be parsed to an integer.
		 */
		ARGUMENT_NOT_INT,
	}

	/**
	 * The {@link Outcome} of the {@link DepositItemsIntoChestAction}.
	 */
	private Outcome outcome;

	/**
	 * The {@link ActionPerformCallback} to invoke after performing the {@link DepositItemsIntoChestAction}.
	 */
	private ActionPerformCallback<DepositItemsIntoChestAction> callback;

	/**
	 * The {@link Item}s that were successfully moved.
	 */
	private List<Item> items = new ArrayList<>();

	/**
	 * Creates a new {@link DepositItemsIntoChestAction}.
	 *
	 * @param chest    The {@link Chest} to deposit {@link Item}s into.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link DepositItemsIntoChestAction}.
	 */
	DepositItemsIntoChestAction(Chest chest, ActionPerformCallback<DepositItemsIntoChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link DepositItemsIntoChestAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link DepositItemsIntoChestAction}.
	 * @param arguments The arguments provided to the {@link DepositItemsIntoChestAction}.
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


		if (arguments.length == 1) {
			withArguments(game, player, chest, backpack, arguments[0]);
			return;
		}

		gameInterface.select(game, player, new BaseSelect<>(backpack.asOptions(), selection -> {

			Item currentItem = null;

			try {
				for (Option option : selection) {
					currentItem = backpack.takeItem(option.getOptionIdentifier());
					this.items.add(currentItem);
					chest.addItem(currentItem);
				}
				outcome = Outcome.SUCCESS;
				callback.send(game, player, this);
			} catch (SlotOutOfRangeException | NotEnoughItemsException e) {
				throw new IllegalStateException();
			} catch (InventoryFullException e) {
				returnItem(backpack, currentItem);
				outcome = Outcome.CHEST_FULL;
				callback.send(game, player, this);
			}
		}));
	}

	/**
	 * *Performs the {@link DepositItemsIntoChestAction} using the provided argument.
	 *
	 * @param game The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link DepositItemsIntoChestAction}.
	 * @param chest The {@link Chest} the items get deposited into.
	 * @param backpack The {@link Backpack} the items get taken from.
	 * @param argument The arguments provided to the {@link DepositItemsIntoChestAction}.
	 */
	private void withArguments(Game game, Player player, Chest chest, Backpack backpack, String argument)
	{
		Item currentItem = null;
		try {
			currentItem = backpack.takeItem(Integer.parseInt(argument));
			this.items.add(currentItem);
			chest.addItem(currentItem);
			outcome = Outcome.SUCCESS;
			callback.send(game, player, this);
		} catch (SlotOutOfRangeException | NotEnoughItemsException e) {
			throw new IllegalStateException();
		} catch (InventoryFullException e) {
			returnItem(backpack, currentItem);
			outcome = Outcome.CHEST_FULL;
			callback.send(game, player, this);
		} catch (NumberFormatException e) {
			outcome = DepositItemsIntoChestAction.Outcome.ARGUMENT_NOT_INT;
			callback.send(game, player, this);
		}
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
	 * Returns the {@link Outcome} of the {@link DepositItemsIntoChestAction}.
	 *
	 * @return The {@link Outcome} of the {@link DepositItemsIntoChestAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}

	/**
	 * Returns the {@link Item}(s) that were correctly deposited.
	 *
	 * @return The {@link Item}(s) that were correctly deposited.
	 */
	public ImmutableList<Item> getItems()
	{
		return new ImmutableList.Builder<Item>().addAll(items).build();
	}
}
