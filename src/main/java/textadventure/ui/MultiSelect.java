package textadventure.ui;

public interface MultiSelect<O extends Option> extends Select<O>
{

	/**
	 * Returns the minimum amount of {@link Option}s that can be selected.
	 *
	 * @return The minimum amount of {@link Option}s that can be selected.
	 */
	int getMinOptions();

	/**
	 * Returns the maximum amount of {@link Option}s that can be selected.
	 *
	 * @return The maximum amount of {@link Option}s that can be selected.
	 */
	int getMaxOptions();
}
