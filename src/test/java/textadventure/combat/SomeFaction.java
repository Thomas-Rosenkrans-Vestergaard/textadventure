package textadventure.combat;

import com.google.common.collect.ImmutableList;
import textadventure.Game;
import textadventure.Player;
import textadventure.characters.Character;
import textadventure.rooms.Room;

public class SomeFaction implements Faction
{
	@Override public String getName()
	{
		return null;
	}

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
		return ImmutableList.of();
	}

	@Override public ImmutableList<Character> getActiveCharacters()
	{
		return ImmutableList.of();
	}

	private Player player;

	@Override public Player getLeader()
	{
		return player;
	}

	public void setPlayer(Player player)
	{
		this.player = player;
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
		return ImmutableList.of();
	}
}
