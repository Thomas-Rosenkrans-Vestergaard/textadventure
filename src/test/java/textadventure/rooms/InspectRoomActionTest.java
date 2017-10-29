package textadventure.rooms;

import org.junit.Test;
import textadventure.Character;
import textadventure.SomeCharacter;
import textadventure.ui.SomeGameInterface;

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

		InspectRoomAction action = new InspectRoomAction((characterResponse, actionResponse) -> {
			try {
				assertSame(character, characterResponse);
				assertFalse(actionResponse.hasException());
				assertEquals(1, actionResponse.getCharacters().size());
				assertTrue(actionResponse.getCharacters().contains(other));
				assertFalse(actionResponse.getCharacters().contains(character));
			} catch (Exception e) {
				fail();
			}
		});

		action.perform(new SomeGameInterface(), character, new String[0]);
	}
}