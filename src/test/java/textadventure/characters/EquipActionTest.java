package textadventure.characters;

import org.junit.Test;
import textadventure.characters.SomeCharacter;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.EquipAction;
import textadventure.items.backpack.Backpack;
import textadventure.items.wearables.HeadWear;
import textadventure.items.wearables.TorsoWear;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Room;
import textadventure.ui.*;

import java.awt.*;

import static org.junit.Assert.*;

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
		action.perform(character, new SomeActionResponses()
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
		});
	}

	@Test
	public void performThrowsNotEquipableException() throws Exception
	{
		Backpack     backpack = new Backpack();
		SomeHeadWear headWear = new SomeHeadWear();
		backpack.addItem(headWear);
		SomeTorsoWear torsoWear = new SomeTorsoWear();
		backpack.addItem(torsoWear);
		Room          currentLocation = new BaseRoom(null, null);
		SomeCharacter character       = new SomeCharacter();
		character.setBackpack(backpack);
		character.setCurrentLocation(currentLocation);

		assertEquals(2, backpack.getNumberOfItems());
		assertEquals(null, character.getHeadWear());
		assertEquals(null, character.getTorsoWear());

		EquipAction action = new EquipAction();
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onEquipAction(Character character, EquipAction action)
			{
				assertFalse(action.hasException());
				assertSame(headWear, character.getHeadWear());
				assertSame(torsoWear, character.getTorsoWear());
				assertEquals(0, backpack.getNumberOfItems());
			}
		});
	}
}
