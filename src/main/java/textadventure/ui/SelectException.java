package textadventure.ui;

import textadventure.GameException;

/**
 * Base exception for errors related to {@link Select}s.
 */
public class SelectException extends GameException
{

	/**
	 * The {@link Select} object where the {@link SelectException} occurred.
	 */
	private Select select;

	/**
	 * Creates a new {@link SelectException}.
	 *
	 * @param select The {@link Select} object where the {@link SelectException} occurred.
	 */
	public SelectException(Select select)
	{
		this.select = select;
	}

	/**
	 * Returns the {@link Select} object where the {@link SelectException} occurred.
	 *
	 * @return The {@link Select} object where the {@link SelectException} occurred.
	 */
	public Select getSelect()
	{
		return this.select;
	}
}
