package textadventure.combat;

import textadventure.Character;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionPerformCallback;
import textadventure.lock.LockLockAction;
import textadventure.ui.GameInterface;

public class AttackAction extends AbstractAction
{

	/**
	 * The target of the {@link AttackAction}.
	 */
	private Character target;

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link LockLockAction}.
	 */
	private ActionPerformCallback<AttackAction> callback;

	/**
	 * Creates a new {@link AttackAction}.
	 *
	 * @param target   The target of the {@link AttackAction}.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link LockLockAction}.
	 */
	public AttackAction(Character target, ActionPerformCallback<AttackAction> callback)
	{
		this.target = target;
		this.callback = callback;
	}

	/**
	 * Performs the {@link AttackAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link AttackAction}.
	 * @param arguments     The arguments provided to the {@link AttackAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		if (target.getFaction() == character.getFaction()) {
			setException(new IncorrectTargetException(character, target));
			callback.send(character, this);
		}

		target.takeDamage(character.getWeapon());

	}
}
