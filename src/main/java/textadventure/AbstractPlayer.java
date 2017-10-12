package textadventure;

import textadventure.actions.Focusable;
import textadventure.rooms.Room;


/**
 * Abstract implementation of the {@link Player} interface.
 */
public abstract class AbstractPlayer extends AbstractPerson implements Player
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
	 * <<<<<<< Updated upstream
	 * The current focus of the {@link Player}.
	 */
	private Focusable focus;

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
	 **/
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
	 * Creates a new {@link AbstractPlayer}.
	 *
	 * @param name            The name of the {@link Player}.
	 * @param currentLocation The current location of the {@link Player}.
	 * @param strength        determines the damage and the carry capacity
	 * @param dexterity       This stat determines the chance to successfully disarm traps.
	 * @param intelligence    This stat determines the chance to successfully disarm traps.
	 * @param stealth         determines the chance to pickpocket monsters undetected.
	 */
	public AbstractPlayer(
			String name, Room currentLocation, int maxHealth, int strength, int dexterity,
			int intelligence, int stealth)
	{
		super(100);
		this.currentLocation = currentLocation;
		this.name = name;
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		this.stealth = stealth;
	}

	/**
	 * Returns the name of the {@link Player}.
	 *
	 * @return The name of the {@link Player}.
	 */
	@Override public String getName()
	{
		return name;
	}

	/**
	 * Returns the {@link Player}'s current location.
	 *
	 * @return currentLocation of the {@link Player}.
	 */
	public Room getCurrentLocation()
	{
		return currentLocation;
	}


	/**
	 * Sets the {@link Player's}'s current location.
	 *
	 * @param currentLocation The current location of the {@link Player}.
	 */
	public void setCurrentLocation(Room currentLocation)
	{
		this.currentLocation = currentLocation;
	}


	/**
	 * Returns the current focus of the {@link Player}.
	 *
	 * @return The current focus of the {@link Player}.
	 */
	@Override public Focusable getFocus()
	{
		return focus;
	}

	/**
	 * Sets the focus of the {@link Player} on the provided {@link Focusable} object.
	 *
	 * @param focus The {@link Focusable} object.
	 */
	@Override public void setFocus(Focusable focus)
	{
		this.focus = focus;
	}
}

