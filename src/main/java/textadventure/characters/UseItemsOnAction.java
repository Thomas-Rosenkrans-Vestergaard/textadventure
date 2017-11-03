package textadventure.characters;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.combat.Faction;
import textadventure.items.EnemyUsableItem;
import textadventure.items.FriendlyUsableItem;
import textadventure.items.UsableItem;
import textadventure.ui.*;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link textadventure.actions.Action} allowing a {@link Character} to use
 * {@link textadventure.items.FriendlyUsableItem} or {@link textadventure.items.EnemyUsableItem} on another
 * {@link Character} in a {@link textadventure.rooms.Room}.
 */
public class UseItemsOnAction extends AbstractAction
{

	/**
	 * The {@link UsableItem} that was used.
	 */
	private UsableItem item;

	/**
	 * The {@link Character}s that the selected {@link textadventure.items.Item} was successfully used on.
	 */
	private List<Character> targets = new ArrayList<>();

	/**
	 * Performs the {@link UseItemsOnAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link UseItemsOnAction}.
	 * @param responses The {@link ActionResponses} to report to after performing the {@link UseItemsOnAction}.
	 */
	@Override public void perform(Character character, ActionResponses responses)
	{
		try {

			ImmutableSet<Option<UsableItem>> itemOptions = character.getBackpack().asOptions(UsableItem.class);
			Select<UsableItem> itemSelect = new BaseSelect<>(itemOptions, 1, itemSelection -> {
				this.item = itemSelection.get(0).getT();
				ImmutableSet<Option<Character>> characterOptions = getOptions(character, item);
				Select<Character> characterSelect = new BaseSelect<>(characterOptions, characterSelection -> {
					for (Option<Character> targetOption : characterSelection) {
						Character target = targetOption.getT();
						item.use(target);
						targets.add(target);
					}
				});

				responses.select(characterSelect);
			});

			responses.select(itemSelect);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onUseItemsOnAction(character, this);
		}
	}

	private ImmutableSet<Option<Character>> getOptions(Character character, UsableItem item)
	{
		int                                     index   = 0;
		Faction                                 faction = character.getFaction();
		ImmutableSet.Builder<Option<Character>> builder = new ImmutableSet.Builder<>();

		if (item instanceof EnemyUsableItem) {
			for (Character x : character.getCurrentLocation().getCharacters())
				if (x.getFaction() != faction)
					builder.add(new BaseOption<>(index++, x.getName(), "", x));
		}

		if (item instanceof FriendlyUsableItem) {
			for (Character x : character.getCurrentLocation().getCharacters())
				if (x.getFaction() == faction)
					builder.add(new BaseOption<>(index++, x.getName(), "", x));
		}

		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the {@link UsableItem} that was used.
	 *
	 * @return The {@link UsableItem} that was used.
	 */
	public UsableItem getItem()
	{
		return this.item;
	}

	/**
	 * Returns the {@link Character}s that the selected {@link textadventure.items.Item} was successfully used on.
	 *
	 * @return The {@link Character}s that the selected {@link textadventure.items.Item} was successfully used on.
	 */
	public List<Character> getTargets()
	{
		return this.targets;
	}
}
