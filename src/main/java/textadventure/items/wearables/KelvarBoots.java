package textadventure.items.wearables;

public class KelvarBoots extends AbstractWearable implements Boots
{
	/**
	 * Creates a new {@link WornDownWorkBoots}.
	 *
	 *  protectiveFactor Double representing the protective factor of the {@link WornDownWorkBoots}.
	 */
	public KelvarBoots()
	{
		super(5);
	}

	/**
	 * Returns the name of the {@link KelvarBoots}.
	 *
	 * @return The name of the {@link KelvarBoots}.
	 */
	@Override public String getItemTypeName()
	{
		return "Kelvar Boots.";
	}

	/**
	 * Returns the description of the {@link KelvarBoots}.
	 *
	 * @return The description of the {@link KelvarBoots}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "True army green boots.";
	}
}
