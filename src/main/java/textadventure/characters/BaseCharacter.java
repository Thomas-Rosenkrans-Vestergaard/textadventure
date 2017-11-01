package textadventure.characters;

import com.google.common.collect.ImmutableMap;
import textadventure.AbstractPropertyContainer;
import textadventure.Property;
import textadventure.PropertyContainer;
import textadventure.combat.AttackAction;
import textadventure.combat.DamageSource;
import textadventure.combat.Faction;
import textadventure.items.EquipAction;
import textadventure.items.UnEquipAction;
import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Fists;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.rooms.InspectFloorAction;
import textadventure.rooms.InspectRoomAction;
import textadventure.rooms.Room;

import java.awt.*;

/**
 * The default implementation of the {@link Character} interface.
 */
public class BaseCharacter extends AbstractPropertyContainer implements Character
{

	/**
	 * The default number of positions in the {@link Character}s {@link Backpack}.
	 */
	static public int DEFAULT_BACKPACK_POSITIONS = 10;

	/**
	 * The default maximum amount of HP the {@link Character} has at the start of the game.
	 */
	static public int DEFAULT_MAX_HP = 100;

	/**
	 * The default level of the {@link Character} at the start of the game.
	 */
	static public int DEFAULT_LEVEL = 1;

	/**
	 * The default sanity of the {@link Character} at the start of the game.
	 */
	static public int DEFAULT_SANITY = 100;

	/**
	 * The default strength of the {@link Character} at the start of the game.
	 */
	static public int DEFAULT_STRENGTH = 1;

	/**
	 * The default dexterity of the {@link Character} at the start of the game.
	 */
	static public int DEFAULT_DEXTERITY = 1;

	/**
	 * The default intelligence of the {@link Character} at the start of the game.
	 */
	static public int DEFAULT_INTELLIGENCE = 1;

	/**
	 * The default stealth of the {@link Character} at the start of the game.
	 */
	static public int DEFAULT_STEALTH = 1;

	/**
	 * The default amount of money the {@link Character} has at the start of the game.
	 */
	static public int DEFAULT_MONEY = 0;

	/**
	 * The name of the {@link Character}.
	 */
	private String name;

	/**
	 * The {@link Faction} the {@link Character} belongs to.
	 */
	private Faction faction;

	/**
	 * The {@link Backpack} of the {@link Character}. Used to store items.
	 */
	private Backpack backpack;

	/**
	 * The {@link Room} the {@link Character} is currently in.
	 */
	private Room currentLocation;

	/**
	 * The {@link HeadWear} worn by the {@link Character}.
	 */
	private HeadWear headWear;

	/**
	 * The {@link TorsoWear} worn by the {@link Character}.
	 */
	private TorsoWear torsoWear;

	/**
	 * The {@link Gloves} worn by the {@link Character}.
	 */
	private Gloves gloves;

	/**
	 * The {@link Pants} worn on the {@link Character}.
	 */
	private Pants pants;

	/**
	 * The {@link Boots} worn by the {@link Character}.
	 */
	private Boots boots;

