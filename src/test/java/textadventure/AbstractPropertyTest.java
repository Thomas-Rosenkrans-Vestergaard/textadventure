package textadventure;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import textadventure.actions.AbstractAction;
import textadventure.actions.Action;
import textadventure.characters.Character;
import textadventure.ui.GameInterface;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class AbstractPropertyTest
{

	private class PropertyImplementation extends AbstractProperty
	{

	}

	private class ActionImplementation extends AbstractAction
	{
		@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
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