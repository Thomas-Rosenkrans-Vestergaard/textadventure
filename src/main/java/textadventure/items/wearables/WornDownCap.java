package textadventure.items.wearables;

import java.awt.*;

/**
 * The default {@link Headwear}.
 */
public class WornDownCap extends AbstractWearable implements Headwear
{

	/**
	 * Creates a new {@link WornDownCap}.
	 *
	 * @param protectiveFactor Double representing the protective factor of the {@link WornDownCap}.
	 * @param color            The {@link Color} of the {@link WornDownCap}.
	 */
	public WornDownCap(double protectiveFactor, Color color)
	{
		super(protectiveFactor, color);
	}

	/**
	 * Returns the name of the {@link WornDownCap}.
	 *
	 * @return The name of the {@link WornDownCap}.
	 */
	@Override public String getItemName()
	{
		return "Worn down cap.";
	}

	/**
	 * Returns the description of the {@link WornDownCap}.
	 *
	 * @return The description of the {@link WornDownCap}.
	 */
	@Override public String getItemDescription()
	{
		return "The default headwear.";
	}
}