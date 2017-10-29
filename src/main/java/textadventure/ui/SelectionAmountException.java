package textadventure.ui;

/**
 * Exception thrown when the number of selected {@link Option}(s) it outside of the range set by the {@link Select}
 * object.
 */
public class SelectionAmountException extends SelectException
{

	/**
	 * The maximum number of {@link Option}(s) to be selected.
	 */
	private int minimumNumber;

	/**
	 * The maximum number of {@link Option}(s) to be selected.
	 */
	private int maximumNumber;

	/**
	 * The actual number of {@link Option}(s) that were selected.
	 */
	private int actualNumber;

	/**
	 * Creates a new {@link SelectionAmountException}.
	 *
	 * @param select        The {@link Select} where the {@link SelectionAmountException} occurred.
	 * @param minimumNumber The maximum number of {@link Option}(s) to be selected.
	 * @param maximumNumber The maximum number of {@link Option}(s) to be selected.
	 * @param actualNumber  The actual number of {@link Option}(s) that were selected.
	 */
	public SelectionAmountException(Select select, int minimumNumber, int maximumNumber, int actualNumber)
	{
		super(select);
		this.minimumNumber = minimumNumber;
		this.maximumNumber = maximumNumber;
		this.actualNumber = actualNumber;
	}

	/**
	 * Returns the maximum number of {@link Option}(s) to be selected.
	 *
	 * @return The maximum number of {@link Option}(s) to be selected.
	 */
	public int getMinimumNumber()
	{
		return this.minimumNumber;
	}

	/**
	 * Returns the maximum number of {@link Option}(s) to be selected.
	 *
	 * @return The maximum number of {@link Option}(s) to be selected.
	 */
	public int getMaximumNumber()
	{
		return this.maximumNumber;
	}

	/**
	 * Returns the actual number of {@link Option}(s) that were selected.
	 *
	 * @return The actual number of {@link Option}(s) that were selected.
	 */
	public int getActualNumber()
	{
		return this.actualNumber;
	}
}
