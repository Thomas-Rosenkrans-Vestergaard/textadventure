package textadventure.items.chest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.Character;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.Item;
import textadventure.items.backpack.Backpack;
import textadventure.ui.BaseSelect;
import textadventure.ui.GameInterface;
import textadventure.ui.Option;
import textadventure.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link textadventure.actions.Action} allowing a {@link textadventure.Character} to deposit {@link Item}s from their
 * {@link textadventure.items.Inventory} to a {@link Chest}.
 */
public class DepositItemsIntoChestAction extends ChestAction
{

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
	public DepositItemsIntoChestAction(Chest chest, ActionPerformCallback<DepositItemsIntoChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link DepositItemsIntoChestAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link DepositItemsIntoChestAction}.
	 * @param arguments     The arguments provided to the {@link DepositItemsIntoChestAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Chest.State state    = chest.getState();
		Backpack    backpack = character.getBackpack();

		if (state == Chest.State.CLOSED) {
			setException(new ChestClosedException(chest));
			callback.send(character, this);
			return;
		}

		ImmutableSet<Option<Item>> options = backpack.asOptions(Item.class);
		Select<Item> select = new BaseSelect<>(options, selection -> {

			try {
				for (Option option : selection) {
					Item currentItem = backpack.getItem(option.getOptionIndex());
					this.items.add(currentItem);
					chest.addItem(currentItem);
					backpack.takeItem(option.getOptionIndex());
				}

			} catch (Exception e) {
				setException(e);
			}
		});

		try {

			if (arguments.length == 1) {
				List<Integer> indices = new ArrayList<>();
				for (String argument : arguments) indices.add(Integer.parseInt(argument));
				select.selectIndices(indices);
				return;
			}

			gameInterface.select(character, select);

		} catch (Exception e) {
			setException(e);
		} finally {
			callback.send(character, this);
		}
	}

	/**
	 * Returns the {@link Item}(s) that were correctly deposited.
	 *
	 * @return The {@link Item}(s) that were correctly deposited.
	 */
	public ImmutableList<Item> getItems()
	{
		return ImmutableList.copyOf(items);
	}
}
