package textadventure.ui;

@FunctionalInterface
public interface SelectCallback<T>
{

	/**
	 * Used as a callback in the {@link Select}.
	 *
	 * @throws SelectException When something goes wrong.
	 */
	void call(T item) throws SelectException;
}
