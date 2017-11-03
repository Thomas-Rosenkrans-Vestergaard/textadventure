package textadventure;

import com.google.common.collect.ImmutableList;
import textadventure.actions.Action;
import textadventure.characters.Character;
import textadventure.combat.Faction;
import textadventure.rooms.Room;
import textadventure.rooms.RoomController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameState
{

	private Map<Class<? extends Faction>, Faction> factions;
	private Map<Character, Room>                   currentLocation;
	private RoomController                         roomController;

	public GameState(RoomController roomController)
	{
		this.factions = new HashMap<>();
		this.currentLocation = new HashMap<>();
		this.roomController = roomController;
	}

	public void addFaction(Faction faction)
	{
		factions.put(faction.getClass(), faction);
	}

	public <T extends Faction> T getFaction(Class<T> type)
	{
		return type.cast(factions.get(type));
	}

	public void removeFaction(Class<? extends Faction> type)
	{
		factions.remove(type);
	}

	public ImmutableList<Faction> getFactions()
	{
		ImmutableList.Builder<Faction> factionBuilder = new ImmutableList.Builder<>();
		factions.values().forEach(faction -> factionBuilder.add(faction));
		return factionBuilder.build();
	}

	public Room getCurrentLocation(Character character)
	{
		return this.currentLocation.get(character);
	}

	public void setCurrentLocation(Character character, Room currentLocation)
	{
		this.currentLocation.put(character, currentLocation);
	}

	public RoomController getRoomController()
	{
		return this.roomController;
	}
}
