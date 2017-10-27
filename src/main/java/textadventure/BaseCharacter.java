package textadventure;

import com.google.common.collect.ImmutableMap;
import textadventure.items.backpack.Backpack;
import textadventure.items.backpack.DropItemAction;
import textadventure.items.backpack.PickupItemAction;
import textadventure.items.weapons.Fist;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;

public class BaseCharacter extends BasePropertyContainer implements Character
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
	 * The maximum number of hit points that the {@link Character} has.
	 */
	private int maxHP;

	/**
	 * The current number of hit points that the {@link Character} has.
	 */
	private int currentHP;

	/**
	 * The level of the {@link Character}.
	 */
	private int level;

	/**
	 * The sanity level of the {@link Character}.
	 */
	private int sanity;

	/**
	 * The strength level of the {@link Character}. Determines the damage done by the {@link Character} and the carry
	 * capacity of the {@link Character}.
	 */
	private int strength;

	/**
	 * The dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 */
	private int dexterity;

	/**
	 * The intelligence level of the {@link Character}.
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

	/**
	 * the boots you wear on the {@link Character}.
	 */
	private Boots boots;

	/**
	 * the armour you wear on the {@link Character}.
	 */
	private Torso torso;

	/**
	 * the headgear you wear on the {@link Character}.
	 */
	private Headwear headwear;

	/**
	 * the gloves you wear on the {@link Character}.
	 */
	private Gloves gloves;

	/**
	 * the pants you wear on the {@link Character}.
	 */
	private Pants pants;

	/**
	 * the weapon waer on the {@link Character}
	 */
	private Weapon weapon;


	/**
	 * Creates a new {@link BaseCharacter}.
	 *
	 * @param name            The name of the {@link Character}.
	 * @param backpack        The {@link Backpack} of the {@link Character}. Used to store items.
	 * @param currentLocation The {@link Room} the {@link Character} is currently in.
	 * @param maxHP           The maximum number of hit points that the {@link Character} has.
	 * @param currentHP       The current number of hit points that the {@link Character} has.
	 * @param level           The level of the {@link Character}.
	 * @param sanity          The sanity level of the {@link Character}.
	 * @param strength        The strength level of the {@link Character}. Determines the damage done by the
	 *                        {@link Character} and the carry capacity of the {@link Character}.
	 * @param dexterity       The dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 * @param intelligence    The intelligence level of the {@link Character}.
	 * @param stealth         The stealth level of the {@link Character}. Determines the chance to pickpocket other {@link Character}s.
	 * @param money           The amount of money the {@link Character} has.
	 */
	public BaseCharacter(
			String name,
			Backpack backpack,
			Headwear headwear,
			Torso torso,
			Gloves gloves,
			Pants pants,
			Boots boots,
			Weapon weapon,
			Room currentLocation,
			int maxHP,
			int currentHP,
			int level,
			int sanity,
			int strength,
			int dexterity,
			int intelligence,
			int stealth,
			int money)
	{
		this.name = name;
		this.backpack = backpack;
		this.headwear = headwear;
		this.torso = torso;
		this.gloves = gloves;
		this.pants = pants;
		this.boots = boots;
		this.weapon = weapon;
		this.currentLocation = currentLocation;
		this.maxHP = maxHP;
		this.currentHP = currentHP;
		this.level = level;
		this.sanity = sanity;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.stealth = stealth;
		this.money = money;
	}

	/**
	 * Creates a new {@link Character}.
	 *
	 * @param name            The name of the {@link Character}.
	 * @param backpack        The {@link Backpack} worn by the {@link Character}.
	 * @param currentLocation The {@link Room} the player is currently in.
	 */
	public BaseCharacter(String name, Backpack backpack, Room currentLocation)
	{
		this(
				name,
				backpack,
				new WornDownCargoCap(),
				new WornDownCargoTshirt(),
				null,
				new WornDownCargoPants(),
				new WornDownCargoBoots(),
				new Fist(),
				currentLocation,
				DEFAULT_MAX_HP,
				DEFAULT_MAX_HP,
				DEFAULT_LEVEL,
				DEFAULT_SANITY,
				DEFAULT_STRENGTH,
				DEFAULT_DEXTERITY,
				DEFAULT_INTELLIGENCE,
				DEFAULT_STEALTH,
				DEFAULT_MONEY
		);
	}

	/**
	 * Creates and returns a new {@link BaseCharacter} with a {@link Backpack} {@link Property} and
	 * {@link DropItemAction}.
	 *
	 * @param name            The name of the {@link Character} to create.
	 * @param backpack        The {@link Backpack} on the {@link Character}.
	 * @param currentLocation The location of the {@link Character}.
	 * @param game            The {@link Game} instance.
	 * @return The newly created {@link BaseCharacter}.
	 */
	public static Character factory(String name, Backpack backpack, Room currentLocation, Game game)
	{
		BaseCharacter character     = new BaseCharacter(name, backpack, currentLocation);
		GameInterface gameInterface = game.getGameInterface();

		character.addProperty("backpack", backpack);
		character.addAction("drop", new DropItemAction(backpack, gameInterface::onItemDrop));
		character.addAction("pickup", new PickupItemAction(backpack, gameInterface::onItemPickup));

		return character;
	}

	/**
	 * Returns an {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 *
	 * @return The {@link ImmutableMap} map of the instances of {@link Property} in the {@link PropertyContainer}.
	 */
	@Override public ImmutableMap<String, Property> getProperties()
	{
		ImmutableMap<String, Property> properties = super.getProperties();

		return new ImmutableMap.Builder<String, Property>()
				.putAll(getCurrentLocation().getProperties())
				.putAll(properties).build();
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
	 * Returns the {@link Boots} of the {@link java.lang.Character}.
	 *
	 * @return The {@link Boots} of the {@link java.lang.Character}.
	 */
	@Override public Boots getBoots()
	{
		return boots;
	}


	@Override public Boots setBoots()
	{
		return boots;
	}


	/**
	 * Returns the {@link Torso} of the {@link java.lang.Character}.
	 *
	 * @return The {@link Torso} of the {@link java.lang.Character}.
	 */
	@Override public Torso getTorso()
	{
		return torso;
	}

	@Override public Torso SetTorso()
	{
		return torso;
	}


	/**
	 * Returns the {@link Gloves} of the {@link java.lang.Character}.
	 *
	 * @return The {@link Gloves} of the {@link java.lang.Character}.
	 */
	@Override public Gloves getGloves()
	{
		return gloves;
	}

	@Override public Gloves setGloves()
	{
		return gloves;
	}

	/**
	 * Returns the {@link Headwear} of the {@link java.lang.Character}.
	 *
	 * @return The {@link Headwear} of the {@link java.lang.Character}.
	 */
	@Override public Headwear getHeadwear()
	{
		return headwear;
	}

	@Override public Headwear setHeadwear()
	{
		return headwear;
	}

	/**
	 * Returns the {@link Gloves} of the {@link java.lang.Character}.
	 *
	 * @return The {@link Gloves} of the {@link java.lang.Character}.
	 */
	@Override public Pants getPants()
	{
		return pants;
	}

	@Override public Pants setPants()
	{
		return pants;
	}

	/**
	 * return the {@link Weapon} of the {@link java.lang.Character}
	 *
	 * @return The {@link Weapon} of the {@link java.lang.Character}
	 */
	@Override public Weapon getWeapon()
	{
		return weapon;
	}

	@Override public Weapon setWeapon()
	{
		return weapon;
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
	 * Returns the current number of hit points that the {@link Character} has.
	 *
	 * @return The current number of hit points that the {@link Character} has.
	 */
	@Override public int getCurrentHP()
	{
		return currentHP;
	}

	/**
	 * Returns the maximum number of hit points that the {@link Character} can has.
	 *
	 * @return The maximum number of hit points that the {@link Character} can has.
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
	 * Returns the level of sanity of the {@link Character}.
	 *
	 * @return The level of sanity of the {@link Character}.
	 */
	@Override public int getSanity()
	{
		return sanity;
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
	 * Returns the intelligence level of the {@link Character}.
	 *
	 * @return The intelligence level of the {@link Character}.
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

