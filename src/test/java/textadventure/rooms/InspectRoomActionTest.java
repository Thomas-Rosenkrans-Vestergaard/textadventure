package textadventure.rooms;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;

import static org.junit.Assert.*;

public class InspectRoomActionTest
{

	@Test
	public void perform() throws Exception
	{
		Room      room      = new SomeRoom();
		Character character = new SomeCharacter();
		Character other     = new SomeCharacter();
		character.setCurrentLocation(room);
		room.addCharacter(character);
		room.addCharacter(other);

		InspectRoomAction action = new InspectRoomAction();
		action.perform(character, new String[0], new SomeActionResponses()
		{
			@Override public void onInspectRoomAction(Character character, InspectRoomAction action)
			{
				try {
					assertEquals(1, action.getCharacters().size());
					assertTrue(action.getCharacters().contains(other));
					assertFalse(action.getCharacters().contains(character));
				} catch (Exception e) {
					fail();
				}
			}
		});
	}
}