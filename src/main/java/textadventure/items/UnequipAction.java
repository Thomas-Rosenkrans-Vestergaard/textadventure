package textadventure.items;

import com.google.common.collect.ImmutableSet;
import textadventure.characters.Character;
import textadventure.Equipable;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.ui.*;

public class UnequipAction extends AbstractAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link UnequipAction}.
	 */
	private ActionPerformCallback<UnequipAction> callback;

	/**
	 * Creates a new {@link UnequipAction}.
	 *
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the
	 *                 {@link UnequipAction}.
	 */
	public UnequipAction(ActionPerformCallback<UnequipAction> callback)
	{
		this.callback = callback;
	}

	/**
	 * Performs the {@link UnequipAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link UnequipAction}.
	 * @param arguments     The arguments provided to the {@link UnequipAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Backpack backpack = character.getBackpack();

		try {


			ImmutableSet<Option<Equipable>> options = getOptions(character);
			Select<Equipable> select = new BaseSelect<>(options, 1, selection -> {
				Option<Equipable> equipable = selection.get(0);
				System.out.println(equipable.getT());

			});

			gameInterface.select(character, select);

		} catch (Exception e) {
			setException(e);
		}
	}

	private ImmutableSet<Option<Equipable>> getOptions(Character character)
	{
		int                                     index   = 0;
		ImmutableSet.Builder<Option<Equipable>> builder = new ImmutableSet.Builder<>();

		HeadWear headWear = character.getHeadWear();
		if (headWear != null)
			builder.add(new BaseOption<>(index++, headWear.getItemTypeName(), headWear.getItemTypeDescription(), headWear));

		TorsoWear torsoWear = character.getTorsoWear();
		if (torsoWear != null)
			builder.add(new BaseOption<>(index++, torsoWear.getItemTypeName(), torsoWear.getItemTypeDescription(), torsoWear));

		Gloves gloves = character.getGloves();
		if (gloves != null)
			builder.add(new BaseOption<>(index++, gloves.getItemTypeName(), gloves.getItemTypeDescription(), gloves));

		Pants pants = character.getPants();
		if (pants != null)
			builder.add(new BaseOption<>(index++, pants.getItemTypeName(), pants.getItemTypeDescription(), pants));

		Boots boots = character.getBoots();
		if (boots != null)
			builder.add(new BaseOption<>(index++, boots.getItemTypeName(), boots.getItemTypeDescription(), boots));

		Weapon weapon = character.getWeapon();
		if (weapon != null)
			builder.add(new BaseOption<>(index++, weapon.getItemTypeName(), weapon.getItemTypeDescription(), weapon));

		return builder.build();
	}
}
