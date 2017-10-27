package textadventure.ui;

/**
 * Represents a {@link Select}able {@link Option}. Any implementations must delegate {@link Option#hashCode()} to
 * {@link Option#getOptionIdentifier()}. {@link Option#equals(Object)} must likewise compare the results from
 * {@link Option#getOptionIdentifier()}.
 */
public interface Option
{

	/**
	 * Returns the identifier of the {@link Option}. The identifier also serves as the {@link Option#hashCode()}.
	 *
	 * @return The identifier of the {@link Option}. The identifier also serves as the {@link Option#hashCode()}.
	 */
	Integer getOptionIdentifier();

	/**
	 * Returns the name of the {@link Option} that should be displayed in the {@link textadventure.ui.Select}.
	 *
	 * @return The name of the {@link Option} that should be displayed in the {@link textadventure.ui.Select}.
	 */
	String getOptionName();

	/**
	 * Returns the description of the {@link Option} that should be displayed in the {@link textadventure.ui.Select}.
	 *
	 * @return The description of the {@link Option} that should be displayed in the {@link textadventure.ui.Select}.
	 */
	String getOptionDescription();
}
