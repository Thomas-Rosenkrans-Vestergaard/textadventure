package textadventure.items.backpack;

import textadventure.items.Item;

/**
 * An {@link Item} allowing {@link Charactser}s to expand their {@link Backpack}. The {@link Backpack} is expanded
 * using the {@link ExpandBackpackAction}.
 */
public class BackpackExpansion implements Item
{

	/**
	 * The amount of slots added by the {@link BackpackExpansion}.
	 */
	private int upgradeAmount;

	/**
	 * Creates a new {@link BackpackExpansion}.
	 *
	 * @param upgradeAmount The amount of slots added by the {@link BackpackExpansion}.
	 */
	public BackpackExpansion(int upgradeAmount)
	{
		if (upgradeAmount < 1) {
			throw new IllegalArgumentException("Upgrade amount must be positive.");
		}

		this.upgradeAmount = upgradeAmount;
	}

	/**
	 * Returns the amount of slots added by the {@link BackpackExpansion}.
	 *
	 * @return The amount of slots added by the {@link BackpackExpansion}.
	 */
	public int getUpgradeAmount()
	{
		return upgradeAmount;
	}

	/**
	 * Returns the name of the {@link BackpackExpansion}.
	 *
	 * @return The name of the {@link BackpackExpansion}.
	 */
	@Override public String getItemName()
	{
		return "backpack-expansion:" + upgradeAmount;
	}

	/**
	 * Returns the description of the {@link BackpackExpansion}.
	 *
	 * @return The description of the {@link BackpackExpansion}.
	 */
	@Override public String getItemDescription()
	{
		return "Expands a backpack with " + upgradeAmount + " slots.";
	}
}
