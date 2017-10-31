package textadventure.items.wearables;

import textadventure.items.EquipableItem;
import textadventure.items.Item;

import java.awt.*;

/**
 * Represents an item the {@link Character} can wear.
 */
public interface Wearable extends Item, EquipableItem
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
