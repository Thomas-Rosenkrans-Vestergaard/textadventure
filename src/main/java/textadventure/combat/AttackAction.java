package textadventure.combat;

import com.google.common.collect.ImmutableSet;
import textadventure.characters.Character;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionPerformCallback;
import textadventure.lock.LockLockAction;
import textadventure.rooms.Room;
import textadventure.ui.*;

/**
 * Allows one {@link Character} to attack another.
 */
public class AttackAction extends AbstractAction
{

	/**
	 * The {@link ActionPerformCallback} to invoke after performing the {@link LockLockAction}.
	 */
	private ActionPerformCallback<AttackAction> callback;

	/**
	 * The target of the {@link AttackAction}.
	 */
	private Character target;

	/**
	 * The damage done to the target {@link Character}.
	 */
	private int damageDone;

	/**
	 * Creates a new {@link AttackAction}.
	 *
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link LockLockAction}.
	 */
	public AttackAction(ActionPerformCallback<AttackAction> callback)
	{
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
		try {
			Room                            currentLocation = character.getCurrentLocation();
			ImmutableSet<Option<Character>> attackable      = getOptions(currentLocation, character.getFaction());
			Select<Character> select = new BaseSelect<>(attackable, selection -> {
				Character target = selection.get(0).getT();
				if (target.getFaction() == character.getFaction()) {
					setException(new FriendlyTargetException(character, target));
					return;
				}

				this.damageDone = target.takeDamage(character.getWeapon());
				this.target = target;
			});

			gameInterface.select(character, select);

		} catch (Exception e) {
			setException(e);
		} finally {
			callback.send(character, this);
		}
	}

	/**
	 * Returns the attackable {@link Character}s in the provided {@link Room}. A {@link Character} is attackable if
	 * they belong to a different {@link Faction} that the one provided.
	 *
	 * @param room    The {@link Room} to find attackable {@link Character}s.
	 * @param faction The {@link Faction} the attacker belongs to.
	 * @return An {@link ImmutableSet} of {@link Option}s containing the attackable {@link Character}s.
	 */
	private ImmutableSet<Option<Character>> getOptions(Room room, Faction faction)
	{
		int                                     optionIndex = 0;
		ImmutableSet.Builder<Option<Character>> builder     = new ImmutableSet.Builder<>();
		for (Character character : room.getCharacters())
			if (character.getFaction() != faction)
				builder.add(new BaseOption<>(optionIndex++, character.getName(), "", character));

		return builder.build();
	}

	/**
	 * Returns the target of the {@link AttackAction}.
	 *
	 * @return The target of the {@link AttackAction}.
	 */
	public Character getTarget()
	{
		return this.target;
	}

	/**
	 * Returns the damage done to the target {@link Character}.
	 *
	 * @return The damage done to the target {@link Character}.
	 */
	public int getDamageDone()
	{
		return this.damageDone;
	}
}
