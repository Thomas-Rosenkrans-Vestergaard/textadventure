package textadventure.items.backpack;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.Character;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.Item;
import textadventure.items.chest.TakeItemFromChestAction;
import textadventure.rooms.Floor;
import textadventure.ui.BaseSelect;
import textadventure.ui.GameInterface;
import textadventure.ui.Option;
import textadventure.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to pick up {@link Item}s from the
 * {@link Floor}, placing the {@link Item}s in their {@link Backpack}.
 */
public class PickUpItemAction extends BackpackAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	private ActionPerformCallback<PickUpItemAction> callback;

	/**
	 * The {@link Item}s that were picked up.
	 */
	private ImmutableList.Builder<Item> items = new ImmutableList.Builder<>();

	/**
	 * Creates a new {@link PickUpItemAction}.
	 *
	 * @param backpack The {@link Backpack} to drop {@link textadventure.items.Item}s from.
	 */
	public PickUpItemAction(Backpack backpack, ActionPerformCallback<PickUpItemAction> callback)
	{
		super(backpack);

		this.callback = callback;
	}

	/**
	 * Performs the {@link PickUpItemAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link PickUpItemAction}.
	 * @param arguments     The arguments provided to the {@link PickUpItemAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Floor                      floor    = character.getCurrentLocation().getRoomFloor();
		Backpack                   backpack = getBackpack();
		ImmutableSet<Option<Item>> options  = floor.asOptions(Item.class);

		Select<Item> select = new BaseSelect<>(options, selection -> {
			try {

				for (Option option : selection) {
					Item item = floor.getItem(option.getOptionIndex());
					backpack.addItem(item);
					floor.takeItem(option.getOptionIndex());
					this.items.add(item);
				}

			} catch (Exception e) {
				setException(e);
			}
		});

		try {

			if (arguments.length > 0) {
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
	 * Returns the {@link Item}(s) that were picked up.
	 *
	 * @return The {@link Item}(s) that were picked up.
	 */
	public ImmutableList<Item> getItems()
	{
		return items.build();
	}
}
