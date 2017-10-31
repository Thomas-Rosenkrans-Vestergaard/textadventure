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
 * {@link textadventure.actions.Action} that allows a {@link Character} to take {@link Item}s from the
 * {@link Chest}, depositing the {@link Item}s into the {@link Character}'s {@link Backpack}.
 */
public class TakeItemFromChestAction extends ChestAction
{

	/**
	 * The {@link Item}s being taken from the {@link Chest}.
	 */
	private List<Item> items = new ArrayList<>();

	/**
	 * Creates a new {@link OpenChestAction}.
	 *
	 * @param chest The {@link Chest} where the {@link Item} is taken from.
	 */
	public TakeItemFromChestAction(Chest chest)
	{
		super(chest);
	}

	/**
	 * Resets the {@link TakeItemFromChestAction} to its default state.
	 */
	@Override public void reset()
	{
		this.exception = null;
		this.chest = null;
		this.items = null;
	}

	/**
	 * Performs the {@link TakeItemFromChestAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link TakeItemFromChestAction}.
	 * @param arguments The arguments provided to the {@link TakeItemFromChestAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		Chest.State state    = chest.getState();
		Backpack    backpack = character.getBackpack();

		if (state == Chest.State.CLOSED) {
			setException(new ChestClosedException(chest));
			responses.onTakeItemFromChestAction(character, this);
			return;
		}

		try {

			ImmutableSet<Option<Item>> options = chest.asOptions(Item.class);
			Select<Item> select = new BaseSelect<>(options, selection -> {
				for (Option option : selection) {
					Item currentItem = chest.getItem(option.getOptionIndex());
					backpack.addItem(currentItem);
					this.items.add(currentItem);
					chest.takeItem(option.getOptionIndex());
				}
			});

			if (arguments.length == 1) {
				List<Integer> indices = new ArrayList<>();
				for (String argument : arguments) indices.add(Integer.parseInt(argument));
				select.selectIndices(indices);
				return;
			}

			character.getFaction().getLeader().select(select);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onTakeItemFromChestAction(character, this);
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
