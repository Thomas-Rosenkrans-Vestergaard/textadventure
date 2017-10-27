package textadventure.items.backpack;

import com.google.common.collect.ImmutableList;
import textadventure.Character;
import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.InventoryFullException;
import textadventure.items.Item;
import textadventure.items.NotEnoughItemsException;
import textadventure.items.SlotOutOfRangeException;
import textadventure.items.chest.TakeItemFromChestAction;
import textadventure.rooms.Floor;
import textadventure.ui.BaseSelect;
import textadventure.ui.GameInterface;
import textadventure.ui.Option;

/**
 * {@link textadventure.actions.Action} that allows a player to drop {@link textadventure.items.Item}(s) from the
 * {@link Backpack}. The {@link Item}s are then added to the {@link Floor} in the <code>currentLocation</code>.
 */
public class DropItemAction extends BackpackAction
{

	/**
	 * The possible {@link Outcome}s of the {@link DropItemAction}.
	 */
	public enum Outcome
	{
		SUCCESS,
		ARGUMENT_NOT_INT,
	}

	/**
	 * The {@link Outcome} of the {@link DropItemAction}.
	 */
	private Outcome outcome;

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link TakeItemFromChestAction}.
	 */
	private ActionPerformCallback<DropItemAction> callback;

	/**
	 * The {@link Item}(s) that were dropped.
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
	 * Performs the {@link DropItemAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link DropItemAction}.
	 * @param arguments The arguments provided to the {@link DropItemAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		Character     character     = player.getCharacter();
		GameInterface gameInterface = game.getGameInterface();
		Floor         floor         = character.getCurrentLocation().getFloor();
		Backpack      backpack      = character.getBackpack();

		if (arguments.length == 1) {
			withArgument(game, player, floor, backpack, arguments[0]);
			return;
		}

		gameInterface.select(game, player, new BaseSelect<>(backpack.asOptions(), selection -> {
			try {
				for (Option option : selection) {
					Item item = backpack.takeItem(option.getOptionIdentifier());
					floor.addItem(item);
					this.items.add(item);
				}
				this.outcome = Outcome.SUCCESS;
				callback.send(game, player, this);
			} catch (SlotOutOfRangeException | NotEnoughItemsException | InventoryFullException e) {
				throw new IllegalStateException();
			}
		}));

	}

	/**
	 *Performs the {@link DropItemAction} using the argument it was given.
	 *
	 * @param game The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link DropItemAction}.
	 * @param floor The {@link Floor} the items get dropped to.
	 * @param backpack The {@link Backpack} the items get taken from.
	 * @param argument The arguments provided to the {@link DropItemAction}.
	 */
	private void withArgument(Game game, Player player, Floor floor, Backpack backpack, String argument)
	{
		try {
			Item item = backpack.takeItem(Integer.parseInt(argument));
			floor.addItem(item);
			this.items.add(item);
			this.outcome = Outcome.SUCCESS;
			callback.send(game, player, this);
		} catch (NumberFormatException | NotEnoughItemsException | InventoryFullException | SlotOutOfRangeException e) {
			outcome = Outcome.ARGUMENT_NOT_INT;
			callback.send(game, player, this);
		}
	}

	/**
	 * Returns the {@link Outcome} of the {@link DropItemAction}.
	 *
	 * @return The {@link Outcome} of the {@link DropItemAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}

	/**
	 * Returns the {@link Item}(s) that were dropped.
	 *
	 * @return The {@link Item}(s) that were dropped.
	 */
	public ImmutableList<Item> getItems()
	{
		return items.build();
	}
}
