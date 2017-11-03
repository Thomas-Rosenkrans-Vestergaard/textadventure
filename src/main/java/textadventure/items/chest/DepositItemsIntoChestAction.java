package textadventure.items.chest;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.items.Item;
import textadventure.items.backpack.Backpack;
import textadventure.ui.BaseSelect;
import textadventure.ui.Option;
import textadventure.ui.Select;
import textadventure.ui.SelectResponseException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link textadventure.actions.Action} allowing a {@link Character} to deposit {@link Item}s from their
 * {@link textadventure.items.Inventory} to a {@link Chest}.
 */
public class DepositItemsIntoChestAction extends ChestAction
{

	/**
	 * The {@link Item}s that were successfully moved.
	 */
	private List<Item> items = new ArrayList<>();

	/**
	 * Creates a new {@link DepositItemsIntoChestAction}.
	 *
	 * @param chest The {@link Chest} to deposit {@link Item}s into.
	 */
	public DepositItemsIntoChestAction(Chest chest)
	{
		super(chest);
	}

	/**
	 * Performs the {@link DepositItemsIntoChestAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link DepositItemsIntoChestAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link DepositItemsIntoChestAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		Chest.State state    = chest.getState();
		Backpack    backpack = character.getBackpack();

		if (state == Chest.State.CLOSED) {
			setException(new ChestClosedException(chest));
			responses.onDepositItemsIntoChestAction(character, this);
			return;
		}

		try {

			ImmutableSet<Option<Item>> options = backpack.asOptions(Item.class);
			Select<Item> select = new BaseSelect<>(options, selection -> {
				for (Option option : selection) {
					Item currentItem = backpack.getItem(option.getOptionIndex());
					this.items.add(currentItem);
					chest.addItem(currentItem);
					backpack.takeItem(option.getOptionIndex());
				}
			});

			responses.select(select);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onDepositItemsIntoChestAction(character, this);
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
