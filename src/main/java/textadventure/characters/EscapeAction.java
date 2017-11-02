package textadventure.characters;

import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.combat.Escapees;

public class EscapeAction extends AbstractAction
{

	/**
	 * Performs the {@link EscapeAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link EscapeAction}.
	 * @param responses The {@link ActionResponses} to report to after performing the {@link EscapeAction}.
	 */
	@Override public void perform(Character character, ActionResponses responses)
	{
		if (character.getFaction() instanceof Escapees) {
			Escapees escapees = (Escapees) character.getFaction();
			if (character.getCurrentLocation() != escapees.getEndingRoom()) {
				setException(new NotEndingRoomException());
				responses.onEscapeAction(character, this);
				return;
			}

			escapees.addEscapee(character);
			responses.onEscapeAction(character, this);
			return;
		}

		setException(new NotEscapeeException());
		responses.onEscapeAction(character, this);
	}
}
