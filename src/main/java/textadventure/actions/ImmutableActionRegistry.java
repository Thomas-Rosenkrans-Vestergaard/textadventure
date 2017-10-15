package textadventure.actions;

import textadventure.Property;

import java.util.List;
import java.util.Map;

public class ImmutableActionRegistry extends MutableActionRegistry
{

	/**
	 * Creates a new empty {@link ImmutableActionRegistry}.
	 */
	public ImmutableActionRegistry()
	{
		super();
	}

	/**
	 * Creates a new {@link ImmutableActionRegistry}.
	 *
	 * @param actions The {@link Action}s to insert into the {@link ImmutableActionRegistry}.
	 */
	public ImmutableActionRegistry(Map<String, List<NamedAction>> actions)
	{
		super(actions);
	}

	/**
	 * Adds a new {@link Action} to the {@link Action}.
	 *
	 * @param property The {@link Property} to add the {@link Action} under.
	 * @param action   The {@link Action} to add.
	 */
	@Override public void addAction(String property, NamedAction action)
	{
		throw new UnsupportedOperationException();
	}
}
