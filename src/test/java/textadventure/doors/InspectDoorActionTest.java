package textadventure.doors;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.lock.Lock;
import textadventure.lock.MockLock;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class InspectDoorActionTest
{

	@Test
	public void perform() throws Exception
	{
		Lock         lock      = new MockLock(Lock.State.UNLOCKED);
		Room         a         = new SomeRoom();
		Room         b         = new SomeRoom();
		PropertyDoor door      = new NorthDoor(new BaseDoor(Door.State.OPEN, lock, a, b));
		Character    character = new SomeCharacter();

		assertEquals(Door.State.OPEN, door.getState());
		InspectDoorAction action = new InspectDoorAction(door);
		SomeActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onInspectDoorAction(Character character, InspectDoorAction action)
			{
				assertFalse(action.hasException());
				assertSame(door, action.getDoor());
			}
		};

		SomeActionResponses mocked = Mockito.spy(actionResponses);
		action.perform(character, mocked);
		Mockito.verify(mocked).onInspectDoorAction(same(character), same(action));
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new InspectDoorAction(null));
	}
}