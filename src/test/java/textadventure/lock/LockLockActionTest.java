package textadventure.lock;

import org.junit.Test;
import textadventure.characters.SomeCharacter;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.items.SomeItem;
import textadventure.items.backpack.Backpack;

import static org.junit.Assert.*;

public class LockLockActionTest
{
	@Test
	public void perform() throws Exception
	{
		Lock     lock     = new Lock("Code", Lock.State.UNLOCKED);
		Backpack backpack = new Backpack();
		backpack.addItem(new Key("Code"));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		assertEquals(Lock.State.UNLOCKED, lock.getState());
		LockLockAction action = new LockLockAction(lock);

		action.perform(character, new SomeActionResponses()
		{

			@Override public void onLockLockAction(Character character, LockLockAction action)
			{
				assertFalse(action.hasException());
				assertEquals(Lock.State.LOCKED, lock.getState());
			}
		});
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
		LockLockAction action = new LockLockAction(lock);
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onLockLockAction(Character character, LockLockAction action)
			{
				assertEquals(Lock.State.LOCKED, lock.getState());
				assertFalse(action.hasException());
			}
		});
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
		LockLockAction action = new LockLockAction(lock);

		action.perform(character, new SomeActionResponses()
		{
			@Override public void onLockLockAction(Character character, LockLockAction action)
			{
				assertEquals(Lock.State.UNLOCKED, lock.getState());
				assertTrue(action.hasException(NumberFormatException.class));
			}
		});
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
		LockLockAction action = new LockLockAction(lock);

		action.perform(character, new SomeActionResponses()
		{
			@Override public void onLockLockAction(Character character, LockLockAction action)
			{
				assertEquals(Lock.State.UNLOCKED, lock.getState());
				assertTrue(action.hasException(SelectionNotKeyException.class));
			}
		});
	}
}