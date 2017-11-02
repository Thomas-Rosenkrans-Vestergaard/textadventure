package textadventure.combat;

import textadventure.characters.Character;

public class DamageReport
{
	private Character    attacker;
	private Character    target;
	private DamageSource source;

	public DamageReport(Character attacker, Character target, DamageSource source)
	{
		this.attacker = attacker;
		this.target = target;
		this.source = source;
	}

	public Character getAttacker()
	{
		return this.attacker;
	}

	public Character getTarget()
	{
		return this.target;
	}

	public DamageSource getSource()
	{
		return this.source;
	}
}
