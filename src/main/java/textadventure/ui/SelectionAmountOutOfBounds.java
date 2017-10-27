package textadventure.ui;

import com.google.common.collect.ImmutableSet;

/**
 * Exception thrown when the number of selected {@link Option}(s) it outside of the range set by the {@link Select}
 * object.
 */
public class SelectionAmountOutOfBounds extends SelectException
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
	 * The {@link Option}(s) that were selected.
	 */
	private ImmutableSet<?> selection;

	/**
	 * Creates a new {@link SelectionAmountOutOfBounds}.
	 *
	 * @param select        The {@link Select} where the {@link SelectionAmountOutOfBounds} occurred.
	 * @param minimumNumber The maximum number of {@link Option}(s) to be selected.
	 * @param maximumNumber The maximum number of {@link Option}(s) to be selected.
	 * @param actualNumber  The actual number of {@link Option}(s) that were selected.
	 * @param selection     The selected {@link Option}(s).
	 */
	public SelectionAmountOutOfBounds(Select select, int minimumNumber, int maximumNumber, int actualNumber, ImmutableSet<? extends Option> selection)
	{
		super(select);
		this.minimumNumber = minimumNumber;
		this.maximumNumber = maximumNumber;
		this.actualNumber = actualNumber;
		this.selection = selection;
	}

	/**
	 * Returns The maximum number of {@link Option}(s) to be selected.
	 *
	 * @return the maximum number of {@link Option}(s) to be selected.
	 */
	public int getMinimumNumber()
	{
		return this.minimumNumber;
	}

	/**
	 * Returns The maximum number of {@link Option}(s) to be selected.
	 *
	 * @return the maximum number of {@link Option}(s) to be selected.
	 */
	public int getMaximumNumber()
	{
		return this.maximumNumber;
	}

	/**
	 * Returns The actual number of {@link Option}(s) that were selected.
	 *
	 * @return the actual number of {@link Option}(s) that were selected.
	 */
	public int getActualNumber()
	{
		return this.actualNumber;
	}

	/**
	 * Returns The {@link Option}(s) that were selected.
	 *
	 * @return the {@link Option}(s) that were selected.
	 */
	public ImmutableSet<?> getSelection()
	{
		return this.selection;
	}
}
