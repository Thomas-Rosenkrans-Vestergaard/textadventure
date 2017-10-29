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
 * {@link textadventure.actions.Action} that allows a {@link textadventure.Character} to take {@link Item}s from the
 * {@link Chest}, depositing the {@link Item}s into the {@link textadventure.Character}'s {@link Backpack}.
 */
public class TakeItemFromChestAction extends ChestAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	private ActionPerformCallback<TakeItemFromChestAction> callback;

	/**
	 * The {@link Item}s being taken from the {@link Chest}.
	 */
	private List<Item> items = new ArrayList<>();

	/**
	 * Creates a new {@link OpenChestAction}.
	 *
	 * @param chest    The {@link Chest} where the {@link Item} is taken from.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	public TakeItemFromChestAction(Chest chest, ActionPerformCallback<TakeItemFromChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link TakeItemFromChestAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link TakeItemFromChestAction}.
	 * @param arguments     The arguments provided to the {@link TakeItemFromChestAction}.
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

		ImmutableSet<Option<Item>> options = chest.asOptions(Item.class);
		Select<Item> select = new BaseSelect<>(options, selection -> {

			try {
				Item currentItem;
				for (Option option : selection) {
					currentItem = chest.getItem(option.getOptionIndex());
					backpack.addItem(currentItem);
					this.items.add(currentItem);
					chest.takeItem(option.getOptionIndex());
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
	 * Returns the {@link Item}(s) that were correctly taken.
	 *
	 * @return The {@link Item}(s) that were correctly taken.
	 */
	public ImmutableList<Item> getItems()
	{
		return new ImmutableList.Builder<Item>().addAll(items).build();
	}
}
