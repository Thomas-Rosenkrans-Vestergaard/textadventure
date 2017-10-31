package textadventure.rooms;

import org.junit.Test;
<<<<<<< Updated upstream
=======
import textadventure.characters.Character;
>>>>>>> Stashed changes
import textadventure.SomeCharacter;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.ui.SomeGameInterface;

import static org.junit.Assert.*;

public class InspectFloorActionTest
{

	@Test
	public void perform() throws Exception
	{
		Floor floor = new Floor();
		Item  a     = new SomeItem();
		Item  b     = new SomeItem();
		floor.addItem(a, 0);
		floor.addItem(b, 1);
		Room          room      = new BaseRoom(null, null, floor);
		SomeCharacter character = new SomeCharacter();
		character.setCurrentLocation(room);
		InspectFloorAction action = new InspectFloorAction((characterResponse, actionResponse) -> {
			try {
				assertSame(character, characterResponse);
				assertFalse(actionResponse.hasException());
				assertSame(floor, actionResponse.getFloor());
				assertEquals(2, actionResponse.getFloor().getNumberOfItems());
				assertSame(a, actionResponse.getFloor().takeItem(0));
				assertSame(b, actionResponse.getFloor().takeItem(1));

			} catch (Exception e) {
				fail();
			}
		});

		action.perform(new SomeGameInterface(), character, new String[0]);
	}
}