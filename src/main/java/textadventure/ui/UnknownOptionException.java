package textadventure.ui;

import com.google.common.collect.ImmutableList;

/**
 * Exception thrown when the selected {@link Option}(s) were not found in the possible {@link Option}(s).
 */
public class UnknownOptionException extends SelectException
{

	/**
	 * The possible {@link Option}(s) that could be selected.
	 */
	private ImmutableList<? extends Option> possibleOptions;

	/**
	 * The key of the selected {@link Option}.
	 */
	private Option selectedOption;

	/**
	 * Creates a new {@link UnknownOptionException}.
	 *
	 * @param select          The {@link Select} where the {@link UnknownOptionException}
	 * @param possibleOptions The possible {@link Option}(s) that could be selected.
	 * @param selectedOption  The selected {@link Option}.
	 */
	public UnknownOptionException(Select select, ImmutableList<? extends Option> possibleOptions, Option selectedOption)
	{
		super(select);
		this.possibleOptions = possibleOptions;
		this.selectedOption = selectedOption;
	}

	/**
	 * Returns the possible {@link Option}(s) that could be selected.
	 *
	 * @return The possible {@link Option}(s) that could be selected.
	 */
	public ImmutableList<? extends Option> getPossibleOptions()
	{
		return possibleOptions;
	}

	/**
	 * Returns the selected {@link Option}.
	 *
	 * @return The selected {@link Option}.
	 */
	public Option getSelectedOption()
	{
		return this.selectedOption;
	}
}
