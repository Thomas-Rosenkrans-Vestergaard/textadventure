package textadventure.select;

/**
 * Represents a {@link Select}able {@link Option}. Any implementations must delegate {@link Option#hashCode()} to
 * {@link Option#getOptionIndex()}. {@link Option#equals(Object)} must likewise compare the results from
 * {@link Option#getOptionIndex()}.
 */
public interface Option<T>
{

	/**
	 * Returns the identifier of the {@link Option}. The identifier also serves as the {@link Option#hashCode()}.
	 *
	 * @return The identifier of the {@link Option}. The identifier also serves as the {@link Option#hashCode()}.
	 */
	Integer getOptionIndex();

	/**
	 * Returns the name of the {@link Option} that should be displayed in the {@link textadventure.select.Select}.
	 *
	 * @return The name of the {@link Option} that should be displayed in the {@link textadventure.select.Select}.
	 */
	String getOptionName();

	/**
	 * Returns the description of the {@link Option} that should be displayed in the {@link textadventure.select.Select}.
	 *
	 * @return The description of the {@link Option} that should be displayed in the {@link textadventure.select.Select}.
	 */
	String getOptionDescription();

	/**
	 * Returns the actual {@link Option} value.
	 *
	 * @return The actual {@link Option} value.
	 */
	T getT();
}
