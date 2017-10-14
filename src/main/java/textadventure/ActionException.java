package textadventure;

/**
 * Thrown when an error occurs while performing an {@link Action}.
 */
public class ActionException extends GameException
{

	/**
	 * The {@link Property} that the {@link Action} was performed on.
	 */
	private Property property;

	/**
	 * The {@link Action} that was attempted.
	 */
	private Action action;

	/**
	 * The {@link Player} who attempted the {@link Action}.
	 */
	private Player player;

	/**
	 * Creates a new {@link ActionException}.
	 *
	 * @param property The {@link textadventure.Property} object that the {@link Action} was performed on.
	 * @param action   The {@link Action} that was attempted.
	 * @param player   The {@link Player} who attempted the {@link Action}.
	 */
	public ActionException(Property property, Action action, Player player)
	{
		this.property = property;
		this.action = action;
		this.player = player;
	}

	/**
	 * Returns the {@link Property} that the {@link Action} was performed on.
	 *
	 * @return The {@link Property} that the {@link Action} was performed on.
	 */
	public Property getProperty()
	{
		return this.property;
	}

	/**
	 * Returns the {@link Action} that was attempted.
	 *
	 * @return The {@link Action} that was attempted.
	 */
	public Action getAction()
	{
		return this.action;
	}

	/**
	 * Returns the {@link Player} who attempted the {@link Action}.
	 *
	 * @return The {@link Player} who attempted the {@link Action}.
	 */
	public Player getPlayer()
	{
		return this.player;
	}
}
