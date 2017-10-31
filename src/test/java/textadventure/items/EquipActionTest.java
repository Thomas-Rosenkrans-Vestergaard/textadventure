package textadventure.items;

import org.junit.Test;
import textadventure.SomeCharacter;
import textadventure.characters.Character;
import textadventure.items.backpack.Backpack;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;
import textadventure.ui.Select;
import textadventure.ui.SomeGameInterface;

import static org.junit.Assert.*;

public class EquipActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack  backpack        = new Backpack();
		Item      item            = new SomeItem();
		Room      currentLocation = new BaseRoom(null, null);
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

		assertEquals(0, backpack.getNumberOfItems());
		assertEquals(1, character.getHeadWear());

		EquipAction action = new EquipAction((characterResponse, actionResponse) -> {
			assertFalse(actionResponse.hasException());
			assertEquals(0, character.getHeadWear() );
			assertEquals(1, backpack.getNumberOfItems());


			try {
				assertSame(item, backpack.getItem(0));
			} catch (Exception e) {
				fail();
			}
		});

		action.perform(gameInterface, character, new String[0]);
		}

	}
