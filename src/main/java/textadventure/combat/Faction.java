package textadventure.combat;

import textadventure.Game;

/**
 * The {@link Faction}s the {@link textadventure.Character}s can belong to.
 */
public interface Faction
{

	/**
	 * Checks if the {@link Faction} win condition has been fulfilled.
	 *
	 * @param game The {@link Game} instance.
	 * @return True when the win condition of the {@link Faction} has been fulfilled.
	 */
	boolean hasWon(Game game);
}
