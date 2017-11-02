package textadventure.characters;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.SomeActionResponses;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.same;

public class CharacterInformationActionTest
{
	@Test
	public void perform() throws Exception
	{
		SomeCharacter              character = new SomeCharacter();
		CharacterInformationAction action    = new CharacterInformationAction();
		SomeActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onCharacterInformationAction(Character character, CharacterInformationAction action)
			{
			}
		};
		SomeActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onCharacterInformationAction(same(character), same(action));
	}

	@Test
	public void getCharacterInformation() throws Exception
	{
		SomeCharacter              character = new SomeCharacter();
		CharacterInformationAction action    = new CharacterInformationAction();
		SomeActionResponses actionResponses = new SomeActionResponses()
		{
			@Override public void onCharacterInformationAction(Character character, CharacterInformationAction action)
			{
				assertTrue(action.getCharacterInformation().equals(character));
			}
		};
		SomeActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onCharacterInformationAction(same(character), same(action));
	}
}