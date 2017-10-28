package textadventure.items.backpack;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.Character;
import textadventure.Game;
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
public class PickupItemAction extends BackpackAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	private ActionPerformCallback<PickupItemAction> callback;

	/**
	 * The {@link Item}s that were picked up.
	 */
	private ImmutableList.Builder<Item> items = new ImmutableList.Builder<>();

	/**
	 * Creates a new {@link PickupItemAction}.
	 *
	 * @param backpack The {@link Backpack} to drop {@link textadventure.items.Item}s from.
	 */
	public PickupItemAction(Backpack backpack, ActionPerformCallback<PickupItemAction> callback)
	{
		super(backpack);

		this.callback = callback;
	}

	/**
	 * Performs the {@link PickupItemAction} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link PickupItemAction}.
	 * @param arguments The arguments provided to the {@link PickupItemAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		GameInterface              gameInterface = game.getGameInterface();
		Floor                      floor         = character.getCurrentLocation().getFloor();
		Backpack                   backpack      = getBackpack();
		ImmutableSet<Option<Item>> options       = floor.asOptions(Item.class);

		Select<Item> select = new BaseSelect<>(options, selection -> {
			try {

				for (Option option : selection) {
					Item item = floor.takeItem(option.getOptionIndex());
					backpack.addItem(item);
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

			gameInterface.select(game, character, select);
		} catch (Exception e) {
			setException(e);
		} finally {
			callback.send(game, character, this);
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
