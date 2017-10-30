package textadventure;

import textadventure.combat.Faction;

public class FactionAlreadyTakenException extends GameException
{
	private Faction faction;

	public FactionAlreadyTakenException(Faction faction)
	{
		this.faction = faction;
	}

	public Faction getFaction()
	{
		return this.faction;
	}
}
