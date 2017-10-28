package textadventure.items.backpack;

import com.google.common.collect.ImmutableSet;
import textadventure.Character;
import textadventure.Game;
import textadventure.actions.ActionPerformCallback;
import textadventure.items.Item;
import textadventure.ui.BaseSelect;
import textadventure.ui.GameInterface;
import textadventure.ui.Option;
import textadventure.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to expand the number of slots in their
 * {@link Backpack}.
 */
public class ExpandBackpackAction extends BackpackAction
{

	/**
	 * The {@link BackpackExpansion} used to expand the {@link Backpack}.
	 */
	private BackpackExpansion backpackExpansion;

	/**
	 * The {@link ActionPerformCallback} to invoke after performing the {@link ExpandBackpackAction}.
	 */
	private ActionPerformCallback<ExpandBackpackAction> callback;

	/**
	 * Creates a new {@link ExpandBackpackAction}.
	 *
	 * @param backpack The {@link Backpack} to expand.
	 * @param callback The {@link ActionPerformCallback} to invoke after performing the {@link ExpandBackpackAction}.
	 */
	public ExpandBackpackAction(Backpack backpack, ActionPerformCallback<ExpandBackpackAction> callback)
	{
		super(backpack);

		this.callback = callback;
	}

	/**
	 * Performs the {@link ExpandBackpackAction} using the provided arguments.
	 *
	 * @param game      The {@link Game} instance.
	 * @param character The {@link Character} performing the {@link ExpandBackpackAction}.
	 * @param arguments The arguments provided to the {@link ExpandBackpackAction}.
	 */
	@Override public void perform(Game game, Character character, String[] arguments)
	{
		GameInterface                           gameInterface = game.getGameInterface();
		Backpack                                backpack      = character.getBackpack();
		ImmutableSet<Option<BackpackExpansion>> options       = backpack.asOptions(BackpackExpansion.class);

		Select<BackpackExpansion> select = new BaseSelect<>(options, 1, selection -> {

			try {

				for (Option<BackpackExpansion> backpackExpansionOption : selection) {
					backpack.takeItem(backpackExpansionOption.getOptionIndex());
					this.backpackExpansion = backpackExpansionOption.getT();
					backpack.expand(backpackExpansion.getUpgrade());
				}

			} catch (Exception e) {
				setException(e);
			}
		});

		try {

			if (arguments.length > 0) {
				List<Integer> indices = new ArrayList<>();
				for (String argument : arguments) indices.add(Integer.parseInt(argument));
				select.selectIndices(indices);
				return;
			}

			gameInterface.select(game, character, select);
		} catch (Exception e) {
			setException(e);
		} finally {
			callback.send(game, character, this);
		}
	}

	/**
	 * Returns the {@link BackpackExpansion} used to expand the {@link Backpack}.
	 *
	 * @return The {@link BackpackExpansion} used to expand the {@link Backpack}.
	 */
	public BackpackExpansion getBackpackExpansion()
	{
		return this.backpackExpansion;
	}
}
