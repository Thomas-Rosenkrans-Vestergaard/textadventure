package textadventure.characters;

import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.items.backpack.ExpandBackpackAction;

/**
 * {@link textadventure.actions.Action} allowing a {@link Character} to check their stats.
 */
public class CharacterInformationAction extends AbstractAction
{

	/**
	 * The {@link CharacterInformation}.
	 */
	private CharacterInformation characterInformation;

	/**
	 * Performs the {@link ExpandBackpackAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link ExpandBackpackAction}.
	 * @param arguments The arguments provided to the {@link ExpandBackpackAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link ExpandBackpackAction}.
	 */
	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		this.characterInformation = new CharacterInformation(character);
		responses.onCharacterInformationAction(character, this);
	}

	/**
	 * Returns {@link CharacterInformation}.
	 *
	 * @return The {@link CharacterInformation}.
	 */
	public CharacterInformation getCharacterInformation()
	{
		return this.characterInformation;
	}
}
