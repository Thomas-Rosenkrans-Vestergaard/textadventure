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
	@Override public int getIdentifier()
	{
		return Objects.hash(getClass(), code);
	}

	/**
	 * Returns The name of the {@link ItemType}.
	 *
	 * @return the name of the {@link ItemType}.
	 */
	@Override public String getItemName()
	{
		return null;
	}

	/**
	 * Returns The description of the {@link ItemType}.
	 *
	 * @return the description of the {@link ItemType}.
	 */
	@Override public String getItemDescription()
	{
		return null;
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
