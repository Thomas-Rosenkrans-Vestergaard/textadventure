package textadventure.characters;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionResponses;
import textadventure.items.EquipableItem;
import textadventure.items.NotEquipableException;
import textadventure.items.weapons.Fists;
import textadventure.items.weapons.Weapon;
import textadventure.items.wearables.*;
import textadventure.ui.*;

import java.util.ArrayList;
import java.util.List;

public class UnEquipAction extends AbstractAction
{

	/**
	 * The {@link EquipableItem}s the were unequipped during the {@link UnEquipAction}.
	 */
	private List<EquipableItem> items = new ArrayList<>();

	/**
	 * Performs the {@link UnEquipAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link UnEquipAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link UnEquipAction}.
	 */
	public void perform(Character character, ActionResponses responses)
	{
		try {

			ImmutableSet<Option<EquipableItem>> options = getOptions(character);
			Select<EquipableItem> select = new BaseSelect<>(options, selection -> {

				for (Option<EquipableItem> equipableOption : selection) {
					EquipableItem equipable = equipableOption.getT();

					if (equipable instanceof HeadWear) {
						HeadWear current = character.getHeadWear();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setHeadWear(null);
						}
						continue;
					}

					if (equipable instanceof TorsoWear) {
						TorsoWear current = character.getTorsoWear();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setTorsoWear(null);
						}
						continue;
					}

					if (equipable instanceof Gloves) {
						Gloves current = character.getGloves();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setGloves(null);
						}
						continue;
					}

					if (equipable instanceof Pants) {
						Pants current = character.getPants();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setPants(null);
						}
						continue;
					}

					if (equipable instanceof Boots) {
						Boots current = character.getBoots();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setBoots(null);
						}
						continue;
					}
					if (equipable instanceof Weapon) {
						Weapon current = character.getWeapon();
						if (current != null) {
							character.getBackpack().addItem(current);
							character.setWeapon(new Fists());
						}
						continue;
					}

					throw new UnsupportedOperationException();
				}
			});

			responses.select(select);

		} catch (SelectResponseException e) {
			setException(e.getCause());
		} catch (NotEnoughOptionsException e) {
			setException(new NotEquipableException());
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onUnEquipAction(character, this);
		}
	}

	/**
	 * Returns the {@link Option}s of {@link Wearable}s that can be unequipped.
	 *
	 * @param character The {@link Character} to return {@link Wearable} {@link Option}s from.
	 * @return The {@link Option}s of {@link Wearable}s that can be unequipped.
	 */
	private ImmutableSet<Option<EquipableItem>> getOptions(Character character)
	{
		int                                         index   = 0;
		ImmutableSet.Builder<Option<EquipableItem>> builder = new ImmutableSet.Builder<>();

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

	/**
	 * Returns the {@link EquipableItem}s the were unequipped during the {@link UnEquipAction}.
	 *
	 * @return The {@link EquipableItem}s the were unequipped during the {@link UnEquipAction}.
	 */
	public List<EquipableItem> getItems()
	{
		return this.items;
	}
}
