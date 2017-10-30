package textadventure.characters;

import textadventure.actions.AbstractAction;
import textadventure.actions.ActionPerformCallback;
import textadventure.ui.GameInterface;

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
	 * The {@link ActionPerformCallback} to invoke after performing the {@link CharacterInformationAction}.
	 */
	private ActionPerformCallback<CharacterInformationAction> callback;

	/**
	 * Creates a new {@link CharacterInformationAction}.
	 *
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link CharacterInformationAction}.
	 */
	public CharacterInformationAction(ActionPerformCallback<CharacterInformationAction> callback)
	{
		this.callback = callback;
	}

	/**
	 * Performs the {@link CharacterInformationAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link CharacterInformationAction}.
	 * @param arguments     The arguments provided to the {@link CharacterInformationAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		this.characterInformation = new CharacterInformation(character);
		callback.send(character, this);
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
