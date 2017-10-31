package textadventure.actions;

import textadventure.characters.Character;

import java.util.function.Consumer;

/**
 * Represents some {@link Action} that can be taken by a {@link Character}. The state of the {@link Action} should be
 * reset on each call from {@link Action#perform(Character, String[], ActionResponses)}.
 */
public interface Action
{

	/**
	 * Resets the {@link Action} to its default state.
	 */
	void reset();

	/**
	 * Performs the {@link Action} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link Action}.
	 * @param arguments The arguments provided to the {@link Action}.
	 * @param responses The {@link ActionResponses} to report to after performing the {@link Action}.
	 */
	void perform(Character character, String[] arguments, ActionResponses responses);

	/**
	 * Invokes the provided {@link Runnable} provided the {@link Action} has no exceptions.
	 */
	void onSuccess(Runnable runnable);

	/**
	 * Provides some code to call when an exception {@link T} has occurred.
	 *
	 * @param exceptionClass The class instance of type {@link T}.
	 * @param callback       The code to call if the provided type of {@link Exception} was thrown.
	 * @param <T>            The type of {@link Exception} to trigger the code for.
	 */
	<T extends Exception> void onException(Class<T> exceptionClass, Consumer<T> callback);

	/**
	 * Returns the {@link Exception} that occurred while performing the {@link Action}. Returns <code>null</code> if
	 * no {@link Exception} was thrown.
	 *
	 * @return The {@link Exception} that occurred while performing the {@link Action}. Returns <code>null</code> if
	 * no {@link Exception} was thrown.
	 */
	Exception getException();

	/**
	 * Sets the {@link Exception} that was thrown during execution of the {@link Action}.
	 *
	 * @param exception The {@link Exception} that was thrown during execution of the {@link Action}.
	 */
	void setException(Exception exception);

	/**
	 * Checks if the {@link Action} responded with an {@link Exception} of the provided {@link Class}.
	 *
	 * @param exceptionClass The {@link Exception} type to check for.
	 * @return True when the {@link Action} reported a exception with the provided type, otherwise <code>false</code>.
	 */
	boolean hasException(Class<?> exceptionClass);

	/**
	 * Checks if the {@link Action} responded with an {@link Exception}.
	 *
	 * @return True when the {@link Action} reported a exception, otherwise <code>false</code>.
	 */
	boolean hasException();
}
