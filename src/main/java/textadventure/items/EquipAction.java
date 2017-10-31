package textadventure.items;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import textadventure.characters.Character;
import textadventure.Equipable;
import textadventure.actions.AbstractAction;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.backpack.Backpack;
import textadventure.items.backpack.DropItemAction;
import textadventure.items.chest.TakeItemFromChestAction;
import textadventure.items.wearables.Wearable;
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
			Select<Equipable> select = new BaseSelect<>(options, 1, selection -> {
				Option<Equipable> equipable = selection.get(0);
				System.out.println(equipable.getT());

			});

			gameInterface.select(character, select);

		} catch (Exception e) {
			setException(e);
		}
	}
}
