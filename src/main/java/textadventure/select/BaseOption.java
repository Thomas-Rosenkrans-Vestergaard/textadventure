package textadventure.select;

/**
 * The default implementation of the {@link Option} interface.
 */
public class BaseOption<T> implements Option<T>
{

	/**
	 * The identifier of the {@link Option}.
	 */
	private int identifier;

	/**
	 * The name of the {@link Option}.
	 */
	private String name;

	/**
	 * The description of the {@link Option}.
	 */
	private String description;

	/**
	 * The actual {@link Option} value.
	 */
	private T value;

	/**
	 * Creates a new {@link BaseOption}.
	 *
	 * @param identifier  The identifier of the {@link Option}.
	 * @param name        The name of the {@link Option}.
	 * @param description The description of the {@link Option}.
	 * @param value       The actual value of {@link Option}.
	 */
	public BaseOption(int identifier, String name, String description, T value)
	{
		this.identifier = identifier;
		this.name = name;
		this.description = description;
		this.value = value;
	}

	/**
	 * Returns the identifier of the {@link Option}. The identifier also serves as the {@link Option#hashCode()}.
	 *
	 * @return The identifier of the {@link Option}. The identifier also serves as the {@link Option#hashCode()}.
	 */
	@Override public Integer getOptionIndex()
	{
		return identifier;
	}

	/**
	 * Returns the name that should be displayed in the {@link Select}.
	 *
	 * @return The name that should be displayed in the {@link Select}.
	 */
	@Override public String getOptionName()
	{
		return name;
	}

	/**
	 * Returns the name that should be displayed in the {@link Select}.
	 *
	 * @return The name that should be displayed in the {@link Select}.
	 */
	@Override public String getOptionDescription()
	{
		return description;
	}

	/**
	 * Returns the actual {@link Option} value.
	 *
	 * @return The actual {@link Option} value.
	 */
	@Override public T getT()
	{
		return value;
	}

	/**
	 * Returns the identifier of the {@link Option}.
	 *
	 * @return The identifier of the {@link Option}.
	 */
	@Override public int hashCode()
	{
		return identifier;
	}

	/**
	 * Compares equality with the provided {@link Object}. The two objects are only equal when <code>obj</code> is an
	 * instance of {@link Option} and their {@link Option#getOptionIndex()} are equal.
	 *
	 * @param obj The other object.
	 * @return <code>True</code> when equal, <code>false</code> when not.
	 */
	@Override public boolean equals(Object obj)
	{
		return obj != null && obj instanceof Option && identifier == ((Option) obj).getOptionIndex();
	}
}
