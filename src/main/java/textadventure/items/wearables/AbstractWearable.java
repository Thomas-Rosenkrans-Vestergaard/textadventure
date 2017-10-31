package textadventure.items.wearables;

import java.awt.*;
import java.util.Random;

/**
 * An abstract base of the {@link Wearable} interface.
 */
public abstract class AbstractWearable implements Wearable
{

	/**
	 * Double representing the protective factor of the {@link Wearable}.
	 */
	private double protectiveFactor;

	/**
	 * The {@link Color} of the {@link Wearable} item.
	 */
	private Color color;

	/**
	 * Creates a new {@link AbstractWearable}.
	 *
	 * @param protectiveFactor Double representing the protective factor of the {@link Wearable}.
	 */
	public AbstractWearable(double protectiveFactor)
	{
		this.protectiveFactor = protectiveFactor;

		Random rn = new Random();

		int r = rn.nextInt(256);
		int g = rn.nextInt(256);
		int b = rn.nextInt(256);

		this.color = new Color(r,g,b);
	}

	/**
	 * Return the double representing the protective factor of the {@link Wearable}.
	 *
	 * @return The double representing the protective factor of the {@link Wearable}.
	 */
	@Override public double getProtectiveFactor()
	{
		return protectiveFactor;
	}

	/**
	 * Return the color of the {@link Wearable} item.
	 *
	 * @return The color of the {@link Wearable} item.
	 */
	@Override public Color getColor()
	{
		return color;
	}
}
