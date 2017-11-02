package textadventure.combat;

import textadventure.characters.Character;

public class HealingReport
{

	private Character     character;
	private HealingSource healingSource;

	public HealingReport(Character character, HealingSource healingSource)
	{
		this.character = character;
		this.healingSource = healingSource;
	}

	public Character getCharacter()
	{
		return this.character;
	}

	public HealingSource getHealingSource()
	{
		return this.healingSource;
	}
}
