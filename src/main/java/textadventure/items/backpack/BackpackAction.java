package textadventure.items.backpack;

import textadventure.actions.Action;

/**
 * The base class for {@link Action}s performed on {@link Backpack}s.
 */
public abstract class BackpackAction implements Action
{

	/**
	 * The {@link Backpack} to execute {@link Action}s on.
	 */
	private Backpack backpack;

	/**
	 * Creates a new {@link BackpackAction}.
	 *
	 * @param backpack The {@link Backpack} to execute {@link Action}s on.
	 */
	BackpackAction(Backpack backpack)
	{
		this.backpack = backpack;
	}

	/**
	 * Returns the {@link Backpack} to execute {@link Action}s on.
	 *
	 * @return The {@link Backpack} to execute {@link Action}s on.
	 */
	public Backpack getBackpack()
	{
		return this.backpack;
	}
}
