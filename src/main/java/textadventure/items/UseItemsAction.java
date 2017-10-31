package textadventure.items;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.ui.BaseSelect;
import textadventure.ui.Option;
import textadventure.ui.Select;
import textadventure.ui.SelectResponseException;

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
	private ArrayList<UsableItem> items;

	/**
	 * Resets the {@link EquipAction} to its default state.
	 */
	@Override public void reset()
	{
		this.exception = null;
		this.items = new ArrayList<>();
	}

	/**
	 * Performs the {@link UseItemsAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link UseItemsAction}.
	 * @param arguments The arguments provided to the {@link UseItemsAction}.
	 * @param responses The {@link ActionResponses} to report to after performing the {@link UseItemsAction}.
	 */
	@Override public void perform(Character character, String[] arguments, ActionResponses responses)
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

			if (arguments.length > 0) {
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
			responses.onUseItemAction(character, this);
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
