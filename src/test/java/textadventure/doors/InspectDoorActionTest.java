package textadventure.doors;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.characters.Character;
import textadventure.SomeCharacter;
import textadventure.actions.ActionPerformCallback;
import textadventure.actions.ActionTest;
import textadventure.lock.Lock;
import textadventure.lock.MockLock;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;
import textadventure.ui.GameInterface;
import textadventure.ui.SomeGameInterface;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;

public class InspectDoorActionTest
{

	@Test
	public void perform() throws Exception
	{
		Lock          lock          = new MockLock(Lock.State.UNLOCKED);
		Room          a             = new SomeRoom();
		Room          b             = new SomeRoom();
		Door          door          = new BaseDoor(Door.State.OPEN, lock, a, b);
		Character     character     = new SomeCharacter();
		GameInterface gameInterface = new SomeGameInterface();

		assertEquals(Door.State.OPEN, door.getState());
		ActionPerformCallback<InspectDoorAction> mockConsumer = Mockito.mock(ActionPerformCallback.class);
		InspectDoorAction                        action       = new InspectDoorAction(door, mockConsumer);
		action.perform(gameInterface, character, new String[0]);
		assertFalse(action.hasException());
		assertSame(door, action.getDoor());
		Mockito.verify(mockConsumer).send(any(Character.class), any(InspectDoorAction.class));
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new InspectDoorAction(null, null));
	}
}