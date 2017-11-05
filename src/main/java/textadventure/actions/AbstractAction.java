package textadventure.actions;

import textadventure.Player;
import textadventure.characters.Character;
import textadventure.rooms.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * An abstract class implementing the {@link Action} interface. Method besides
 * {@link Action#perform(Character, ActionResponses)} are all implemented.
 */
public abstract class AbstractAction implements Action
{

	/**
	 * Stores the exception thrown while performing the {@link Action}. The field remains null if the {@link Action}
	 * performs without exception.
	 */
	protected Exception exception;

	/**
	 * Invokes the provided {@link Runnable} provided the {@link Action} has no exceptions.
	 *
	 * @param runnable
	 */
	@Override public void onSuccess(Runnable runnable)
	{
		if (exception == null) runnable.run();
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
	 * Sets the {@link Exception} that was thrown during execution of the {@link Action}.
	 *
	 * @param exception The {@link Exception} that was thrown during execution of the {@link Action}.
	 */
	public void setException(Exception exception)
	{
		this.exception = exception;
	}

	/**
	 * Checks if the {@link Action} responded with an {@link Exception} of the provided {@link Class}.
	 *
	 * @param exceptionClass The {@link Exception} type to check for.
	 * @return True when the {@link Action} reported a exception with the provided type, otherwise <code>false</code>.
	 */
	@Override public boolean hasException(Class<?> exceptionClass)
	{
		return exceptionClass.isInstance(exception);
	}

	/**
	 * Checks if the {@link Action} responded with an {@link Exception}.
	 *
	 * @return True when the {@link Action} reported a exception, otherwise <code>false</code>.
	 */
	@Override public boolean hasException()
	{
		return hasException(Exception.class);
	}

	/**
	 * Allows an {@link Action} to notify the other {@link Player}s in the provided {@link Room}.
	 *
	 * @param room     The {@link Room} where the other {@link Player}s should be notified.
	 * @param player   The {@link Player} controlling the {@link Character} who performed the {@link Action}. The
	 *                 provided consumer is not called with this {@link Player}.
	 * @param consumer The {@link Consumer} receiving the {@link ActionResponses} retrieved from the
	 *                 {@link Player#getSecondaryActionResponses()}.
	 */
	public void alertOtherPlayers(Room room, Player player, Consumer<ActionResponses> consumer)
	{
		List<Player> alerted = new ArrayList<>();
		alerted.add(player);
		for (Character x : room.getCharacters())
			if (!alerted.contains(player)) {
				consumer.accept(x.getFaction().getLeader().getSecondaryActionResponses());
				alerted.add(player);
			}
	}
}
