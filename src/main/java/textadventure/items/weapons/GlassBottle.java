package textadventure.items.weapons;

import textadventure.characters.Character;
import textadventure.items.Item;

public class GlassBottle extends AbstractBluntWeapon implements BluntWeapon, Item
{
	/**
	 * Creates a new {@link GlassBottle}.
	 */
	public GlassBottle()
	{
		super(7);
	}

	/**
	 * Returns the name of the {@link GlassBottle}.
	 *
	 * @return The name of the {@link GlassBottle}.
	 */
	@Override public String getItemTypeName()
	{
		return "Glass bottle";
	}

	/**
	 * Returns the description of the {@link GlassBottle}.
	 *
	 * @return The description of the {@link GlassBottle}.
	 */
	@Override public String getItemTypeDescription()
	{
		return "Very fragile, but lies around everywhere.";
	}

}
