package textadventure.items.chest;

import textadventure.Game;
import textadventure.Player;
import textadventure.actions.NamedAction;
import textadventure.items.Item;
import textadventure.items.backpack.Backpack;
import textadventure.ui.GameInterface;

public class TakeChestItemAction extends ChestAction implements NamedAction
{
	/**
	 * The possible {@link Outcome}s of the {@link TakeChestItemAction}.
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
	 * The {@link Outcome} of the {@link TakeChestItemAction}.
	 */
	private Outcome outcome;

	/**
	 * The {@link Item} being taken from the {@link Chest}.
	 */
	private Item item;

	/**
	 * Creates a new {@link TakeChestItemAction}.
	 *
	 * @param chest The {@link Chest} to take {@link Item}s from.
	 */
	public TakeChestItemAction(Chest chest)
	{
		super(chest);
	}

	/**
	 * Returns the name of the {@link TakeChestItemAction}.
	 *
	 * @return The name of the {@link TakeChestItemAction}.
	 */
	@Override public String getActionName()
	{
		return "take";
	}

	/**
	 * Returns the description of the {@link TakeChestItemAction}.
	 *
	 * @return The description of the {@link TakeChestItemAction}.
	 */
	@Override public String getActionDescription()
	{
		return "Take an item from the chest.";
	}

	/**
	 * Performs the {@link TakeChestItemAction} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link TakeChestItemAction}.
	 */
	@Override public void perform(Game game, Player player)
	{
		Chest.State   state         = chest.getState();
		GameInterface userInterface = game.getUserInterface();

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
	 * Returns the {@link Outcome} of the {@link TakeChestItemAction}.
	 *
	 * @return The {@link Outcome} of the {@link TakeChestItemAction}.
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
