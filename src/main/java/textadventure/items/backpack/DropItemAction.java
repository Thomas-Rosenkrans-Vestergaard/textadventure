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
 * {@link textadventure.actions.Action} that allows a {@link Character} to drop {@link textadventure.items.Item}s
 * from their {@link Backpack}. The {@link Item}s are then added to the {@link Floor} in current
 * {@link textadventure.rooms.Room}.
 */
public class DropItemAction extends BackpackAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	private ActionPerformCallback<DropItemAction> callback;

	/**
	 * The {@link Item}s that were dropped.
	 */
	private ImmutableList.Builder<Item> items = new ImmutableList.Builder<>();

	/**
	 * Creates a new {@link DropItemAction}.
	 *
	 * @param backpack The {@link Backpack} to discard {@link textadventure.items.Item}s from.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the
	 *                 {@link TakeItemFromChestAction}.
	 */
	public DropItemAction(Backpack backpack, ActionPerformCallback<DropItemAction> callback)
	{
		super(backpack);

		this.callback = callback;
	}

	/**
	 * Performs the {@link DropItemAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link DropItemAction}.
	 * @param arguments     The arguments provided to the {@link DropItemAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Floor    floor    = character.getCurrentLocation().getRoomFloor();
		Backpack backpack = character.getBackpack();

		ImmutableSet<Option<Item>> options = backpack.asOptions();
		Select<Item> select = new BaseSelect<>(options, selection -> {
			try {

				for (Option option : selection) {
					Item item = backpack.getItem(option.getOptionIndex());
					floor.addItem(item);
					backpack.takeItem(option.getOptionIndex());
					this.items.add(item);
				}

			} catch (Exception e) {
				System.out.println(e);
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
	 * Returns the {@link Item}s that were dropped.
	 *
	 * @return The {@link Item}s that were dropped.
	 */
	public ImmutableList<Item> getItems()
	{
		return items.build();
	}
}
