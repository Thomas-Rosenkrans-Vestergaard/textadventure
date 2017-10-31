package textadventure.items.wearables;

import textadventure.Equipable;
import textadventure.items.Item;

import java.awt.*;

public interface Wearable extends Item, Equipable
{


	/**
	 * Return the double representing the protective factor of the {@link Wearable}.
	 *
	 * @return The double representing the protective factor of the {@link Wearable}.
	 */
	double getProtectiveFactor();

	/**
	 * Return the color of the {@link Wearable} item.
	 *
	 * @return The color of the {@link Wearable} item.
	 */
	Color getColor();
}