	/**
	 * The {@link Weapon} equipped by the {@link Character}
	 */
	private Weapon weapon;

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
	 * Creates a new {@link BaseCharacter}.
	 *
	 * @param faction      The {@link Faction} the {@link Character} belongs to.
	 * @param name         The name of the {@link Character}.
	 * @param backpack     The {@link Backpack} of the {@link Character}. Used to store items.
	 * @param headWear     The {@link HeadWear} worn by the {@link Character}.
	 * @param torsoWear    The {@link TorsoWear} worn by the {@link Character}.
	 * @param gloves       The {@link Gloves} worn by the {@link Character}.
	 * @param pants        The {@link Pants} worn by the {@link Character}.
	 * @param boots        The {@link Boots} worn by the {@link Character}.
	 * @param weapon       The {@link Weapon} equipped.
	 * @param maxHP        The maximum number of hit points that the {@link Character} has.
	 * @param currentHP    The current number of hit points that the {@link Character} has.
	 * @param level        The level of the {@link Character}.
	 * @param sanity       The sanity level of the {@link Character}.
	 * @param strength     The strength level of the {@link Character}. Determines the damage done by the
	 *                     {@link Character} and the carry capacity of the {@link Character}.
	 * @param dexterity    The dexterity level of the {@link Character}. Determines the chance to dodge incoming attacks.
	 * @param intelligence The intelligence level of the {@link Character}.
	 * @param stealth      The stealth level of the {@link Character}. Determines the chance to pickpocket other {@link Character}s.
	 * @param money        The amount of money the {@link Character} has.
	 */
	public BaseCharacter(
			Faction faction,
			String name,
			Backpack backpack,
			HeadWear headWear,
			TorsoWear torsoWear,
			Gloves gloves,
			Pants pants,
			Boots boots,
			Weapon weapon,
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
		this.faction = faction;
		this.backpack = backpack;
		this.headWear = headWear;
		this.torsoWear = torsoWear;
		this.gloves = gloves;
		this.pants = pants;
		this.boots = boots;
		this.weapon = weapon;
		this.currentLocation = faction.getStartingRoom();
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
	 * Creates a new {@link BaseCharacter} from a {@link CharacterCreationTemplate}.
	 *
	 * @param faction                   The {@link Faction} The {@link Character} belongs to.
	 * @param characterCreationTemplate The {@link CharacterCreationTemplate}.
	 * @return The newly created {@link BaseCharacter}.
	 */
	public static BaseCharacter factory(Faction faction, CharacterCreationTemplate characterCreationTemplate)
	{
		Backpack backpack = Backpack.factory(DEFAULT_BACKPACK_POSITIONS);
		BaseCharacter character = new BaseCharacter(
				faction,
				characterCreationTemplate.getName(),
				backpack,
				new WornDownCap(1.0, Color.BLUE),
				new WornDownSweatshirt(1.0),
				null,
				new WornDownCargoPants(1.0),
				new WornDownWorkBoots(1.0),
				new Fists(),
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

		character.addProperty("backpack", backpack);
		character.addAction("drop", new DropItemAction());
		character.addAction("pickup", new PickUpItemAction());
		character.addAction("inspect-room", new InspectRoomAction());
		character.addAction("inspect-floor", new InspectFloorAction());
		character.addAction("stats", new CharacterInformationAction());
		character.addAction("attack", new AttackAction());
		character.addAction("equip", new EquipAction());
		character.addAction("unequip", new UnEquipAction());

		faction.getStartingRoom().addCharacter(character);

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
	 * Returns the {@link Faction} the {@link Character} belongs to.
	 *
	 * @return The {@link Faction} the {@link Character} belongs to.
	 */
	@Override public Faction getFaction()
	{
		return faction;
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
	 * Return the double representing the protective factor of the {@link Wearable}s on the {@link Character}.
	 *
	 * @return The double representing the protective factor of the {@link Wearable}s on the {@link Character}.
	 */
	@Override public double getProtectiveFactor()
	{
		return headWear.getProtectiveFactor() +
				torsoWear.getProtectiveFactor() +
				gloves.getProtectiveFactor() +
				pants.getProtectiveFactor() +
				boots.getProtectiveFactor();
	}

	/**
	 * Returns the {@link HeadWear} worn by the {@link Character}.
	 *
	 * @return The {@link HeadWear} worn by the {@link Character}.
	 */
	@Override public HeadWear getHeadWear()
	{
		return headWear;
	}

	/**
	 * Returns the {@link TorsoWear} worn by the {@link Character}.
	 *
	 * @return The {@link TorsoWear} worn by the {@link Character}.
	 */
	@Override public TorsoWear getTorsoWear()
	{
		return torsoWear;
	}

	/**
	 * Returns the {@link Gloves} worn by the {@link Character}.
	 *
	 * @return The {@link Gloves} worn by the {@link Character}.
	 */
	@Override public Gloves getGloves()
	{
		return gloves;
	}

	/**
	 * Returns the {@link Gloves} worn by the {@link Character}.
	 *
	 * @return The {@link Gloves} worn by the {@link Character}.
	 */
	@Override public Pants getPants()
	{
		return pants;
	}

	/**
	 * Returns the {@link Boots} worn by the {@link Character}.
	 *
	 * @return The {@link Boots} worn by the {@link Character}.
	 */
	@Override public Boots getBoots()
	{
		return boots;
	}

	/**
	 * return the {@link Weapon} equipped by the {@link Character}
	 *
	 * @return The {@link Weapon} equipped by the {@link Character}
	 */
	@Override public Weapon getWeapon()
	{
		return weapon;
	}

	/**
	 * Sets the {@link HeadWear} worn by the {@link Character}.
	 *
	 * @param headWear The {@link HeadWear} to set.
	 */
	@Override public void setHeadWear(HeadWear headWear)
	{
		this.headWear = headWear;
	}

	/**
	 * Sets the {@link TorsoWear} worn by the {@link Character}.
	 *
	 * @param torsoWear The {@link TorsoWear} to set.
	 */
	@Override public void setTorsoWear(TorsoWear torsoWear)
	{
		this.torsoWear = torsoWear;
	}

	/**
	 * Sets the {@link Gloves} worn by the {@link Character}.
	 *
	 * @param gloves The {@link Gloves} to set.
	 */
	@Override public void setGloves(Gloves gloves)
	{
		this.gloves = gloves;
	}

	/**
	 * Sets the {@link Pants} worn by the {@link Character}.
	 *
	 * @param pants The {@link Pants} to set.
	 */
	@Override public void setPants(Pants pants)
	{
		this.pants = pants;
	}

	/**
	 * Sets the {@link Boots} worn by the {@link Character}.
	 *
	 * @param boots The {@link Boots} to set.
	 */
	@Override public void setBoots(Boots boots)
	{
		this.boots = boots;
	}

	/**
	 * Sets the {@link Weapon} equipped by the {@link Character}.
	 *
	 * @param weapon The {@link Weapon} to set.
	 */
	@Override public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
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
	 * Returns the strength level of the {@link Character}. Determines the damage done by the {@link Character} and the
	 * carry capacity of the {@link Character}.
	 *
	 * @return The strength level of the {@link Character}. Determines the damage done by the {@link Character} and the
	 * carry capacity of the {@link Character}.
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
	 * Returns the stealth level of the {@link Character}.
	 *
	 * @return The stealth level of the {@link Character}.
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

	/**
	 * Causes the {@link Character} to take damage from the provided {@link DamageSource}.
	 *
	 * @param damageSource The {@link DamageSource}.
	 * @return How much damage was taken.
	 */
	@Override public int takeDamage(DamageSource damageSource)
	{
		this.currentHP -= damageSource.getDamage();

		return damageSource.getDamage();
	}
}

