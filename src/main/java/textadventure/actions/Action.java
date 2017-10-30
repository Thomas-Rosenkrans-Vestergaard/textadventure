package textadventure.actions;

import textadventure.characters.Character;
import textadventure.ui.GameInterface;

import java.util.function.Consumer;

public interface Action
{

	/**
	 * Performs the {@link Action} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link Action}.
	 * @param arguments     The arguments provided to the {@link Action}.
	 */
	void perform(GameInterface gameInterface, Character character, String[] arguments);

	/**
	 * Provides some code to call when the {@link Action} succeeded. The {@link Action} was successful when no
	 * {@link Exception} was thrown.
	 *
	 * @param callback The code to call if the {@link Action} succeeded.
	 */
	void onSuccess(Runnable callback);

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
