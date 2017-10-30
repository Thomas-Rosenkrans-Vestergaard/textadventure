package textadventure;

import textadventure.combat.Faction;

public class FactionAlreadyTakenException extends GameException
{
	private Player player;
	private Faction faction;

	public FactionAlreadyTakenException(Player player, Faction faction)
	{
		this.player = player;
		this.faction = faction;
	}

	public Player getPlayer()
	{
		return this.player;
	}

	public Faction getFaction()
	{
		return this.faction;
	}
}
