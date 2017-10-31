package textadventure.doors;

import org.junit.Test;
<<<<<<< Updated upstream
=======
import textadventure.characters.BaseCharacter;
import textadventure.characters.Character;
>>>>>>> Stashed changes
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.lock.Lock;
import textadventure.lock.MockLock;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;
import textadventure.ui.GameInterface;
import textadventure.ui.SomeGameInterface;
import textadventure.ui.characterSelection.CharacterCreationTemplate;

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
		GameInterface gameInterface = new SomeGameInterface();
		SomeCharacter character     = new SomeCharacter();
		character.setCurrentLocation(a);
		a.addCharacter(character);
		assertSame(a, character.getCurrentLocation());
		assertTrue(a.hasCharacter(character));
		assertFalse(b.hasCharacter(character));
		UseDoorAction action = new UseDoorAction(door, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(door, actionResponse.getDoor());
			assertFalse(actionResponse.hasException());
			assertSame(b, character.getCurrentLocation());
			assertTrue(b.hasCharacter(character));
			assertFalse(a.hasCharacter(character));
		}));

		action.perform(gameInterface, character, new String[0]);
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
		GameInterface gameInterface = new SomeGameInterface();

		assertSame(a, character.getCurrentLocation());
		UseDoorAction action = new UseDoorAction(door, ((characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(door, actionResponse.getDoor());
			assertTrue(actionResponse.hasException(DoorClosedException.class));
			assertSame(a, character.getCurrentLocation());
		}));

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new UseDoorAction(null, null));
	}
}