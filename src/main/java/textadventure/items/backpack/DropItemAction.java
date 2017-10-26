package textadventure.items.backpack;

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
 * {@link textadventure.actions.Action} that allows a player to discard {@link textadventure.items.Item}(s) from the
 * {@link Backpack}.
 */
public class DropItemAction extends BackpackAction
{

	/**
	 * The possible {@link Outcome}s of the {@link DropItemAction}.
	 */
	public enum Outcome
	{
		SUCCESS,
	}

	/**
	 * The {@link Outcome} of the {@link DropItemAction}.
	 */
	private Outcome outcome;

	/**
	 * {@link ActionPerformCallback} to use as a send after performing the {@link TakeItemFromChestAction}.
	 */
	private ActionPerformCallback<DropItemAction> callback;

	/**
	 * The {@link Item}(s) that were successfully dropped.
	 */
	private Item item;

	/**
	 * Creates a new {@link DropItemAction}.
	 *
	 * @param backpack The {@link Backpack} to discard {@link textadventure.items.Item}s from.
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
		GameInterface userInterface = game.getGameInterface();
		Floor         floor         = player.getCharacter().getCurrentLocation().getFloor();
		Backpack      backpack      = player.getCharacter().getBackpack();

		userInterface.select(game, player, new BaseSelect<>(floor.asOptions(), selection -> {
			try {
				for (Option option : selection) {
					this.item = backpack.takeItem(option.getOptionIdentifier());
					player.getCharacter().getCurrentLocation().getFloor().addItem(this.item);
				}
				this.outcome = Outcome.SUCCESS;
				callback.send(game, player, this);
			} catch (SlotOutOfRangeException | NotEnoughItemsException | InventoryFullException e) {
				throw new IllegalStateException();
			}
		}));
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
	 * Returns the {@link Item} that was dropped.
	 *
	 * @return The {@link Item} that was dropped.
	 */
	public Item getItem()
	{
		return item;
	}
}
