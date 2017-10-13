package textadventure.openable.doors;

/**
 * Represents some {@link Obstruction} blocking a {@link BlockedDoor}. This {@link Obstruction} must first be cleared
 * before the {@link BlockedDoor} can be used.
 */
public class Obstruction
{

	/**
	 * The description of the {@link Obstruction}.
	 */
	private String description;

	/**
	 * The weight of the {@link Obstruction}.
	 */
	private int weight;

	/**
	 * Creates a new {@link Obstruction}.
	 *
	 * @param description The description of the {@link Obstruction}.
	 * @param weight      The weight of the {@link Obstruction}.
	 */
	public Obstruction(String description, int weight)
	{
		this.description = description;
		this.weight = weight;
	}

	/**
	 * Returns the description of the {@link Obstruction}.
	 *
	 * @return The description of the {@link Obstruction}.
	 */
	public String getDescription()
	{
		return this.description;
	}

	/**
	 * Returns the weight of the {@link Obstruction}.
	 *
	 * @return The weight of the {@link Obstruction}.
	 */
	public int getWeight()
	{
		return this.weight;
	}
}
