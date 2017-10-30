package textadventure;

import textadventure.combat.Faction;

/**
 * Thrown when a {@link Faction} has already been taken by another {@link Player}.
 */
public class FactionAlreadyTakenException extends GameException
{

	/**
	 * The {@link Faction} that was taken.
	 */
	private Faction faction;

	/**
	 * Creates a new {@link FactionAlreadyTakenException}.
	 *
	 * @param faction The {@link Faction} that was taken.
	 */
	public FactionAlreadyTakenException(Faction faction)
	{
		this.faction = faction;
	}

	/**
	 * Returns the {@link Faction} that was taken.
	 *
	 * @return The {@link Faction} that was taken.
	 */
	public Faction getFaction()
	{
		return this.faction;
	}
}
