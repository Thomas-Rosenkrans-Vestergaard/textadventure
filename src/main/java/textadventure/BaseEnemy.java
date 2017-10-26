package textadventure;

import textadventure.items.backpack.Backpack;
import textadventure.rooms.Room;

public class BaseEnemy extends BaseCharacter implements Enemy
{
	public BaseEnemy(String name, Backpack backpack, Room currentLocation, int maxHP, int currentHP, int level, int sanity, int strength, int dexterity, int intelligence, int stealth, int money)
	{
		super(name, backpack, currentLocation, maxHP, currentHP, level, sanity, strength, dexterity, intelligence, stealth, money);
	}

	/**
	 * Creates a new {@link Character}.
	 *
	 * @param name            The name of the {@link Character}.
	 * @param backpack        The {@link Backpack} worn by the {@link Character}.
	 * @param currentLocation The {@link Room} the player is currently in.
	 */
	public BaseEnemy(String name, Backpack backpack, Room currentLocation)
	{
		super(name, backpack, currentLocation);
	}


	/**
	 *Returns an int representing an attack
	 */
	@Override public int attack()
	{
		return 0;
	}
}
