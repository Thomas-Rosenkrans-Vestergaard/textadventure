package textadventure.characters;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.SomeActionResponses;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class InspectRoomActionTest
{

	private InspectRoomAction action;

	@Test
	public void perform() throws Exception
	{
		Room      room      = new SomeRoom();
		Character character = new SomeCharacter();
		Character a         = new SomeCharacter();
		Character b         = new SomeCharacter();
		character.setCurrentLocation(room);
		room.addCharacter(character);
		room.addCharacter(a);
		room.addCharacter(b);

		InspectRoomAction action = new InspectRoomAction();

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onInspectRoomAction(Character character, InspectRoomAction action)
			{
				assertEquals(2, action.getCharacters().size());
				assertTrue(action.getCharacters().contains(a));
				assertTrue(action.getCharacters().contains(b));
				assertFalse(action.getCharacters().contains(character));
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onInspectRoomAction(same(character), same(action));
	}
}