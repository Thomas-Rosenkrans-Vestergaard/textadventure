package textadventure.items.wearables;

import java.awt.*;

/**
 * The default {@link TorsoWear}.
 */
public class WornDownSweatshirt extends AbstractWearable implements TorsoWear
{

	/**
	 * Creates a new {@link WornDownSweatshirt}.
	 *
	 * @param protectiveFactor Double representing the protective factor of the {@link WornDownSweatshirt}.
	 * @param color            The {@link Color} of the {@link Wearable} item.
	 */
	public WornDownSweatshirt(double protectiveFactor)
	{
		super(protectiveFactor);
	}

	/**
	 * Returns the name of the {@link WornDownSweatshirt}.
	 *
	 * @return The name of the {@link WornDownSweatshirt}.
	 */
	@Override public String getItemTypeName()
	{
		return "Work down sweatshirt.";
	}

	/**
	 * Returns the description of the {@link WornDownSweatshirt}.
	 *
	 * @return The description of the {@link WornDownSweatshirt}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "The default torsowear.";
	}
}
