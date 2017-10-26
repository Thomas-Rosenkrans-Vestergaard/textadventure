package textadventure.ui;

/**
 * Represents a {@link Select}able {@link Option}.
 */
public interface Option
{

	/**
	 * Returns the name of the {@link Option} that should be displayed in the {@link textadventure.ui.Select} or
	 * {@link textadventure.ui.MultiSelect}.
	 *
	 * @return The name of the {@link Option} that should be displayed in the {@link textadventure.ui.Select} or
	 * {@link textadventure.ui.MultiSelect}.
	 */
	String getOptionName();

	/**
	 * Returns the description of the {@link Option} that should be displayed in the {@link textadventure.ui.Select} or
	 * {@link textadventure.ui.MultiSelect}.
	 *
	 * @return The description of the {@link Option} that should be displayed in the {@link textadventure.ui.Select} or
	 * {@link textadventure.ui.MultiSelect}.
	 */
	String getOptionDescription();
}
