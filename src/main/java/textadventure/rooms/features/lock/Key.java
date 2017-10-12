package textadventure.rooms.features.lock;

import textadventure.rooms.features.doors.Door;

/**
 * Represents a {@link Key} needed to open a locked {@link Door}.
 */
public class Key
{

	/**
	 * The code representing the {@link Key}.
	 */
	private final int code;

	/**
	 * The <code>code</code> on the {@link Key}. The {@link Key} can open {@link Lock}s with same corresponding
	 * <code>code</code>.
	 *
	 * @param code The
	 */
	public Key(int code)
	{
		this.code = code;
	}

	/**
	 * Returns the code identifying the {@link Key}.
	 *
	 * @return The code identifying the {@link Key}.
	 */
	public int getCode()
	{
		return this.code;
	}
}
