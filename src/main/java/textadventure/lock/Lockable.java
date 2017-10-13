package textadventure.lock;

public interface Lockable
{

	/**
	 * Returns the {@link Lock} on the {@link Lockable} object.
	 */
	Lock getLock();
}
