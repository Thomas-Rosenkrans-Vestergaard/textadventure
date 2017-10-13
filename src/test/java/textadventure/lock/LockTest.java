package textadventure.lock;

import org.junit.Test;

import static org.junit.Assert.*;

public class LockTest
{


	@Test
	public void getPropertyName() throws Exception
	{
		Lock lock = new Lock("a", Lock.State.LOCKED);
		assertEquals("lock", lock.getPropertyName());
	}

	@Test
	public void getCode() throws Exception
	{
		Lock lock = new Lock("a", Lock.State.LOCKED);
		assertEquals("a", lock.getCode());
	}

	@Test
	public void getState() throws Exception
	{
		Lock lock = new Lock("a", Lock.State.LOCKED);
		assertEquals(Lock.State.LOCKED, lock.getState());
	}

	@Test
	public void lock() throws Exception
	{
		Lock lock = new Lock("a", Lock.State.UNLOCKED);
		Key  key  = new Key("a");
		assertEquals(Lock.State.UNLOCKED, lock.getState());
		lock.lock(key);
		assertEquals(Lock.State.LOCKED, lock.getState());
	}

	@Test(expected = AlreadyLockedException.class)
	public void lockAlreadyLockedException() throws Exception
	{
		Lock lock = new Lock("a", Lock.State.LOCKED);
		Key  key  = new Key("a");
		lock.lock(key);
	}

	@Test(expected = IncorrectKeyException.class)
	public void lockIncorrectKeyException() throws Exception
	{
		Lock lock = new Lock("a", Lock.State.UNLOCKED);
		Key key = new Key("b");
		lock.lock(key);
	}

	@Test
	public void unlock() throws Exception
	{
		Lock lock = new Lock("a", Lock.State.LOCKED);
		Key  key  = new Key("a");
		assertEquals(Lock.State.LOCKED, lock.getState());
		lock.unlock(key);
		assertEquals(Lock.State.UNLOCKED, lock.getState());
	}

	@Test(expected = AlreadyUnlockedException.class)
	public void lockAlreadyUnlockedException() throws Exception
	{
		Lock lock = new Lock("a", Lock.State.UNLOCKED);
		Key key = new Key("a");
		lock.unlock(key);
	}

	@Test(expected = IncorrectKeyException.class)
	public void unlockIncorrectKeyException2() throws Exception
	{
		Lock lock = new Lock("a", Lock.State.LOCKED);
		Key key = new Key("b");
		lock.unlock(key);
	}
}
