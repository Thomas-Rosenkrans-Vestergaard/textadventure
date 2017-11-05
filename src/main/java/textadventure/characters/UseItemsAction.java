package textadventure.characters;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.items.UsableItem;
import textadventure.select.BaseSelect;
import textadventure.select.Option;
import textadventure.select.Select;
import textadventure.select.SelectResponseException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link textadventure.actions.Action} allowing a {@link textadventure.characters.Character} to use an
 * {@link UsableItem}.
 */
public class UseItemsAction extends AbstractAction
{

	/**
	 * The {@link UsableItem}s used during the {@link UseItemsAction}.
	 */
	private List<UsableItem> items = new ArrayList<>();

	/**
	 * Performs the {@link UseItemsAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link UseItemsAction}.
	 * @param responses The {@link ActionResponses} to report to after performing the {@link UseItemsAction}.
	 */
	@Override public void perform(Character character, ActionResponses responses)
	{
		try {

			ImmutableSet<Option<UsableItem>> options = character.getBackpack().asOptions(UsableItem.class);
			Select<UsableItem> select = new BaseSelect<>(options, selection -> {
				for (Option<UsableItem> option : selection) {
					UsableItem item = option.getT();
					item.use(character);
					this.items.add(item);
				}
			});

			responses.select(select);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onUseItemsAction(character, this);
		}
	}

	/**
	 * Returns the {@link UsableItem}s used by the {@link UseItemsAction}.
	 *
	 * @return The {@link UsableItem}s used by the {@link UseItemsAction}.
	 */
	public ImmutableList<UsableItem> getItems()
	{
		return ImmutableList.copyOf(items);
	}
}
