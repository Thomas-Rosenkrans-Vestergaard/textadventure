package textadventure.items.wearables;

import java.awt.*;

public class KelvarHelmet extends AbstractWearable implements HeadWear
{
	/**
	 * Creates a new {@link WornDownWorkBoots}.
	 *
	 * protectiveFactor Double representing the protective factor of the {@link WornDownWorkBoots}.
	 * color            The {@link Color} of the {@link WornDownWorkBoots}.
	 */
	public KelvarHelmet()
	{
		super(9);
	}

	/**
	 * Returns the name of the {@link KelvarHelmet}.
	 *
	 * @return The name of the {@link KelvarHelmet}.
	 */
	@Override public String getItemTypeName()
	{
		return "Standart army helmet.";
	}

	/**
	 * Returns the description of the {@link KelvarHelmet}.
	 *
	 * @return The description of the {@link KelvarHelmet}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "great agains exploisen damage and it even in green!!";
	}


}
