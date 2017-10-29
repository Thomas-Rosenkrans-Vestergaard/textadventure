package textadventure.items.backpack;

import org.junit.Test;
import textadventure.Character;
import textadventure.SomeCharacter;
import textadventure.actions.ActionTest;

import static org.junit.Assert.*;

public class ExpandBackpackActionTest
{
	@Test
	public void perform() throws Exception
	{
		Backpack backpack = new Backpack(5);
		backpack.addItem(new BackpackExpansion(5));
		Character character = new SomeCharacter(backpack);

		assertEquals(5, backpack.getNumberOfPositions());

		ExpandBackpackAction action = new ExpandBackpackAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertFalse(actionResponse.hasException());
			assertEquals(10, backpack.getNumberOfPositions());
		});
	}

	@Test
	public void getBackpackExpansion() throws Exception
	{
		Backpack          backpack          = new Backpack(5);
		BackpackExpansion backpackExpansion = new BackpackExpansion(5);
		backpack.addItem(backpackExpansion);
		Character character = new SomeCharacter(backpack);

		assertEquals(5, backpack.getNumberOfPositions());

		ExpandBackpackAction action = new ExpandBackpackAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(backpackExpansion, actionResponse.getBackpackExpansion());
		});
	}

	@Test
	public void getBackpack() throws Exception
	{
		Backpack          backpack          = new Backpack(5);
		BackpackExpansion backpackExpansion = new BackpackExpansion(5);
		backpack.addItem(backpackExpansion);
		Character character = new SomeCharacter(backpack);

		assertEquals(5, backpack.getNumberOfPositions());

		ExpandBackpackAction action = new ExpandBackpackAction(backpack, (characterResponse, actionResponse) -> {
			assertSame(character, characterResponse);
			assertSame(backpack, actionResponse.getBackpack());
		});
	}

	@Test
	public void testInheritedMethods() throws Exception
	{
		ActionTest.test(() -> new ExpandBackpackAction(null, null));
	}
}