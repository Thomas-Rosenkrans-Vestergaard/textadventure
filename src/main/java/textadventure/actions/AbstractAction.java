package textadventure.actions;

import java.util.function.Consumer;

public abstract class AbstractAction implements Action
{

	/**
	 * The {@link Exception} that was thrown during execution of the {@link Action}.
	 */
	private Exception exception;

	/**
	 * Sets the {@link Exception} that was thrown during execution of the {@link Action}.
	 *
	 * @param exception The {@link Exception} that was thrown during execution of the {@link Action}.
	 */
	public void setException(Exception exception)
	{
		this.exception = exception;
	}

	/**
	 * Provides some code to call when an exception {@link T} has occurred.
	 *
	 * @param exceptionClass The class instance of type {@link T}.
	 * @param callback       The code to call if the provided type of {@link Exception} was thrown.
	 */
	@Override public <T extends Exception> void onException(Class<T> exceptionClass, Consumer<T> callback)
	{
		if (exceptionClass.isInstance(exception))
			callback.accept(exceptionClass.cast(exception));
	}

	/**
	 * Provides some code to call when the {@link Action} succeeded. The {@link Action} was successful when no
	 * {@link Exception} was thrown.
	 *
	 * @param callback The code to call if the {@link Action} succeeded.
	 */
	@Override public void onSuccess(Runnable callback)
	{
		if (exception == null)
			callback.run();
	}

	/**
	 * Returns the {@link Exception} that occurred while performing the {@link Action}. Returns <code>null</code> if
	 * no {@link Exception} was thrown.
	 *
	 * @return The {@link Exception} that occurred while performing the {@link Action}. Returns <code>null</code> if
	 * no {@link Exception} was thrown.
	 */
	@Override public Exception getException()
	{
		return exception;
	}

	/**
	 * Checks if the {@link Action} responded with an {@link Exception} of the provided {@link Class}.
	 *
	 * @param exceptionClass The {@link Exception} type to check for.
	 * @return <code>true</code> or <code>false</code>.
	 */
	@Override public boolean hasException(Class<?> exceptionClass)
	{
		return exceptionClass.isInstance(exception);
	}
}
