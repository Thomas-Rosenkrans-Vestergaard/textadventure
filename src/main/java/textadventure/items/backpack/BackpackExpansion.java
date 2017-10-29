package textadventure.items.backpack;

import textadventure.items.Item;

/**
 * An {@link Item} allowing {@link textadventure.Character}s to expand their {@link Backpack}. The {@link Backpack}
 * is expanded using the {@link ExpandBackpackAction}.
 */
public class BackpackExpansion implements Item
{

	/**
	 * The amount of positions added by the {@link BackpackExpansion}.
	 */
	private int upgrade;

	/**
	 * Creates a new {@link BackpackExpansion}.
	 *
	 * @param upgrade The amount of positions added by the {@link BackpackExpansion}.
	 */
	public BackpackExpansion(int upgrade)
	{
		if (upgrade < 1)
			throw new IllegalArgumentException("Upgrade amount must be positive.");

		this.upgrade = upgrade;
	}

	/**
	 * Returns the amount of positions added by the {@link BackpackExpansion}.
	 *
	 * @return The amount of positions added by the {@link BackpackExpansion}.
	 */
	public int getUpgrade()
	{
		return upgrade;
	}

	/**
	 * Returns the name of the {@link BackpackExpansion}.
	 *
	 * @return The name of the {@link BackpackExpansion}.
	 */
	@Override public String getItemTypeName()
	{
		return "backpack-expansion:" + upgrade;
	}

	/**
	 * Returns the description of the {@link BackpackExpansion}.
	 *
	 * @return The description of the {@link BackpackExpansion}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Expands a backpack with " + upgrade + " positions.";
	}
}
