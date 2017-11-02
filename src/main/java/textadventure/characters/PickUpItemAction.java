package textadventure.characters;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.items.Item;
import textadventure.items.backpack.Backpack;
import textadventure.rooms.Floor;
import textadventure.ui.BaseSelect;
import textadventure.ui.Option;
import textadventure.ui.Select;
import textadventure.ui.SelectResponseException;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to pick up {@link Item}s from the
 * {@link Floor}, placing the {@link Item}s in their {@link Backpack}.
 */
public class PickUpItemAction extends AbstractAction
{

	/**
	 * The {@link Item}s that were dropped.
	 */
	private ImmutableList.Builder<Item> items = new ImmutableList.Builder<>();

	/**
	 * Performs the {@link PickUpItemAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link PickUpItemAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link PickUpItemAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		Floor                      floor    = character.getCurrentLocation().getRoomFloor();
		Backpack                   backpack = character.getBackpack();
		ImmutableSet<Option<Item>> options  = floor.asOptions(Item.class);

		try {

			Select<Item> select = new BaseSelect<>(options, selection -> {
				for (Option option : selection) {
					Item item = floor.getItem(option.getOptionIndex());
					backpack.addItem(item);
					floor.takeItem(option.getOptionIndex());
					this.items.add(item);
				}
			});

			character.getFaction().getLeader().select(select);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onPickUpItemAction(character, this);
		}
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
