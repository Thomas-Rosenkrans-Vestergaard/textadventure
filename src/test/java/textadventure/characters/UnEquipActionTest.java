package textadventure.characters;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.SomePlayer;
import textadventure.actions.ActionResponses;
import textadventure.actions.SomeActionResponses;
import textadventure.combat.SomeFaction;
import textadventure.items.InventoryFullException;
import textadventure.items.backpack.Backpack;
import textadventure.items.wearables.HeadWear;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Room;
import textadventure.select.*;

import java.awt.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class UnEquipActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack    backpack        = new Backpack();
		Room        currentLocation = new BaseRoom(null, null);
		SomeFaction faction         = new SomeFaction();
		faction.setPlayer(new SomePlayer());
		SomeCharacter character = new SomeCharacter();
		character.setFaction(faction);
		character.setBackpack(backpack);
		character.setCurrentLocation(currentLocation);
		HeadWear headWear = new HeadWear()
		{
			@Override public double getProtectiveFactor()
			{
				return 0;
			}

			@Override public Color getColor()
			{
				return null;
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
		character.setHeadWear(headWear);

		assertEquals(0, backpack.getNumberOfItems());
		assertSame(headWear, character.getHeadWear());

		UnEquipAction action = new UnEquipAction();
		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onUnEquipAction(Character character, UnEquipAction action)
			{
				assertFalse(action.hasException());
				assertEquals(null, character.getHeadWear());
				assertEquals(1, backpack.getNumberOfItems());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onUnEquipAction(same(character), same(action));
	}

	@Test
	public void performThrowsInventoryFullException() throws Exception
	{
		Backpack    backpack        = new Backpack(0);
		Room        currentLocation = new BaseRoom(null, null);
		SomeFaction faction         = new SomeFaction();
		faction.setPlayer(new SomePlayer());
		SomeCharacter character = new SomeCharacter();
		character.setFaction(faction);
		character.setBackpack(backpack);
		character.setCurrentLocation(currentLocation);
		HeadWear headWear = new HeadWear()
		{
			@Override public double getProtectiveFactor()
			{
				return 0;
			}

			@Override public Color getColor()
			{
				return null;
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

		character.setHeadWear(headWear);

		assertEquals(0, backpack.getNumberOfItems());
		assertSame(headWear, character.getHeadWear());

		UnEquipAction action = new UnEquipAction();
		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onUnEquipAction(Character character, UnEquipAction action)
			{
				assertTrue(action.hasException(InventoryFullException.class));
				assertEquals(headWear, character.getHeadWear());
				assertEquals(0, backpack.getNumberOfItems());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onUnEquipAction(same(character), same(action));
	}
}