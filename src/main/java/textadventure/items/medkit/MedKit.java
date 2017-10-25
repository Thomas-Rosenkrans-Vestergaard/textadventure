package textadventure.items.medkit;

import textadventure.items.BaseInventory;
import textadventure.items.Item;

public class MedKit extends BaseInventory implements Item
{

	/**
	 * Creates a new {@link MedKit}.
	 *
	 * @param slots The number of slots in the {@link MedKit}.
	 */
	public MedKit(int slots)
	{
		super(slots);
	}

	/**
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	@Override public String getItemName()
	{
		return "med_kit";
	}

	/**
	 * Returns a description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	@Override public String getItemDescription()
	{
		return "A small box containing medical supplies.";
	}

	/**
	 * Returns an integer representing the type of the {@link Item}.
	 *
	 * @return The integer representing the type of the {@link Item}.
	 */
	@Override public int getType()
	{
		return getClass().hashCode();
	}
}
