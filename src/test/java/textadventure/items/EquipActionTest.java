package textadventure.items;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.characters.Character;
import textadventure.items.backpack.Backpack;
import textadventure.items.wearables.HeadWear;
import textadventure.items.wearables.TorsoWear;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;
import textadventure.ui.Select;
import textadventure.ui.SomeGameInterface;

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

		GameInterface gameInterface = new SomeGameInterface()
		{
			@Override public void select(Character character, Select select)
			{
				try {
					select.selectIndex(0);
				} catch (Exception e) {
					fail();
				}
			}
		};


		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(null, character.getHeadWear());

		EquipAction action = new EquipAction((characterResponse, actionResponse) -> {
			assertFalse(actionResponse.hasException());
			assertSame(headWear, character.getHeadWear());
			assertEquals(0, backpack.getNumberOfItems());
		});

		action.perform(gameInterface, character, new String[0]);
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

		EquipAction action = new EquipAction((characterResponse, actionResponse) -> {
			assertFalse(actionResponse.hasException());
			assertSame(headWear, character.getHeadWear());
			assertSame(torsoWear, character.getTorsoWear());
			assertEquals(0, backpack.getNumberOfItems());
		});

		action.perform(new SomeGameInterface(), character, new String[]{"0", "1"});
	}
}
