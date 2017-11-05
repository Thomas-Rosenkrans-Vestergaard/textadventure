
package textadventure.items.backpack;

import com.google.common.collect.ImmutableSet;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.select.BaseSelect;
import textadventure.select.Option;
import textadventure.select.Select;
import textadventure.select.SelectResponseException;

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
	 * Performs the {@link ExpandBackpackAction} using the provided arguments.
	 *
	 * @param character The {@link Character} performing the {@link ExpandBackpackAction}.
	 * @param responses The {@link ActionResponses} to invoke after performing the {@link ExpandBackpackAction}.
	 */
	public void perform(Character character, ActionResponses responses)
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

			responses.select(select);

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
