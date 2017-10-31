package textadventure.items.wearables;

import java.awt.*;

public class KelvarGloves extends AbstractWearable implements Gloves
{
	/**
	 * Creates a new {@link WornDownWorkBoots}.
	 *
	 * protectiveFactor Double representing the protective factor of the {@link WornDownWorkBoots}.
	 * color            The {@link Color} of the {@link WornDownWorkBoots}.
	 */
	public KelvarGloves()
	{
		super(4);
	}

	/**
	 * Returns the name of the {@link KelvarGloves}.
	 *
	 * @return The name of the {@link KelvarGloves}.
	 */
	@Override public String getItemTypeName()
	{
		return "Black hawk";
	}

	/**
	 * Returns the description of the {@link KelvarGloves}.
	 *
	 * @return The description of the {@link KelvarGloves}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "A graet pair of gloves.";
	}
}
