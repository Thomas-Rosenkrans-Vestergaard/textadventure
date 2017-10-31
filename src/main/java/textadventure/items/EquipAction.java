package textadventure.items;

import com.google.common.collect.ImmutableSet;
import textadventure.characters.Character;
import textadventure.Equipable;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.ui.BaseSelect;
import textadventure.ui.GameInterface;
import textadventure.ui.Option;
import textadventure.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class EquipAction extends AbstractAction
{

	/**
	 * {@link ActionPerformCallback} to invoke after performing the {@link EquipAction}.
	 */
	private ActionPerformCallback<EquipAction> callback;

	/**
	 * Creates a new {@link EquipAction}.
	 *
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the
	 *                 {@link EquipAction}.
	 */
	public EquipAction(ActionPerformCallback<EquipAction> callback)
	{
		this.callback = callback;
	}

	/**
	 * Performs the {@link EquipAction} using the provided arguments.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} performing the {@link EquipAction}.
	 * @param arguments     The arguments provided to the {@link EquipAction}.
	 */
	@Override public void perform(GameInterface gameInterface, Character character, String[] arguments)
	{
		Backpack backpack = character.getBackpack();

		try {

			ImmutableSet<Option<Equipable>> options = backpack.asOptions(Equipable.class);
			Select<Equipable> select = new BaseSelect<>(options, selection -> {

				for(Option<Equipable> equipableOption : selection) {
					Equipable equipable = equipableOption.getT();
					int       index     = equipableOption.getOptionIndex();

					try {

						if (equipable instanceof HeadWear) {
							HeadWear current = character.getHeadWear();
							character.setHeadWear((HeadWear) equipable);
							character.getBackpack().takeItem(index);
							if (current != null) {
								character.getBackpack().addItem(current);
							}
							continue;
						}

						if (equipable instanceof TorsoWear) {
							TorsoWear current = character.getTorsoWear();
							character.setTorsoWear((TorsoWear) equipable);
							character.getBackpack().takeItem(index);
							if (current != null) {
								character.getBackpack().addItem(current);
							}
							continue;
						}

						if (equipable instanceof Gloves) {
							Gloves current = character.getGloves();
							character.setGloves((Gloves) equipable);
							character.getBackpack().takeItem(index);
							if (current != null) {
								character.getBackpack().addItem(current);
							}
							continue;
						}

						if (equipable instanceof Pants) {
							Pants current = character.getPants();
							character.setPants((Pants) equipable);
							character.getBackpack().takeItem(index);
							if (current != null) {
								character.getBackpack().addItem(current);
							}
							continue;
						}

						if (equipable instanceof Boots) {
							Boots current = character.getBoots();
							character.setBoots((Boots) equipable);
							character.getBackpack().takeItem(index);
							if (current != null) {
								character.getBackpack().addItem(current);
							}
							continue;
						}

						if (equipable instanceof Weapon) {
							Weapon current = character.getWeapon();
							character.setWeapon((Weapon) equipable);
							character.getBackpack().takeItem(index);
							if (current != null) {
								character.getBackpack().addItem(current);
							}
							continue;
						}


					} catch (Exception e) {
						setException(e);
						break;
					}
				}
			});

			if (arguments.length > 0) {
				List<Integer> indices = new ArrayList<>();
				for (String argument : arguments) indices.add(Integer.parseInt(argument));
				select.selectIndices(indices);
				return;
			}

			gameInterface.select(character, select);

		} catch (Exception e) {
			setException(e);
		} finally {
			callback.send(character, this);
		}
	}
}
