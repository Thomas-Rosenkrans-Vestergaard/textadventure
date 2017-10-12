package textadventure;


import textadventure.actions.Focusable;
import textadventure.combat.DamageSource;
import textadventure.combat.HealthSource;
import textadventure.rooms.Room;
import textadventure.items.*;


public class AbstractCharacter implements Character, Person
{
	/**
	 * The name of the {@link Player}.
	 */
	private final String name;

	/**
	 * The current location of the {@link Player}.
	 */
	private Room currentLocation;

	/**
	 * The level of the {@link Player}.
	 */
	private int playerLevel;

	/**
	 * The strength of the {@link Player}. This stat determines the damage
	 * done by the {@link Player} and the carry capacity of the {@link Player}.
	 */
	private int strength;

	/**
	 * The dexterity of the {@link Player}. This stat determines the chance
	 * to dodge incoming attacks.
	 */
	private int dexterity;

	/**
	 * The intelligence of the {@link Player}. This stat determines the
	 * chance to successfully disarm traps.
	 */
	private int intelligence;

	/**
	 * The stealth of the {@link Player} determines the chance to pickpx½ocket
	 * monsters undetected.
	 */
	private int stealth;

	/**
	 * The amount of gold the {@link Player} processes.
	 */
	private int gold;


	/**
	 * The largest amount of health the {@link Person} can posses.
	 */
	private final int maxHP;

	/**
	 * The current amount of health the {@link Person} possesses.
	 */
	private int currentHP;

	/**
	 * The {@link textadventure.Person.State} of the {@link Person}.
	 */
	private Person.State state;

	/**
	 * Creates a new {@link AbstractPerson}.
	 *
	 * @param maxHP     The maximum amount of hit points of the {@link Person}.
	 * @param currentHP The amount of hit points that the {@link Person} starts out with.
	 */
	/**
	 * <<<<<<< Updated upstream
	 * The current focus of the {@link Player}.
	 */
	private Focusable focus;


	/**
	 * @param name            The name of the {@link Character}
	 * @param currentLocation Tn which room {@link Character}
	 * @param playerLevel     The level of teh {@link Character}
	 * @param strength        Determines the damage and the carry capacity
	 * @param dexterity       This stat determines the
	 *                        chance to successfully disarm traps.
	 * @param intelligence    This stat determines the
	 *                        chance to successfully disarm traps
	 * @param stealth         determines the chance to pickpocket
	 *                        monsters undetected.
	 * @param gold            value of the player.
	 * @param maxHP           The max hitpoint
	 * @param currentHP       The current time HP
	 * @param state
	 * @param focus           is the action in use.
	 */


	public AbstractCharacter(
			String name, Room currentLocation, int playerLevel, int strength, int dexterity, int intelligence,
			int stealth,
			int gold, int maxHP, int currentHP, Person.State state, Focusable focus)
	{
		this.name = name;
		this.currentLocation = currentLocation;
		this.playerLevel = playerLevel;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.stealth = stealth;
		this.gold = gold;
		this.maxHP = maxHP;
		this.currentHP = currentHP;
		this.state = state;
		this.focus = focus;
	}

	/**
	 * Creates a new {@link AbstractPerson}. Sets the <code>currentHP</code> of the {@link Person} to
	 * <code>maxHP</code>.
	 *
	 * @param maxHP The max health of the {@link AbstractPerson}.
	 */

	/**
	 * Returns the current amount of HP the {@link Person} has.
	 *
	 * @return The current amount of HP the {@link Person} has.
	 */
	@Override public int getCurrentHP()
	{
		return currentHP;
	}

	/**
	 * Returns the largest amount of HP the {@link Person} can have.
	 *
	 * @return The largest amount of HP the {@link Person} can have.
	 */
	@Override public int getMaxHP()
	{
		return maxHP;
	}

	/**
	 * Damages the {@link Person} for the provided number of health points.
	 *
	 * @param source The source of the damage.
	 */
	@Override public void damage(DamageSource source)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Heals the {@link Person} for the provided number of health points.
	 *
	 * @param source The source of the healing.
	 */
	@Override public void heal(HealthSource source)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * Returns the state of the {@link Person}.
	 *
	 * @return The state of the {@link Person}.
	 */
	@Override public Person.State getState()
	{
		return state;
	}


}

