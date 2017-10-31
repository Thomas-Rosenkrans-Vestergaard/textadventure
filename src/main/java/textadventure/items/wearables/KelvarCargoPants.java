package textadventure.items.wearables;

import java.awt.*;

public class KelvarCargoPants extends AbstractWearable implements Pants
{
	/**
	 * Creates a new {@link WornDownWorkBoots}.
	 *
	 *  protectiveFactor Double representing the protective factor of the {@link WornDownWorkBoots}.
	 *  color            The {@link Color} of the {@link WornDownWorkBoots}.
	 */
	public KelvarCargoPants()
	{
		super(5);
	}

	/**
	 * Returns the name of the {@link KelvarCargoPants}.
	 *
	 * @return The name of the {@link KelvarCargoPants}.
	 */
	@Override public String getItemTypeName()
	{
		return "Kelver pants.";
	}

	/**
	 * Returns the description of the {@link KelvarCargoPants}.
	 *
	 * @return The description of the {@link KelvarCargoPants}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "A great choise for armour.";
	}
}
