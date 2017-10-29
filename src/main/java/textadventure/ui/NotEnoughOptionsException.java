package textadventure.ui;

/**
 * Thrown when there are not enough {@link Option}s to {@link Select} the required number of
 * <code>minimumOptions</code>.
 */
public class NotEnoughOptionsException extends SelectException
{

	/**
	 * The amount of {@link Option}s required by the {@link Select}.
	 */
	private int minimum;

	/**
	 * The actual number of {@link Option}s in the {@link Select}.
	 */
	private int actual;

	/**
	 * Creates a new {@link NotEnoughOptionsException}.
	 *
	 * @param select  The {@link Select} object where the {@link NotEnoughOptionsException} occurred.
	 * @param minimum The amount of {@link Option}s required by the {@link Select}.
	 * @param actual  The actual number of {@link Option}s in the {@link Select}.
	 */
	public NotEnoughOptionsException(Select select, int minimum, int actual)
	{
		super(select);
		this.minimum = minimum;
		this.actual = actual;
	}

	/**
	 * Returns the amount of {@link Option}s required by the {@link Select}.
	 *
	 * @return The amount of {@link Option}s required by the {@link Select}.
	 */
	public int getMinimum()
	{
		return this.minimum;
	}

	/**
	 * Returns the actual number of {@link Option}s in the {@link Select}.
	 *
	 * @return The actual number of {@link Option}s in the {@link Select}.
	 */
	public int getActual()
	{
		return this.actual;
	}
}
