package textadventure.characters;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.items.EquipableItem;
import textadventure.items.Item;
import textadventure.items.backpack.Backpack;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.select.BaseSelect;
import textadventure.select.Option;
import textadventure.select.Select;
import textadventure.select.SelectResponseException;

import java.util.ArrayList;
import java.util.List;


public class EquipAction extends AbstractAction
{

	/**
	 * The {@link EquipableItem}s equipped during the {@link EquipAction}.
	 */
	private List<EquipableItem> items = new ArrayList<>();

	/**
	 * Performs the {@link EquipAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link EquipAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link EquipAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		try {
			Backpack                            backpack = character.getBackpack();
			ImmutableSet<Option<EquipableItem>> options  = backpack.asOptions(EquipableItem.class);
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
						if (current != null)
							if (current instanceof Item)
								character.getBackpack().addItem((Item) current);
						continue;
					}

					throw new UnsupportedOperationException();
				}
			});

			responses.select(select);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onEquipAction(character, this);
			alertOtherPlayers(character.getCurrentLocation(), character.getFaction().getLeader(),
					secondaryResponses -> responses.onEquipAction(character, this));
		}
	}

	/**
	 * Returns the {@link EquipableItem}s equipped during the {@link EquipAction}.
	 *
	 * @return The {@link EquipableItem}s equipped during the {@link EquipAction}.
	 */
	public ImmutableList<EquipableItem> getItems()
	{
		return ImmutableList.copyOf(items);
	}
}
