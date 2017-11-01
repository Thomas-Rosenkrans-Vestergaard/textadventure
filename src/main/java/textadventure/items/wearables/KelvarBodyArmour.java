package textadventure.items.wearables;

import java.awt.*;

public class KelvarBodyArmour extends AbstractWearable implements TorsoWear
{
	/**
	 * Creates a new {@link WornDownWorkBoots}.
	 * <p>
	 * protectiveFactor Double representing the protective factor of the {@link WornDownWorkBoots}.
	 * color            The {@link Color} of the {@link WornDownWorkBoots}.
	 */
	public KelvarBodyArmour()
	{
		super(15);
	}

	/**
	 * Returns the name of the {@link KelvarBodyArmour}.
	 *
	 * @return The name of the {@link KelvarBodyArmour}.
	 */
	@Override public String getItemTypeName()
	{
		return "Kelvar body armour.";
	}

	/**
	 * Returns the description of the {@link KelvarBodyArmour}.
	 *
	 * @return The description of the {@link KelvarBodyArmour}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "A blue kelvar armour for great protection";
	}
}
