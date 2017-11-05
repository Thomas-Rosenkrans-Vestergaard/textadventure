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
			/**
			 * Performs the {@link Action} using the provided arguments.
			 *
			 * @param character The {@link Character} performing the {@link Action}.
			 * @param responses The {@link ActionResponses} to report to after performing the {@link Action}.
			 */
			@Override public void perform(Character character, ActionResponses responses)
			{

			}
		});
	}
}
