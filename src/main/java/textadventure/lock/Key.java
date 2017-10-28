package textadventure.lock;

import textadventure.items.Item;
import textadventure.items.ItemType;

import java.util.Objects;

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
	 * Returns the integer identifier that identifies the {@link ItemType}.
	 *
	 * @return The identifier.
	 */
	@Override public int getItemIdentifier()
	{
		return Objects.hash(getClass(), code);
	}

	/**
	 * Returns the name of the {@link ItemType}.
	 *
	 * @return The name of the {@link ItemType}.
	 */
	@Override public String getItemName()
	{
		return code;
	}

	/**
	 * Returns the description of the {@link ItemType}.
	 *
	 * @return The description of the {@link ItemType}.
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
