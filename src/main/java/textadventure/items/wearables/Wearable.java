package textadventure.items.wearables;

import textadventure.items.Item;

import java.awt.*;

public interface Wearable extends Item
{


	/**
	 * Return the double representing the protective factor of the {@link Wearable}
	 *
	 * @return The double representing the protective factor of the {@link Wearable}
	 */
	double getProtectiveFactor();

	/**
	 * Return the color of the {@link Wearable}.
	 *
	 * @return The color of the {@link Wearable}.
	 */
	Color getColor();

}
