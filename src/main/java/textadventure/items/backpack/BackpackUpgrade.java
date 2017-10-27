package textadventure.items.backpack;

import textadventure.items.Item;

public class BackpackUpgrade implements Item
{

	/**
	 * The amount of slots added by the {@link BackpackUpgrade}.
	 */
	private int upgradeAmount;

	/**
	 * Creates a new {@link BackpackUpgrade}.
	 *
	 * @param upgradeAmount The amount of slots added by the {@link BackpackUpgrade}.
	 */
	public BackpackUpgrade(int upgradeAmount)
	{
		if (upgradeAmount < 1) {
			throw new IllegalArgumentException("Upgrade amount must be positive.");
		}

		this.upgradeAmount = upgradeAmount;
	}

	/**
	 * Returns the amount of slots added by the {@link BackpackUpgrade}.
	 *
	 * @return The amount of slots added by the {@link BackpackUpgrade}.
	 */
	public int getUpgradeAmount()
	{
		return upgradeAmount;
	}

	/**
	 * Returns The name of the {@link BackpackUpgrade}.
	 *
	 * @return the name of the {@link BackpackUpgrade}.
	 */
	@Override public String getItemName()
	{
		return "backpack_upgrade";
	}

	/**
	 * Returns The description of the {@link BackpackUpgrade}.
	 *
	 * @return the description of the {@link BackpackUpgrade}.
	 */
	@Override public String getItemDescription()
	{
		return "Adds an amount of slots to the backpack";
	}
}
