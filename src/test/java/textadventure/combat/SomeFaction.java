package textadventure.combat;

import com.google.common.collect.ImmutableList;
import textadventure.Game;
import textadventure.Player;
import textadventure.characters.Character;
import textadventure.rooms.Room;

public class SomeFaction implements Faction
{
	@Override public boolean hasWon(Game state)
	{
		return false;
	}

	@Override public boolean hasLost(Game state)
	{
		return false;
	}

	@Override public ImmutableList<Character> getCharacters(Character.Status status)
	{
		return null;
	}

	@Override public Player getLeader()
	{
		return null;
	}

	@Override public Room getStartingRoom()
	{
		return null;
	}

	@Override public String getStartingMessage()
	{
		return null;
	}

	@Override public String getEndingMessageOnWin()
	{
		return null;
	}

	@Override public String getEndingMessageOnLoss()
	{
		return null;
	}

	@Override public void addCharacter(Character character)
	{

	}

	@Override public ImmutableList<Character> getCharacters()
	{
		return null;
	}
}
