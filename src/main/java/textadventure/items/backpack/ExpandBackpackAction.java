package textadventure.items.backpack;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.ui.BaseSelect;
import textadventure.ui.Option;
import textadventure.ui.Select;
import textadventure.ui.SelectResponseException;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link textadventure.actions.Action} that allows a {@link Character} to expand the number of positions in their
 * {@link Backpack}.
 */
public class ExpandBackpackAction extends BackpackAction
{

	/**
	 * The {@link BackpackExpansion} used to expand the {@link Backpack}.
	 */
	private BackpackExpansion backpackExpansion;

	/**
	 * Creates a new {@link ExpandBackpackAction}.
	 *
	 * @param backpack The {@link Backpack} to expand.
	 */
	public ExpandBackpackAction(Backpack backpack)
	{
		super(backpack);
	}

	/**
	 * Resets the {@link ExpandBackpackAction} to its default state.
	 */
	@Override public void reset()
	{
		this.exception = null;
		this.backpackExpansion = null;
	}

	/**
	 * Performs the {@link ExpandBackpackAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link ExpandBackpackAction}.
	 * @param arguments The arguments provided to the {@link ExpandBackpackAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link ExpandBackpackAction}.
	 */
	public void perform(Character character, String[] arguments, ActionResponses responses)
	{
		Backpack                                backpack = character.getBackpack();
		ImmutableSet<Option<BackpackExpansion>> options  = backpack.asOptions(BackpackExpansion.class);

		try {

			Select<BackpackExpansion> select = new BaseSelect<>(options, 1, selection -> {
				for (Option<BackpackExpansion> backpackExpansionOption : selection) {
					backpack.takeItem(backpackExpansionOption.getOptionIndex());
					this.backpackExpansion = backpackExpansionOption.getT();
					backpack.expand(backpackExpansion.getUpgrade());
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
		} catch (Exception e) {
			setException(e);
		} finally {
			responses.onExpandBackpackAction(character, this);
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
