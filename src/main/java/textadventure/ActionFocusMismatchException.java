package textadventure;

/**
 * Thrown when some {@link Action} could not be performed on some
 * {@link textadventure.Property} object.
 */
public class ActionFocusMismatchException extends ActionException
{

	/**
	 * Creates a new {@link ActionFocusMismatchException}.
	 *
	 * @param focus  The {@link textadventure.Property} object that the {@link Action} could not be performed on.
	 * @param action The {@link Action} that was attempted.
	 * @param player The {@link Player} who attempted the {@link Action}.
	 */
	public ActionFocusMismatchException(textadventure.Property focus, Action action, Player player)
	{
		super(focus, action, player);
	}
}
