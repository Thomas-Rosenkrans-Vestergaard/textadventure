package textadventure;

import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Weapon;
import textadventure.rooms.Room;

/**
 * Represents an opponent in-game.
 */
public class BaseEnemy extends BaseCharacter implements Enemy
{

	/**
	 * Creates a new {@link BaseEnemy}.
	 *
	 * @param name
	 * @param backpack
	 * @param currentLocation
	 * @param maxHP
	 * @param currentHP
	 * @param level
	 * @param sanity
	 * @param strength
	 * @param dexterity
	 * @param intelligence
	 * @param stealth
	 * @param money
	 */
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
	 * Attacks the {@link Enemy} using the provided {@link Weapon}.
	 *
	 * @param weapon The {@link Weapon} used to attack the {@link Enemy}.
	 * @return The amount of damage taken by the {@link Enemy}.
	 */
	@Override public final int attack(Weapon weapon)
	{
		throw new UnsupportedOperationException();
	}
}
