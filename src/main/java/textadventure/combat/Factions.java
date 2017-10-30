package textadventure.combat;

import com.google.common.collect.ImmutableList;
import textadventure.Game;

/**
 * The {@link Faction}s in the {@link Game}.
 */
public class Factions
{

	/**
	 * The {@link Escapees} {@link Faction}.
	 */
	public static final Faction ESCAPEES = new Escapees();

	/**
	 * The {@link SecretPolice} {@link Faction}.
	 */
	public static final Faction SECRET_POLICE = new SecretPolice();

	/**
	 * The {@link Faction}s in the {@link Game}.
	 */
	public static final ImmutableList<Faction> FACTIONS = ImmutableList.of(ESCAPEES, SECRET_POLICE);

	/**
	 * The {@link Escapees}.
	 */
	private static class Escapees extends Faction
	{

		/**
		 * Checks if the {@link Faction} win condition has been fulfilled.
		 *
		 * @param game The {@link Game} instance.
		 * @return True when the win condition of the {@link Faction} has been fulfilled.
		 */
		@Override public boolean hasWon(Game game)
		{
			return false;
		}
	}

	/**
	 * The {@link SecretPolice}.
	 */
	private static class SecretPolice extends Faction
	{

		/**
		 * Checks if the {@link Faction} win condition has been fulfilled.
		 *
		 * @param game The {@link Game} instance.
		 * @return True when the win condition of the {@link Faction} has been fulfilled.
		 */
		@Override public boolean hasWon(Game game)
		{
			return false;
		}
	}
}
