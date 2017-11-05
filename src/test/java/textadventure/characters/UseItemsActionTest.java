package textadventure.characters;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.SomePlayer;
import textadventure.actions.ActionResponses;
import textadventure.actions.SomeActionResponses;
import textadventure.combat.SomeFaction;
import textadventure.items.NoUsesLeftException;
import textadventure.items.UsableItem;
import textadventure.items.UseTimeException;
import textadventure.items.backpack.Backpack;
import textadventure.rooms.SomeRoom;
import textadventure.select.*;

import static org.mockito.Matchers.same;

public class UseItemsActionTest
{
	@Test
	public void perform() throws Exception
	{
		UsableItem usableItem = new UsableItem()
		{
			@Override public void use(Character character) throws NoUsesLeftException, UseTimeException
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

		UsableItem  mockUsableItem = Mockito.spy(usableItem);
		SomeFaction faction        = new SomeFaction();
		faction.setPlayer(new SomePlayer());
		SomeCharacter character = new SomeCharacter();
		character.setFaction(faction);
		Backpack backpack = new Backpack();
		character.setCurrentLocation(new SomeRoom());
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

			@Override public void onUseItemsAction(Character character, UseItemsAction action)
			{

			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onUseItemsAction(same(character), same(action));
		Mockito.verify(mockUsableItem).use(same(character));
	}

	@Test
	public void getItems() throws Exception
	{
	}
}