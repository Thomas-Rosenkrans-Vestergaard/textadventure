package textadventure.ui;

public interface Selectable
{

	/**
	 * Returns the identifier of the {@link Selectable} item. The identifier is used when selecting the
	 * {@link Selectable} item from the {@link UI}.
	 *
	 * @return The identifier of the {@link Selectable} item. The identifier is used when selecting the
	 * {@link Selectable} item from the {@link UI}.
	 */
	String getIdentifier();
}
