package textadventure.ui;

/**
 * Represents a {@link Select}able {@link Option}.
 */
public interface Option
{

	/**
	 * Returns the name that should be displayed in the {@link Select}.
	 *
	 * @return The name that should be displayed in the {@link Select}.
	 */
	String getOptionName();

	/**
	 * Returns the description of the {@link Option}. Informs the user what selecting the {@link Option} will do.
	 *
	 * @return The description of the {@link Option}. Informs the user what selecting the {@link Option} will do.
	 */
	String getOptionDescription();
}
