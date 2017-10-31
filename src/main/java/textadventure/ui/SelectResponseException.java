package textadventure.ui;

/**
 * Wraps an exception thrown from the {@link textadventure.ui.Select.SelectResponse} interface.
 */
public class SelectResponseException extends SelectException
{

	/**
	 * The {@link Exception} that caused the {@link SelectResponseException}.
	 */
	private Exception cause;

	/**
	 * Creates a new {@link SelectResponseException}.
	 *
	 * @param select The {@link Select} object where the {@link SelectException} occurred.
	 * @param cause  The {@link Exception} that caused the {@link SelectResponseException}.
	 */
	public SelectResponseException(Select select, Exception cause)
	{
		super(select);
		this.cause = cause;
	}

	/**
	 * Returns the {@link Exception} that caused the {@link SelectResponseException}.
	 *
	 * @return The {@link Exception} that caused the {@link SelectResponseException}.
	 */
	@Override public Exception getCause()
	{
		return this.cause;
	}
}
