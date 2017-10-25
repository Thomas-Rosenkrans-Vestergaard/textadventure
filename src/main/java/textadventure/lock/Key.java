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
	 * Returns the code representing the {@link Key}. The {@link Key} can only open {@link Lock}s with matching codes.
	 *
	 * @return The code representing the {@link Key}. The {@link Key} can only open {@link Lock}s with matching codes.
	 */
	public String getCode()
	{
		return this.code;
	}

	/**
	 * Returns the name of the {@link Item}.
	 *
	 * @return The name of the {@link Item}.
	 */
	@Override public String getItemName()
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
	 * Returns the name the {@link textadventure.ui.Option} should be displayed in the {@link textadventure.ui.Select}.
	 *
	 * @return The name the {@link textadventure.ui.Option} should be displayed in the {@link textadventure.ui.Select}.
	 */
	@Override public String getOptionName()
	{
		return code;
	}

	/**
	 * Returns the description of the {@link textadventure.ui.Option}. Informs the user what selecting the
	 * {@link textadventure.ui.Option} will do.
	 *
	 * @return The description of the {@link textadventure.ui.Option}. Informs the user what selecting the
	 * {@link textadventure.ui.Option} will do.
	 */
	@Override public String getOptionDescription()
	{
		return String.format("Can be used to lock or unlock locks with the code %s.", code);
	}

	/**
	 * Returns an integer representing the type of the {@link Item}.
	 *
	 * @return The integer representing the type of the {@link Item}.
	 */
	@Override public int getType()
	{
		int hashCode = 1;
		hashCode = 31 * hashCode + code.hashCode();
		hashCode = 31 * hashCode + getClass().hashCode();

		return hashCode;
	}
}
