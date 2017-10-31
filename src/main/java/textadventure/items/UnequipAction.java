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
				Option<Equipable>          equipableOption = selection.get(0);
				Equipable                  equipable       = equipableOption.getT();

				try {

					if (equipable instanceof HeadWear) {

						HeadWear current = character.getHeadWear();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setHeadWear(null);
						}
						return;
					}
					if (equipable instanceof TorsoWear) {
						TorsoWear current = character.getTorsoWear();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setTorsoWear(null);
						}
						return;
					}
					if (equipable instanceof Gloves) {
						Gloves current = character.getGloves();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setGloves(null);
						}
						return;
					}
					if (equipable instanceof Pants) {
						Pants current = character.getPants();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setPants(null);
						}
						return;
					}
					if (equipable instanceof Boots) {
						Boots current = character.getBoots();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setBoots(null);
						}
						return;
					}
					if (equipable instanceof Weapon) {
						Weapon current = character.getWeapon();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setWeapon(null);
						}
						return;
					}

				} catch (Exception e) {
					setException(e);
				}
			});

			gameInterface.select(character, select);

		} catch (NotEnoughOptionsException e) {
			setException(new NotEquipableException());
		} finally {
			callback.send(character, this);
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
