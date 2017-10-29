package textadventure.items.wearables;

import java.awt.*;

/**
 * The default {@link HeadWear}.
 */
public class WornDownCap extends AbstractWearable implements HeadWear
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
	@Override public String getItemTypeName()
	{
		return "Worn down cap.";
	}

	/**
	 * Returns the description of the {@link WornDownCap}.
	 *
	 * @return The description of the {@link WornDownCap}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "The default headwear.";
	}
}
