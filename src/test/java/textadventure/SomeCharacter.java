package textadventure;

import com.google.common.collect.ImmutableMap;
import textadventure.actions.Action;
import textadventure.characters.Character;
import textadventure.combat.DamageSource;
import textadventure.combat.Faction;
import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.rooms.Room;

import java.util.stream.Stream;

public class SomeCharacter implements Character
{
	private String name;
	private Faction faction;
	private Backpack backpack;
	private Room currentLocation;
	private HeadWear headWear;
	private TorsoWear torsoWear;
	private Gloves gloves;
	private Pants pants;
	private Boots boots;
	private Weapon weapon;
	private int maxHP;
	private int currentHP;
	private int level;
	private int sanity;
	private int strength;
	private int dexterity;
	private int intelligence;
	private int stealth;
	private int money;

	@Override public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override public Faction getFaction()
	{
		return this.faction;
	}

	public void setFaction(Faction faction)
	{
		this.faction = faction;
	}

	@Override public Backpack getBackpack()
	{
		return this.backpack;
	}

	public void setBackpack(Backpack backpack)
	{
		this.backpack = backpack;
	}

	@Override public Room getCurrentLocation()
	{
		return this.currentLocation;
	}

	@Override public void setCurrentLocation(Room currentLocation)
	{
		this.currentLocation = currentLocation;
	}

	@Override public HeadWear getHeadWear()
	{
		return this.headWear;
	}

	@Override public void setHeadWear(HeadWear headWear)
	{
		this.headWear = headWear;
	}

	@Override public TorsoWear getTorsoWear()
	{
		return this.torsoWear;
	}

	@Override public void setTorsoWear(TorsoWear torsoWear)
	{
		this.torsoWear = torsoWear;
	}

	@Override public Gloves getGloves()
	{
		return this.gloves;
	}

	@Override public void setGloves(Gloves gloves)
	{
		this.gloves = gloves;
	}

	@Override public Pants getPants()
	{
		return this.pants;
	}

	@Override public void setPants(Pants pants)
	{
		this.pants = pants;
	}

	@Override public Boots getBoots()
	{
		return this.boots;
	}

	@Override public void setBoots(Boots boots)
	{
		this.boots = boots;
	}

	@Override public Weapon getWeapon()
	{
		return this.weapon;
	}

	@Override public void setWeapon(Weapon weapon)
	{
		this.weapon = weapon;
	}

	@Override public int getMaxHP()
	{
		return this.maxHP;
	}

	public void setMaxHP(int maxHP)
	{
		this.maxHP = maxHP;
	}

	@Override public int getCurrentHP()
	{
		return this.currentHP;
	}

	public void setCurrentHP(int currentHP)
	{
		this.currentHP = currentHP;
	}

	@Override public int getLevel()
	{
		return this.level;
	}

	public void setLevel(int level)
	{
		this.level = level;
	}

	@Override public int getSanity()
	{
		return this.sanity;
	}

	public void setSanity(int sanity)
	{
		this.sanity = sanity;
	}

	@Override public int getStrength()
	{
		return this.strength;
	}

	public void setStrength(int strength)
	{
		this.strength = strength;
	}

	@Override public int getDexterity()
	{
		return this.dexterity;
	}

	public void setDexterity(int dexterity)
	{
		this.dexterity = dexterity;
	}

	@Override public int getIntelligence()
	{
		return this.intelligence;
	}

	public void setIntelligence(int intelligence)
	{
		this.intelligence = intelligence;
	}

	@Override public int getStealth()
	{
		return this.stealth;
	}

	public void setStealth(int stealth)
	{
		this.stealth = stealth;
	}

	@Override public int getMoney()
	{
		return this.money;
	}

	public void setMoney(int money)
	{
		this.money = money;
	}

	@Override public int takeDamage(DamageSource damageSource)
	{
		return 0;
	}

	@Override public void addProperty(String propertyName, Property property)
	{

	}

	@Override public Property getProperty(String name)
	{
		return null;
	}

	@Override public ImmutableMap<String, Property> getProperties()
	{
		return null;
	}

	@Override public void addAction(String name, Action action)
	{

	}

	@Override public Action getAction(String name)
	{
		return null;
	}

	@Override public Stream<Action> getActions()
	{
		return null;
	}
}
