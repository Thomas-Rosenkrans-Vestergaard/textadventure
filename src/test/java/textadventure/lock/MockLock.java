package textadventure.lock;

public class MockLock extends Lock
{

	public MockLock(State state)
	{
		super(null, state);
	}

	public MockLock(String code)
	{
		super(code, null);
	}
}
