package textadventure;

public interface Action
{

	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	String getActionName();

	/**
	 * Returns the description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	String getActionDescription();

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	void perform(Game game, Player player);
}
