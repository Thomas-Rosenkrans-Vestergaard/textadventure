package textadventure.lock;

import org.junit.Test;
import textadventure.characters.Character;
import textadventure.SomeCharacter;
import textadventure.items.SomeItem;
import textadventure.items.backpack.Backpack;
import textadventure.ui.GameInterface;
import textadventure.ui.Select;
import textadventure.ui.SomeGameInterface;

import static org.junit.Assert.*;

public class LockLockActionTest
{
	@Test
	public void perform() throws Exception
	{
		GameInterface gameInterface = new SomeGameInterface()
		{
			@Override public void select(Character character, Select select)
			{
				try {
					select.selectIndex(0);
				} catch (Exception e) {
					fail();
				}
			}
		};

		Lock     lock     = new Lock("Code", Lock.State.UNLOCKED);
		Backpack backpack = new Backpack();
		backpack.addItem(new Key("Code"));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		assertEquals(Lock.State.UNLOCKED, lock.getState());
		LockLockAction action = new LockLockAction(lock, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(lock, actionResponse.getLock());
			assertFalse(actionResponse.hasException());
			assertEquals(Lock.State.LOCKED, lock.getState());
		});

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void performArgument() throws Exception
	{
		Lock     lock     = new Lock("Code", Lock.State.UNLOCKED);
		Backpack backpack = new Backpack();
		backpack.addItem(new Key("Code"));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		assertEquals(Lock.State.UNLOCKED, lock.getState());
		LockLockAction action = new LockLockAction(lock, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(lock, actionResponse.getLock());
		});

		action.perform(new SomeGameInterface(), character, new String[]{"0"});
		assertEquals(Lock.State.LOCKED, lock.getState());
		assertFalse(action.hasException());
	}

	@Test
	public void performArgumentThrowsNumberFormatException() throws Exception
	{
		Lock     lock     = new Lock("Code", Lock.State.UNLOCKED);
		Backpack backpack = new Backpack();
		backpack.addItem(new Key("Code"));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		assertEquals(Lock.State.UNLOCKED, lock.getState());
		LockLockAction action = new LockLockAction(lock, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(lock, actionResponse.getLock());
		});

		action.perform(new SomeGameInterface(), character, new String[]{"NOT_INTEGER"}); // <---------
		assertEquals(Lock.State.UNLOCKED, lock.getState());
		assertTrue(action.hasException(NumberFormatException.class));
	}

	@Test
	public void performArgumentThrowsSelectionNotKey() throws Exception
	{
		Lock     lock     = new Lock("Code", Lock.State.UNLOCKED);
		Backpack backpack = new Backpack();
		backpack.addItem(new SomeItem()); // <--------
		backpack.addItem(new Key("Code")); // <-------
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		assertEquals(Lock.State.UNLOCKED, lock.getState());
		LockLockAction action = new LockLockAction(lock, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(lock, actionResponse.getLock());
		});

		action.perform(new SomeGameInterface(), character, new String[]{"0"});
		assertEquals(Lock.State.UNLOCKED, lock.getState());
		assertTrue(action.hasException(SelectionNotKeyException.class));
	}
}