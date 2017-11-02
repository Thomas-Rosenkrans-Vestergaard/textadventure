package textadventure.characters;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.SomeActionResponses;
import textadventure.items.UsableItem;
import textadventure.items.backpack.Backpack;
import textadventure.ui.*;

import static org.mockito.Matchers.same;

public class UseItemsActionTest
{
	@Test
	public void perform() throws Exception
	{
		UsableItem usableItem = new UsableItem()
		{
			@Override public void use(Character character) throws Exception
			{

			}

			@Override public int getNumberOfUsesLeft()
			{
				return 0;
			}

			@Override public String getItemTypeName()
			{
				return null;
			}

			@Override public String getItemTypeDescription()
			{
				return null;
			}
		};

		UsableItem    mockUsableItem = Mockito.spy(usableItem);
		SomeCharacter character      = new SomeCharacter();
		Backpack      backpack       = new Backpack();
		backpack.addItem(mockUsableItem);
		character.setBackpack(backpack);

		UseItemsAction action = new UseItemsAction();

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onUseItemAction(Character character, UseItemsAction action)
			{

			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onUseItemAction(same(character), same(action));
		Mockito.verify(mockUsableItem).use(same(character));
	}

	@Test
	public void getItems() throws Exception
	{
	}
}