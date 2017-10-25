package textadventure.ui;

public interface MultiSelect<O extends Option> extends Select<O>
{

	/**
	 * Returns the minimum amount of {@link Option}s that can be selected. <code>-1</code> means that the selection has no limit.
	 *
	 * @return The minimum amount of {@link Option}s that can be selected. <code>-1</code> means that the selection has no limit.
	 */
	int getMinOptions();

	/**
	 * Returns the maximum amount of {@link Option}s that can be selected. <code>-1</code> means that the selection has no limit.
	 *
	 * @return The maximum amount of {@link Option}s that can be selected. <code>-1</code> means that the selection has no limit.
	 */
	int getMaxOptions();
}
