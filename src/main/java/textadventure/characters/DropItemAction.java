package textadventure.characters;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.Action;
import textadventure.actions.ActionResponses;
import textadventure.items.Item;
import textadventure.items.backpack.Backpack;
import textadventure.rooms.Floor;
import textadventure.ui.BaseSelect;
import textadventure.ui.Option;
import textadventure.ui.Select;
import textadventure.ui.SelectResponseException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to drop {@link textadventure.items.Item}s
 * from their {@link Backpack}. The {@link Item}s are then added to the {@link Floor} in current
 * {@link textadventure.rooms.Room}.
 */
public class DropItemAction extends AbstractAction
{

	/**
	 * The {@link Item}s that were correctly dropped.
	 */
	private List<Item> items = new ArrayList<>();

	/**
	 * Performs the {@link DropItemAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link DropItemAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link DropItemAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		Floor    floor    = character.getCurrentLocation().getRoomFloor();
		Backpack backpack = character.getBackpack();

		try {

			ImmutableSet<Option<Item>> options = backpack.asOptions();
			Select<Item> select = new BaseSelect<>(options, selection -> {
				for (Option option : selection) {
					int  position = option.getOptionIndex();
					Item item     = backpack.getItem(position);
					floor.addItem(item);
					backpack.takeItem(position);
					this.items.add(item);
				}
			});

			responses.select(select);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onDropItemAction(character, this);
		}
	}

	/**
	 * Returns the {@link Item}s that were dropped.
	 *
	 * @return The {@link Item}s that were dropped.
	 */
	public ImmutableList<Item> getItems()
	{
		return ImmutableList.copyOf(items);
	}
}
