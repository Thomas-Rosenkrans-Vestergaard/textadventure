package textadventure.select;

import com.google.common.collect.ImmutableSet;

/**
 * Exception thrown when the selected {@link Option}(s) were not found in the possible {@link Option}(s).
 */
public class UnknownOptionException extends SelectException
{

	/**
	 * The possible {@link Option}(s) that could be selected.
	 */
	private ImmutableSet<? extends Option> options;

	/**
	 * The key of the selected {@link Option}.
	 */
	private Option selection;

	/**
	 * Creates a new {@link UnknownOptionException}.
	 *
	 * @param select    The {@link Select} where the {@link UnknownOptionException}
	 * @param options   The possible {@link Option}(s) that could be selected.
	 * @param selection The selected {@link Option}.
	 */
	public UnknownOptionException(Select select, ImmutableSet<? extends Option> options, Option selection)
	{
		super(select);
		this.options = options;
		this.selection = selection;
	}

	/**
	 * Returns the possible {@link Option}(s) that could be selected.
	 *
	 * @return The possible {@link Option}(s) that could be selected.
	 */
	public ImmutableSet<? extends Option> getOptions()
	{
		return options;
	}

	/**
	 * Returns the selected {@link Option}.
	 *
	 * @return The selected {@link Option}.
	 */
	public Option getSelection()
	{
		return this.selection;
	}
}
