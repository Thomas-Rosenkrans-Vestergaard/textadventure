package textadventure;

/**
 * Thrown when attempting to get a {@link Property} that doesn't exist.
 */
public class UnknownPropertyException extends GameException
{

	/**
	 * The parent {@link Property} that doesn't contain the missing {@link Property}.
	 */
	private Property parent;

	/**
	 * The child {@link Property} that couldn't be found in the parent {@link Property}.
	 */
	private Class<? extends Property> child;

	/**
	 * Creates a new {@link UnknownPropertyException}.
	 *
	 * @param parent The parent {@link Property} that doesn't contain the missing {@link Property}.
	 * @param child  The child {@link Property} that couldn't be found in the parent {@link Property}.
	 */
	public UnknownPropertyException(Property parent, Class<? extends Property> child)
	{
		this.parent = parent;
		this.child = child;
	}

	/**
	 * Returns the parent {@link Property} that doesn't contain the missing {@link Property}.
	 *
	 * @return The parent {@link Property} that doesn't contain the missing {@link Property}.
	 */
	public Property getParent()
	{
		return this.parent;
	}

	/**
	 * Returns the child {@link Property} that couldn't be found in the parent {@link Property}.
	 *
	 * @return The child {@link Property} that couldn't be found in the parent {@link Property}.
	 */
	public Class<? extends Property> getChild()
	{
		return this.child;
	}
}
