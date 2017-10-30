package textadventure.items.backpack;

import org.junit.Test;
import textadventure.Character;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;
import textadventure.ui.GameInterface;
import textadventure.ui.Select;
import textadventure.ui.SomeGameInterface;

import static org.junit.Assert.*;

public class ExpandBackpackActionTest
{
	@Test
	public void perform() throws Exception
	{
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

		Backpack backpack = new Backpack(5);
		backpack.addItem(new BackpackExpansion(5));
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);

		assertEquals(5, backpack.getNumberOfPositions());

		ExpandBackpackAction action = new ExpandBackpackAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertFalse(actionResponse.hasException());
			assertEquals(10, backpack.getNumberOfPositions());
		});

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void getBackpackExpansion() throws Exception
	{
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

		Backpack          backpack          = new Backpack(5);
		BackpackExpansion backpackExpansion = new BackpackExpansion(5);
		backpack.addItem(backpackExpansion);
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);

		assertEquals(5, backpack.getNumberOfPositions());

		ExpandBackpackAction action = new ExpandBackpackAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(backpackExpansion, actionResponse.getBackpackExpansion());
		});

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void getBackpack() throws Exception
	{
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

		Backpack          backpack          = new Backpack(5);
		BackpackExpansion backpackExpansion = new BackpackExpansion(5);
		backpack.addItem(backpackExpansion);
		SomeCharacter character = new SomeCharacter();
		character.setBackpack(backpack);

		assertEquals(5, backpack.getNumberOfPositions());

		ExpandBackpackAction action = new ExpandBackpackAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(backpack, actionResponse.getBackpack());
		});

		action.perform(gameInterface, character, new String[0]);
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new ExpandBackpackAction(null, null));
	}
}