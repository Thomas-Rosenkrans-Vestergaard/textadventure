package textadventure.select;

/**
 * Thrown when creating a new {@link BaseSelect} without any options.
 */
public class NoOptionsException extends SelectException
{

	/**
	 * Creates a new {@link NotEnoughOptionsException}.
	 *
	 * @param select The {@link Select} where the {@link NotEnoughOptionsException} occurred.
	 */
	public NoOptionsException(Select select)
	{
		super(select);
	}
}
