package textadventure.characters;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.SomeActionResponses;
import textadventure.items.Item;
import textadventure.items.SomeItem;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Floor;
import textadventure.rooms.Room;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

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
		InspectFloorAction action = new InspectFloorAction();

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onInspectFloorAction(Character character, InspectFloorAction action)
			{
				try {
					assertSame(floor, action.getFloor());
					assertEquals(2, action.getFloor().getNumberOfItems());
					assertSame(a, action.getFloor().takeItem(0));
					assertSame(b, action.getFloor().takeItem(1));
				} catch (Exception e) {
					fail();
				}
			}
		};


		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onInspectFloorAction(same(character), same(action));
	}
}