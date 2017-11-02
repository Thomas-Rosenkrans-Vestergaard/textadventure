package textadventure;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import textadventure.actions.AbstractAction;
import textadventure.actions.Action;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class AbstractPropertyTest
{

	private class PropertyImplementation extends AbstractProperty
	{

	}

	private class ActionImplementation extends AbstractAction
	{
		/**
		 * Performs the {@link Action} using the provided arguments.
		 *
		 * @param character The {@link Character} performing the {@link Action}.
		 * @param responses The {@link ActionResponses} to report to after performing the {@link Action}.
		 */
		@Override public void perform(Character character, ActionResponses responses)
		{

		}
	}

	@Test
	public void addAction() throws Exception
	{
		Property property = new PropertyImplementation();
		Action   action   = new ActionImplementation();
		assertNull(property.getAction("a"));
		property.addAction("a", action);
		assertSame(action, property.getAction("a"));
	}

	@Test
	public void getAction() throws Exception
	{
		Property property = new PropertyImplementation();
		Action   action   = new ActionImplementation();
		assertNull(property.getAction("a"));
		property.addAction("a", action);
		assertSame(action, property.getAction("a"));
	}

	@Test
	public void getActions() throws Exception
	{
		Property property = new PropertyImplementation();

		Action a = new ActionImplementation();
		Action b = new ActionImplementation();
		Action c = new ActionImplementation();

		property.addAction("a", a);
		property.addAction("b", b);
		property.addAction("c", c);

		ImmutableMap<String, Action> actions = property.getActions();

		assertSame(a, actions.get("a"));
		assertSame(b, actions.get("b"));
		assertSame(c, actions.get("c"));
	}
}