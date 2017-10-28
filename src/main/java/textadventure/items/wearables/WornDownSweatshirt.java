package textadventure.items.wearables;

import java.awt.*;

/**
 * The default {@link Torsowear}.
 */
public class WornDownSweatshirt extends AbstractWearable implements Torsowear
{

	/**
	 * Creates a new {@link WornDownSweatshirt}.
	 *
	 * @param protectiveFactor Double representing the protective factor of the {@link WornDownSweatshirt}.
	 * @param color            The {@link Color} of the {@link Wearable} item.
	 */
	public WornDownSweatshirt(double protectiveFactor, Color color)
	{
		super(protectiveFactor, color);
	}

	/**
	 * Returns the name of the {@link WornDownSweatshirt}.
	 *
	 * @return The name of the {@link WornDownSweatshirt}.
	 */
	@Override public String getItemName()
	{
		return "Work down sweatshirt.";
	}

	/**
	 * Returns the description of the {@link WornDownSweatshirt}.
	 *
	 * @return The description of the {@link WornDownSweatshirt}.
	 */
	@Override public String getItemDescription()
	{
		return "The default torsowear.";
	}
}
