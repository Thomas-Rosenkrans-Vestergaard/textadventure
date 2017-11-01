package textadventure.actions;

import org.junit.Test;
import textadventure.characters.Character;

public class AbstractActionTest
{
	@Test
	public void testAbstractAction() throws Exception
	{
		ActionTest.test(() -> new AbstractAction()
		{
			@Override public void perform(Character character, String[] arguments, ActionResponses callback)
			{

			}
		});
	}
}
