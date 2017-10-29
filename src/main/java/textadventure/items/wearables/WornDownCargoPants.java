package textadventure.items.wearables;

import java.awt.*;

/**
 * The default {@link Pants}.
 */
public class WornDownCargoPants extends AbstractWearable implements Pants
{

	/**
	 * Creates a new {@link WornDownCargoPants}.
	 *
	 * @param protectiveFactor Double representing the protective factor of the {@link WornDownCargoPants}.
	 * @param color            The {@link Color} of the {@link Wearable} item.
	 */
	public WornDownCargoPants(double protectiveFactor, Color color)
	{
		super(protectiveFactor, color);
	}

	/**
	 * Returns the name of the {@link WornDownCargoPants}.
	 *
	 * @return The name of the {@link WornDownCargoPants}.
	 */
	@Override public String getItemTypeName()
	{
		return "Worn down cargo pants.";
	}

	/**
	 * Returns the description of the {@link WornDownCargoPants}.
	 *
	 * @return The description of the {@link WornDownCargoPants}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "The default pants.";
	}
}
