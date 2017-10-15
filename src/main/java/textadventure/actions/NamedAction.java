package textadventure.actions;

public interface NamedAction extends Action
{
	/**
	 * Returns the name of the {@link Action}.
	 *
	 * @return The name of the {@link Action}.
	 */
	String getActionName();

	/**
	 * Returns the description of the {@link Action}.
	 *
	 * @return The description of the {@link Action}.
	 */
	String getActionDescription();
}
