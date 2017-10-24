package textadventure.items.chest;

import textadventure.Game;
import textadventure.Player;
import textadventure.items.Item;
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
	 * Creates a new {@link TakeItemFromChestAction}.
	 *
	 * @param chest The {@link Chest} to take {@link Item}s from.
	 */
	public TakeItemFromChestAction(Chest chest)
	{
		super(chest);
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
			userInterface.onChestTake(game, player, this);
			return;
		}

		String message = "Chose the item to take.";

		userInterface.select(message, chest, player, item -> {

			Backpack backpack = player.getCharacter().getBackpack();
			if (backpack.countEmptySlots() == 0) {
				outcome = Outcome.BACKPACK_FULL;
				userInterface.onChestTake(game, player, this);
				return;
			}

			backpack.addItem(item);
			chest.removeItem(item);
			outcome = Outcome.SUCCESS;
			this.item = item;
			userInterface.onChestTake(game, player, this);
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
