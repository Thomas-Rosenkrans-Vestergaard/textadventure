package textadventure.ui;

@FunctionalInterface
public interface SelectResponse<T extends Option>
{

	/**
	 * Callback for the {@link Select} to return the selected {@link Option}.
	 *
	 * @param selection The selected {@link Option}.
	 */
	void select(T selection);
}
