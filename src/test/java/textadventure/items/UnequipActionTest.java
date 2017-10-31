package textadventure.items;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.characters.Character;
import textadventure.items.backpack.Backpack;
import textadventure.items.wearables.HeadWear;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;
import textadventure.ui.Select;
import textadventure.ui.SomeGameInterface;

import java.awt.*;

import static org.junit.Assert.*;

public class UnequipActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack      backpack        = new Backpack();
		Room          currentLocation = new BaseRoom(null, null);
		SomeCharacter character       = new SomeCharacter();
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

		assertEquals(0, backpack.getNumberOfItems());
		assertSame(headWear, character.getHeadWear());


		UnequipAction action = new UnequipAction((characterResponse, actionResponse) -> {
			assertFalse(actionResponse.hasException());
			assertEquals(null, character.getHeadWear());
			assertEquals(1, backpack.getNumberOfItems());
		});

		action.perform(gameInterface, character, new String[0]);
	}
}