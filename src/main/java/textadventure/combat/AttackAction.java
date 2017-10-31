package textadventure.combat;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.rooms.Room;
import textadventure.ui.BaseOption;
import textadventure.ui.Option;

/**
 * Allows one {@link Character} to attack another.
 */
public class AttackAction extends AbstractAction
{

	/**
	 * The target of the {@link AttackAction}.
	 */
	private Character target;

	/**
	 * The damage done to the target {@link Character}.
	 */
	private int damageDone;

	/**
	 * Resets the {@link AttackAction} to its default state.
	 */
	@Override public void reset()
	{
		this.exception = null;
		this.target = null;
		this.damageDone = 0;
	}

	/**
	 * Performs the {@link AttackAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link AttackAction}.
	 * @param arguments The arguments provided to the {@link AttackAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link AttackAction}.
	 */

	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		Faction faction         = character.getFaction();
		Room    currentLocation = character.getCurrentLocation();

		if (faction == target.getFaction()) {
			setException(new IncorrectTargetException(character, target));
			responses.onAttackAction(character, this);
			return;
		}

		ImmutableSet<Option<Character>> options = getOptions(currentLocation, faction);

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
