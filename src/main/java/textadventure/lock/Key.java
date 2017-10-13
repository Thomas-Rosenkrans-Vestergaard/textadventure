package textadventure.lock;

import textadventure.items.Item;

/**
 * Represents a {@link Key} needed to lock or unlock a {@link Lock}.
 */
public class Key implements Item
{

	/**
	 * The code representing the {@link Key}. The {@link Key} can only open {@link Lock}s with matching codes.
	 */
	private final String code;

	/**
	 * Creates a new {@link Key}.
	 *
	 * @param code The code representing the {@link Key}. The {@link Key} can only open {@link Lock}s with matching
	 *             codes.
	 */
	public Key(String code)
	{
		this.code = code;
	}

	/**
	 * Returns the unique identifier of the {@link Item}.
	 *
	 * @return The unique identifier of the {@link Item}.
	 */
	@Override public int getID()
	{
		return code.hashCode();
	}

	/**
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	@Override public String getOptionName()
	{
		return code;
	}

	/**
	 * Returns a description of the {@link Item}.
	 *
	 * @return The description of the {@link Item}.
	 */
	@Override public String getItemDescription()
	{
		return String.format("On the key is written the code %s.", code);
	}

	/**
	 * Returns the code representing the {@link Key}. The {@link Key} can only open {@link Lock}s with matching codes.
	 *
	 * @return The code representing the {@link Key}. The {@link Key} can only open {@link Lock}s with matching codes.
	 */
	public String getCode()
	{
		return this.code;
	}
}
