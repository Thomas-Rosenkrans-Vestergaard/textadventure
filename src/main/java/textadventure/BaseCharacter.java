package textadventure;

import textadventure.items.backpack.Backpack;
import textadventure.rooms.Room;
import textadventure.rooms.Landmine;

public class BaseCharacter implements Character
{

	/**
	 * The name of the {@link Character}.
	 */
	private String name;

	/**
	 * The {@link Backpack} of the {@link Character}. Used to store items.
	 */
	private Backpack backpack;

	/**
	 * The {@link Room} the {@link Character} is currently in.
	 */
	private Room currentLocation;

	/**
	 * The current number of hit points that the {@link Character} has.
	 */
	private int currentHP;

	/**
	 * The maximum number of hit points that the {@link Character} has.
	 */
	private int maxHP;

	/**
	 * The level of the {@link Character}.
	 */
	private int level;

	/**
	 * The strength level of the {@link Character}. Determines the damage done by the
	 * {@link Character} and the carry capacity of the {@link Character}.
	 */
	private int strength;

	/**
	 * The dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 */
	private int dexterity;

	/**
	 * The intelligence level of the {@link Character}. Determines the chance to disarm {@link Landmine}s.
	 */
	private int intelligence;

	/**
	 * The stealth level of the {@link Character}. Determines the chance to pickpocket other {@link Character}s.
	 */
	private int stealth;

	/**
	 * The amount of money the {@link Character} has.
	 */
	private int money;

	public BaseCharacter(
			String name,
			Backpack backpack,
			Room currentLocation,
			int maxHP,
			int currentHP,
			int level,
			int strength,
			int dexterity,
			int intelligence,
			int stealth,
			int money)
	{
		this.name = name;
		this.backpack = backpack;
		this.currentLocation = currentLocation;
		this.maxHP = maxHP;
		this.currentHP = currentHP;
		this.level = level;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.stealth = stealth;
		this.money = money;
	}

	/**
	 * Returns the name of the {@link Character}.
	 *
	 * @return The name of the {@link Character}.
	 */
	@Override public String getName()
	{
		return name;
	}

	/**
	 * Returns the {@link Backpack} of the {@link Character}.
	 *
	 * @return The {@link Backpack} of the {@link Character}.
	 */
	@Override public Backpack getBackpack()
	{
		return backpack;
	}

	/**
	 * Returns the {@link Room} the {@link Character} is currently in.
	 *
	 * @return The {@link Room} the {@link Character} is currently in.
	 */
	@Override public Room getCurrentLocation()
	{
		return currentLocation;
	}

	/**
	 * Updates the {@link Room} the {@link Character} is currently in.
	 *
	 * @param room The {@link Room} to update to.
	 */
	@Override public void setCurrentLocation(Room room)
	{
		this.currentLocation = room;
	}

	/**
	 * Returns the current number of hit points that the {@link java.lang.Character} has.
	 *
	 * @return The current number of hit points that the {@link java.lang.Character} has.
	 */
	@Override public int getCurrentHP()
	{
		return currentHP;
	}

	/**
	 * Returns the maximum number of hit points that the {@link java.lang.Character} can has.
	 *
	 * @return The maximum number of hit points that the {@link java.lang.Character} can has.
	 */
	@Override public int getMaxHP()
	{
		return maxHP;
	}

	/**
	 * Returns the level of the {@link Character}.
	 *
	 * @return The level of the {@link Character}.
	 */
	@Override public int getLevel()
	{
		return level;
	}

	/**
	 * Returns the strength level of the {@link Character}. Determines the damage done by the
	 * {@link Character} and the carry capacity of the {@link Character}.
	 *
	 * @return The strength level of the {@link Character}. Determines the damage done by the
	 * {@link Character} and the carry capacity of the {@link Character}.
	 */
	@Override public int getStrength()
	{
		return strength;
	}

	/**
	 * Returns the dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 *
	 * @return The dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 */
	@Override public int getDexterity()
	{
		return dexterity;
	}

	/**
	 * Returns the intelligence level of the {@link Character}. Determines the chance to disarm
	 * {@link Landmine}s.
	 *
	 * @return The intelligence level of the {@link Character}. Determines the chance to disarm
	 * {@link Landmine}s.
	 */
	@Override public int getIntelligence()
	{
		return intelligence;
	}

	/**
	 * Returns the stealth level of the {@link Character}. Determines the chance to pickpocket other
	 * {@link Character}s.
	 *
	 * @return The stealth level of the {@link Character}. Determines the chance to pickpocket other
	 * {@link Character}s.
	 */
	@Override public int getStealth()
	{
		return stealth;
	}

	/**
	 * Returns the amount of money the {@link Character} has.
	 *
	 * @return The amount of money the {@link Character} has.
	 */
	@Override public int getMoney()
	{
		return money;
	}
}

