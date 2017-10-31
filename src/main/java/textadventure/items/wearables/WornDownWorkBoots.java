package textadventure.items.wearables;

import java.awt.*;

/**
 * The default {@link Boots}.
 */
public class WornDownWorkBoots extends AbstractWearable implements Boots
{

	/**
	 * Creates a new {@link WornDownWorkBoots}.
	 *
	 * @param protectiveFactor Double representing the protective factor of the {@link WornDownWorkBoots}.
	 * @param color            The {@link Color} of the {@link WornDownWorkBoots}.
	 */
	public WornDownWorkBoots(double protectiveFactor)
	{
		super(protectiveFactor);
	}

	/**
	 * Returns the name of the {@link WornDownWorkBoots}.
	 *
	 * @return The name of the {@link WornDownWorkBoots}.
	 */
	@Override public String getItemTypeName()
	{
		return "Worn down work boots.";
	}

	/**
	 * Returns the description of the {@link WornDownWorkBoots}.
	 *
	 * @return The description of the {@link WornDownWorkBoots}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "The default boots.";
	}
}
