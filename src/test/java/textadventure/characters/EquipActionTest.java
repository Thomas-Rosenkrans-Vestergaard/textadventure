package textadventure.characters;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.SomeActionResponses;
import textadventure.items.SomeItem;
import textadventure.items.backpack.Backpack;
import textadventure.items.wearables.HeadWear;
import textadventure.items.wearables.TorsoWear;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Room;
import textadventure.ui.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.same;

public class EquipActionTest
{

	private class SomeHeadWear implements HeadWear
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
	}

	private class SomeTorsoWear implements TorsoWear
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
	}

	@Test
	public void perform() throws Exception
	{
		Backpack     backpack = new Backpack();
		SomeHeadWear headWear = new SomeHeadWear();
		backpack.addItem(headWear);
		Room          currentLocation = new BaseRoom(null, null);
		SomeCharacter character       = new SomeCharacter();
		character.setBackpack(backpack);
		character.setCurrentLocation(currentLocation);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(null, character.getHeadWear());

		EquipAction action = new EquipAction();
		SomeActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onEquipAction(Character character, EquipAction action)
			{
				assertFalse(action.hasException());
				assertSame(headWear, character.getHeadWear());
				assertEquals(0, backpack.getNumberOfItems());
			}
		};

		SomeActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onEquipAction(same(character), same(action));
	}

	@Test
	public void performThrowsNotEquipableException() throws Exception
	{
		Backpack backpack = new Backpack();
		backpack.addItem(new SomeItem());
		backpack.addItem(new SomeItem());
		Room          currentLocation = new BaseRoom(null, null);
		SomeCharacter character       = new SomeCharacter();
		character.setBackpack(backpack);
		character.setCurrentLocation(currentLocation);

		assertEquals(2, backpack.getNumberOfItems());
		assertEquals(null, character.getHeadWear());
		assertEquals(null, character.getTorsoWear());

		EquipAction action = new EquipAction();
		SomeActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				List<Integer> selection = new ArrayList<>();
				selection.add(0);
				selection.add(1);
				select.selectIndices(selection);
			}

			@Override public void onEquipAction(Character character, EquipAction action)
			{
				assertTrue(action.hasException());
				assertSame(null, character.getHeadWear());
				assertSame(null, character.getTorsoWear());
				assertEquals(2, backpack.getNumberOfItems());
			}
		};

		SomeActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onEquipAction(same(character), same(action));
	}
}
