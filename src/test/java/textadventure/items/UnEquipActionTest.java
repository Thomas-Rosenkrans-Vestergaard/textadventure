package textadventure.items;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.UnEquipAction;
import textadventure.items.backpack.Backpack;
import textadventure.items.wearables.HeadWear;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Room;

import java.awt.*;

import static org.junit.Assert.*;

public class UnEquipActionTest
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

		assertEquals(0, backpack.getNumberOfItems());
		assertSame(headWear, character.getHeadWear());

		UnEquipAction action = new UnEquipAction();
		action.perform(character, new SomeActionResponses()
		{
			@Override public void onUnEquipAction(Character character, UnEquipAction action)
			{
				assertFalse(action.hasException());
				assertEquals(null, character.getHeadWear());
				assertEquals(1, backpack.getNumberOfItems());
			}
		});
	}
}