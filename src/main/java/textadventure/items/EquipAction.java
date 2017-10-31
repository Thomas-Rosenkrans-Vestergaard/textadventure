package textadventure.items;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.ui.*;

import java.util.ArrayList;
import java.util.List;


public class EquipAction extends AbstractAction
{

	/**
	 * The {@link EquipableItem}s equipped during the {@link EquipAction}.
	 */
	private ArrayList<EquipableItem> items;

	/**
	 * Resets the {@link EquipAction} to its default state.
	 */
	@Override public void reset()
	{
		this.exception = null;
		this.items = new ArrayList<>();
	}

	/**
	 * Performs the {@link EquipAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link EquipAction}.
	 * @param arguments The arguments provided to the {@link EquipAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link EquipAction}.
	 */
	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		Backpack backpack = character.getBackpack();

		try {

			ImmutableSet<Option<EquipableItem>> options = backpack.asOptions(EquipableItem.class);
			Select<EquipableItem> select = new BaseSelect<>(options, selection -> {

				for (Option<EquipableItem> equipableOption : selection) {
					EquipableItem equipable = equipableOption.getT();
					int           index     = equipableOption.getOptionIndex();

					if (equipable instanceof HeadWear) {
						HeadWear current = character.getHeadWear();
						character.setHeadWear((HeadWear) equipable);
						this.items.add(equipable);
						character.getBackpack().takeItem(index);
						if (current != null) {
							character.getBackpack().addItem(current);
						}
						continue;
					}

					if (equipable instanceof TorsoWear) {
						TorsoWear current = character.getTorsoWear();
						character.setTorsoWear((TorsoWear) equipable);
						this.items.add(equipable);
						character.getBackpack().takeItem(index);
						if (current != null) {
							character.getBackpack().addItem(current);
						}
						continue;
					}

					if (equipable instanceof Gloves) {
						Gloves current = character.getGloves();
						character.setGloves((Gloves) equipable);
						this.items.add(equipable);
						character.getBackpack().takeItem(index);
						if (current != null) {
							character.getBackpack().addItem(current);
						}
						continue;
					}

					if (equipable instanceof Pants) {
						Pants current = character.getPants();
						character.setPants((Pants) equipable);
						this.items.add(equipable);
						character.getBackpack().takeItem(index);
						if (current != null) {
							character.getBackpack().addItem(current);
						}
						continue;
					}

					if (equipable instanceof Boots) {
						Boots current = character.getBoots();
						character.setBoots((Boots) equipable);
						this.items.add(equipable);
						character.getBackpack().takeItem(index);
						if (current != null) {
							character.getBackpack().addItem(current);
						}
						continue;
					}

					if (equipable instanceof Weapon) {
						Weapon current = character.getWeapon();
						character.setWeapon((Weapon) equipable);
						this.items.add(equipable);
						character.getBackpack().takeItem(index);
						if (current != null) {
							character.getBackpack().addItem(current);
						}
						continue;
					}

					throw new UnsupportedOperationException();
				}
			});

			if (arguments.length > 0) {
				List<Integer> indices = new ArrayList<>();
				for (String argument : arguments) indices.add(Integer.parseInt(argument));
				select.selectIndices(indices);
				return;
			}

			character.getFaction().getLeader().select(select);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (NotEnoughOptionsException e) {
			setException(new NotEquipableException());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onEquipAction(character, this);
		}
	}

	/**
	 * Returns the {@link EquipableItem}s equipped during the {@link EquipAction}.
	 *
	 * @return The {@link EquipableItem}s equipped during the {@link EquipAction}.
	 */
	public ArrayList<EquipableItem> getItems()
	{
		return this.items;
	}
}
