package textadventure;

import textadventure.combat.Faction;

/**
 * Thrown when attempting to access an unknown {@link textadventure.combat.Faction} based on a type.
 */
public class NoSuchFactionException extends GameException
{

	/**
	 * The type of the unknown faction.
	 */
	private Class<? extends Faction> type;

	/**
	 * Creates a new {@link NoSuchFactionException}.
	 *
	 * @param type The type of the unknown faction.
	 */
	public NoSuchFactionException(Class<? extends Faction> type)
	{
		this.type = type;
	}

	/**
	 * Returns the type of the unknown faction.
	 *
	 * @return The type of the unknown faction.
	 */
	public Class<? extends Faction> getType()
	{
		return this.type;
	}
}
