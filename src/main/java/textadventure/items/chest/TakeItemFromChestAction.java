package textadventure.items.chest;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.InventoryFullException;
import textadventure.items.Item;
import textadventure.items.NotEnoughItemsException;
import textadventure.items.UnknownItemSlotException;
import textadventure.items.backpack.Backpack;
import textadventure.ui.GameInterface;

/**
 * {@link textadventure.actions.Action} that allows a player to take item(s) from the {@link Chest}.
 */
public class TakeItemFromChestAction extends ChestAction
{
	/**
	 * The possible {@link Outcome}s of the {@link TakeItemFromChestAction}.
	 */
	public enum Outcome
	{

		/**
		 * The {@link Item} was successfully taken.
		 */
		SUCCESS,

		/**
		 * The {@link textadventure.Character}s {@link Backpack} was full.
		 */
		BACKPACK_FULL,

		/**
		 * The {@link Chest} is not {@link Chest.State#OPEN}.
		 */
		CLOSED,
	}

	/**
	 * The {@link Outcome} of the {@link TakeItemFromChestAction}.
	 */
	private Outcome outcome;

	/**
	 * The {@link Item} being taken from the {@link Chest}.
	 */
	private Item item;

	/**
	 * {@link ActionPerformCallback} to use as a send after performing the {@link TakeItemFromChestAction}.
	 */
	private ActionPerformCallback<TakeItemFromChestAction> callback;

	/**
	 * Creates a new {@link OpenChestAction}.
	 *
	 * @param chest    The {@link Chest} where the {@link Item} is taken from.
	 * @param callback The {@link ActionPerformCallback} to use as a send after performing the {@link TakeItemFromChestAction}.
	 */
	TakeItemFromChestAction(Chest chest, ActionPerformCallback<TakeItemFromChestAction> callback)
	{
		super(chest);

		this.callback = callback;
	}

	/**
	 * Performs the {@link TakeItemFromChestAction} using the provided parameters.
	 *
	 * @param game      The {@link Game} instance.
	 * @param player    The {@link Player} performing the {@link TakeItemFromChestAction}.
	 * @param arguments The arguments provided to the {@link TakeItemFromChestAction}.
	 */
	@Override public void perform(Game game, Player player, String[] arguments)
	{
		Chest.State   state         = chest.getState();
		GameInterface userInterface = game.getGameInterface();

		if (state == Chest.State.CLOSED) {
			outcome = Outcome.CLOSED;
			callback.send(game, player, this);
			return;
		}

		userInterface.select(chest, player, choice -> {

			try {
				Backpack backpack = player.getCharacter().getBackpack();
				this.item = chest.takeItem(choice);
				backpack.addItem(this.item);
				outcome = Outcome.SUCCESS;
				callback.send(game, player, this);
			} catch (UnknownItemSlotException | NotEnoughItemsException e) {
				throw new IllegalStateException();
			} catch (InventoryFullException e) {
				outcome = Outcome.BACKPACK_FULL;
				callback.send(game, player, this);
			}
		});
	}

	/**
	 * Returns the {@link Outcome} of the {@link TakeItemFromChestAction}.
	 *
	 * @return The {@link Outcome} of the {@link TakeItemFromChestAction}.
	 */
	public Outcome getOutcome()
	{
		return this.outcome;
	}

	/**
	 * Returns the {@link Item} that was taken.
	 *
	 * @return The {@link Item} that was taken.
	 */
	public Item getItem()
	{
		return this.item;
	}
}
