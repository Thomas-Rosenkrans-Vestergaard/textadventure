package textadventure.lock;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.items.backpack.Backpack;
import textadventure.ui.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class UnlockLockActionTest
{
	@Test
	public void perform() throws Exception
	{
		Lock     lock     = new Lock("Code", Lock.State.LOCKED);
		Backpack backpack = new Backpack();
		backpack.addItem(new Key("Code"));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		assertEquals(Lock.State.LOCKED, lock.getState());
		UnlockLockAction action = new UnlockLockAction(lock);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onLockLockAction(Character character, LockLockAction action)
			{
				assertFalse(action.hasException());
				assertEquals(Lock.State.UNLOCKED, lock.getState());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onUnlockLockAction(same(character), same(action));
	}

	@Test
	public void performThrowsLockAlreadyUnlockedException() throws Exception
	{
		Lock     lock     = new Lock("Code", Lock.State.UNLOCKED);
		Backpack backpack = new Backpack();
		backpack.addItem(new Key("Code"));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		assertEquals(Lock.State.UNLOCKED, lock.getState());
		UnlockLockAction action = new UnlockLockAction(lock);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onLockLockAction(Character character, LockLockAction action)
			{
				assertTrue(action.hasException(LockAlreadyUnlockedException.class));
				assertEquals(Lock.State.UNLOCKED, lock.getState());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onUnlockLockAction(same(character), same(action));
	}

	@Test
	public void performThrowsIncorrectKeyException() throws Exception
	{
		Lock     lock     = new Lock("Code", Lock.State.LOCKED);
		Backpack backpack = new Backpack();
		backpack.addItem(new Key("NotCode"));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);
		assertEquals(Lock.State.LOCKED, lock.getState());
		UnlockLockAction action = new UnlockLockAction(lock);

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onLockLockAction(Character character, LockLockAction action)
			{
				assertTrue(action.hasException(IncorrectKeyException.class));
				assertEquals(Lock.State.LOCKED, lock.getState());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock, Mockito.times(1)).onUnlockLockAction(same(character), same(action));
	}
}