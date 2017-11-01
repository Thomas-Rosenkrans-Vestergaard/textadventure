package textadventure.combat;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.items.weapons.Weapon;
import textadventure.rooms.Room;
import textadventure.ui.*;

import java.util.HashMap;
import java.util.Map;

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
	 * The damage done to the target {@link Character}(s).
	 */
	private Map<Character, Integer> damageDone;

	/**
	 * Resets the {@link AttackAction} to its default state.
	 */
	@Override public void reset()
	{
		this.exception = null;
		this.target = null;
		this.damageDone = new HashMap<>();
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
			setException(new FriendlyTargetException(character, target));
			responses.onAttackAction(character, this);
			return;
		}

		try {

			Weapon                          weapon  = character.getWeapon();
			ImmutableSet<Option<Character>> options = getOptions(currentLocation, faction);

			if (options.size() == 0)
				throw new NoTargetsException();

			Select<Character> select = new BaseSelect<>(options, 1, weapon.getNumberOfTargets(), selection -> {
				for (Option<Character> characterOption : selection) {
					Character characterT = characterOption.getT();
					this.damageDone.put(characterT, characterT.takeDamage(weapon));
					characterT.getFaction().getLeader().onCharacterAttacked(characterT, this);
				}
			});

			character.getFaction().getLeader().select(select);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onAttackAction(character, this);
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
	 * Returns the amount of damage done to the {@link Character}s.
	 *
	 * @return The amount of damage done to the {@link Character}s.
	 */
	public ImmutableMap<Character, Integer> getDamageDone()
	{
		return ImmutableMap.copyOf(damageDone);
	}
}
