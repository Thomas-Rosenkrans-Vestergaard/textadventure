package textadventure.doors;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.CharacterCreationTemplate;
import textadventure.lock.Lock;
import textadventure.lock.MockLock;
import textadventure.characters.InspectFloorAction;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;

import static org.junit.Assert.*;

public class UseDoorActionTest
{

	@Test
	public void perform() throws Exception
	{
		Lock                      lock                      = new MockLock(Lock.State.UNLOCKED);
		Room                      a                         = new SomeRoom();
		Room                      b                         = new SomeRoom();
		Door                      door                      = new BaseDoor(Door.State.OPEN, lock, a, b);
		CharacterCreationTemplate characterCreationTemplate = new CharacterCreationTemplate();
		characterCreationTemplate.setName("Name");
		SomeCharacter character = new SomeCharacter();
		character.setCurrentLocation(a);
		a.addCharacter(character);
		assertSame(a, character.getCurrentLocation());
		assertTrue(a.hasCharacter(character));
		assertFalse(b.hasCharacter(character));
		UseDoorAction action = new UseDoorAction(door);

		action.perform(character, new SomeActionResponses()
		{

			@Override public void onInspectFloorAction(Character character, InspectFloorAction action)
			{
				assertFalse(action.hasException());
				assertSame(b, character.getCurrentLocation());
				assertTrue(b.hasCharacter(character));
				assertFalse(a.hasCharacter(character));
			}
		});
	}

	@Test
	public void performThrowsDoorClosedException() throws Exception
	{
		Lock          lock      = new MockLock(Lock.State.UNLOCKED);
		Room          a         = new SomeRoom();
		Room          b         = new SomeRoom();
		Door          door      = new BaseDoor(Door.State.CLOSED, lock, a, b);
		SomeCharacter character = new SomeCharacter();
		character.setCurrentLocation(a);

		assertSame(a, character.getCurrentLocation());
		UseDoorAction action = new UseDoorAction(door);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onInspectFloorAction(Character character, InspectFloorAction action)
			{
				assertTrue(action.hasException(DoorClosedException.class));
				assertSame(a, character.getCurrentLocation());
			}
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new UseDoorAction(null));
	}
}