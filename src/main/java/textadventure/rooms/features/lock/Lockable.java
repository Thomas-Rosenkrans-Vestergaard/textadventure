package textadventure.rooms.features.lock;

/**
 * Something that has a {@link Lock}.
 */
public interface Lockable
{

	/**
	 * Returns the {@link Lock} on the {@link Lockable} object.
	 *
	 * @return The {@link Lock} on the {@link Lockable} object.
	 */
	Lock getLock();
}
