package textadventure.ui;

/**
 * Represents a {@link Select}able {@link Option}.
 */
public interface Option
{

	/**
	 * Returns the name that should be displayed in the {@link Select} or {@link MultiSelect}.
	 *
	 * @return The name that should be displayed in the {@link Select} or {@link MultiSelect}.
	 */
	String getOptionName();

	/**
	 * Returns the name that should be displayed in the {@link textadventure.ui.Select} or {@link textadventure.ui.MultiSelect}.
	 *
	 * @return The name that should be displayed in the {@link textadventure.ui.Select} or {@link textadventure.ui.MultiSelect}.
	 */
	String getOptionDescription();
}
