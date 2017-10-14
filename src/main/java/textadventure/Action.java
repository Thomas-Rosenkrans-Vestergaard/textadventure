package textadventure;

@FunctionalInterface
public interface Action
{

	/**
	 * Performs the {@link Action} using the provided parameters.
	 *
	 * @param game   The {@link Game} instance.
	 * @param player The {@link Player} performing the {@link Action}.
	 */
	void perform(Game game, Player player) throws ActionException;
}
