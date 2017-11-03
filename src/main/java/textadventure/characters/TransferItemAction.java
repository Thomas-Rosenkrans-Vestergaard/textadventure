package textadventure.characters;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.combat.Faction;
import textadventure.items.Item;
import textadventure.items.backpack.Backpack;
import textadventure.rooms.Room;
import textadventure.ui.*;

import java.util.ArrayList;
import java.util.List;

public class TransferItemAction extends AbstractAction
{
	private List<Item> items = new ArrayList<>();

	/**
	 * Performs the {@link TransferItemAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link TransferItemAction}.
	 * @param responses The {@link ActionResponses} to report to after performing the {@link TransferItemAction}.
	 */
	@Override public void perform(Character character, ActionResponses responses)
	{

		Backpack                        backpack         = character.getBackpack();
		Faction                         faction          = character.getFaction();
		Room                            currentLocation  = character.getCurrentLocation();
		ImmutableSet<Option<Character>> characterOptions = getOptions(currentLocation, faction);

		try {

			Select<Character> characterSelect = new BaseSelect<>(characterOptions, 1, characterSelection -> {
				ImmutableSet<Option<Item>> options = backpack.asOptions();
				Select<Item> itemSelect = new BaseSelect<>(options, selection -> {
					Backpack target = characterSelection.get(0).getT().getBackpack();
						for (Option option : selection) {
							int  position = option.getOptionIndex();
							Item item     = backpack.getItem(position);
							target.addItem(item);
							backpack.takeItem(position);
							this.items.add(item);
						}
				});
				responses.select(itemSelect);
			});
			responses.select(characterSelect);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onTransferItemAction(character, this);
		}
	}

	private ImmutableSet<Option<Character>> getOptions(Room room, Faction faction)
	{
		int                                     optionIndex = 0;
		ImmutableSet.Builder<Option<Character>> builder     = new ImmutableSet.Builder<>();
		for (Character character : room.getCharacters())
			if (character.getFaction() == faction)
				builder.add(new BaseOption<>(optionIndex++, character.getName(), "", character));

		return builder.build();
	}

	/**
	 * Returns the {@link Item}s that were transfere.
	 *
	 * @return The {@link Item}s that were dropped.
	 */
	public ImmutableList<Item> getItems()
	{
		return ImmutableList.copyOf(items);
	}

}
