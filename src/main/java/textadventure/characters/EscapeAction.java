package textadventure.characters;

import textadventure.actions.AbstractAction;
import textadventure.actions.ActionPerformCallback;
import textadventure.combat.Escapees;
import textadventure.combat.Faction;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;

/**
 * Allows a {@link Character} from the {@link textadventure.combat.Escapees} to escape the {@link textadventure.Game}.
 */
public class EscapeAction extends AbstractAction
{

	/**
	 * The {@link ActionPerformCallback} to invoke after performing the {@link EscapeAction}.
	 */
	private ActionPerformCallback<EscapeAction> callback;

	/**
	 * Creates a new {@link EscapeAction}.
	 *
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link EscapeAction}.
	 */
	public EscapeAction(ActionPerformCallback<EscapeAction> callback)
	{
		this.callback = callback;
	}

	/**
	 * Performs the {@link EscapeAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link EscapeAction}.
	 * @param arguments     The arguments provided to the {@link EscapeAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Faction faction = character.getFaction();

		if (faction instanceof Escapees) {

			Escapees escapees   = (Escapees) faction;
			Room     endingRoom = escapees.getEndingRoom();

			if (endingRoom != character.getCurrentLocation()) {
				setException(new CharacterCantEscapeException());
				callback.send(character, this);
				return;
			}

			escapees.addEscapee(character);
			callback.send(character, this);
			return;
		}

		setException(new CharacterCantEscapeException());
		callback.send(character, this);
	}
}
