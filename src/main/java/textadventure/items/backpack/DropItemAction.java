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
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link DropItemAction}.
	 * @param arguments The arguments provided to the {@link DropItemAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		GameInterface gameInterface = game.getGameInterface();
		Floor         floor         = character.getCurrentLocation().getFloor();
		Backpack      backpack      = character.getBackpack();

		ImmutableSet<Option<Item>> options = backpack.asOptions();
		Select<Item> select = new BaseSelect<>(options, selection -> {
			try {

				for (Option option : selection) {
					Item item = backpack.takeItem(option.getOptionIndex());
					floor.addItem(item);
					this.items.add(item);
				}

			} catch (Exception e) {
				setException(e);
			}

			callback.send(game, character, this);
		});

		try {
			if (arguments.length > 0) {
				List<Integer> indices = new ArrayList<>();
				for (String argument : arguments) indices.add(Integer.parseInt(argument));
				select.selectIndices(indices);
				callback.send(game, character, this);
				return;
			}

			gameInterface.select(game, character, select);
			callback.send(game, character, this);
		} catch (Exception e) {
			setException(e);
			callback.send(game, character, this);
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
