package textadventure.items.Wearables;

import textadventure.items.Item;

public interface Wearable extends Item
{


	/**
	 * Return the double representing the protective factor of the {@link Wearable}
	 *
	 * @return the double representing the protective factor of the {@link Wearable}
	 */
	double getProtectiveFactor();
}
