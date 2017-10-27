package textadventure.items.wearables;

import java.awt.*;

public class WornDownCargoBoots implements Boots
{

	/**
	 * Return the double representing the protective factor of the {@link Wearable}
	 *
	 * @return the double representing the protective factor of the {@link Wearable}
	 */
	@Override public double getProtectiveFactor()
	{
		return 0;
	}

	/**
	 * Return the color of the {@link Wearable}.
	 *
	 * @return The color of the {@link Wearable}.
	 */
	@Override public Color getColor()
	{
		return null;
	}

	/**
	 * Returns The name of the {@link ItemType}.
	 *
	 * @return the name of the {@link ItemType}.
	 */
	@Override public String getItemName()
	{
		return null;
	}

	/**
	 * Returns The description of the {@link ItemType}.
	 *
	 * @return the description of the {@link ItemType}.
	 */
	@Override public String getItemDescription()
	{
		return null;
	}
}
