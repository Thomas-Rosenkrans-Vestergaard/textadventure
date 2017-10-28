package textadventure.items.backpack;

import org.junit.Test;
import textadventure.BaseCharacter;
import textadventure.Character;
import textadventure.items.inventory.MockItem;
import textadventure.rooms.BaseRoom;
import textadventure.rooms.Floor;
import textadventure.rooms.Room;

import static org.junit.Assert.*;

public class DropItemActionTest
{
	@Test
	public void perform() throws Exception
	{
		/*Backpack  backpack        = new Backpack(5);
		Room      currentLocation = new BaseRoom(null, null);
		Floor     floor           = currentLocation.getRoomFloor();
		Character character       = new BaseCharacter(null, backpack, currentLocation);

		assertEquals(1, backpack.getNumberOfItems());
		assertEquals(0, floor.getNumberOfItems());

		DropItemAction dropItemAction = new DropItemAction(backpack, (characterResponse, action) -> {

		});

		dropItemAction.perform();*/
	}

	@Test
	public void getItems() throws Exception
	{

	}

	@Test
	public void getBackpack() throws Exception
	{
		Backpack       expected       = new Backpack(10);
		DropItemAction dropItemAction = new DropItemAction(expected, null);
		assertSame(expected, dropItemAction.getBackpack());
	}

	@Test
	public void setException() throws Exception
	{
		Exception      expected       = new Exception();
		DropItemAction dropItemAction = new DropItemAction(null, null);
		dropItemAction.setException(expected);
		assertSame(expected, dropItemAction.getException());
	}

	@Test
	public void onException() throws Exception
	{
		// TODO TEST
	}

	@Test
	public void onSuccess() throws Exception
	{
		//TODO TEST
	}

	@Test
	public void getException() throws Exception
	{
		Exception expected = new Exception();

		DropItemAction dropItemAction = new DropItemAction(null, null);
		dropItemAction.setException(expected);
		assertSame(expected, dropItemAction.getException());
	}

	@Test
	public void hasException() throws Exception
	{
		DropItemAction dropItemAction = new DropItemAction(null, null);

		Exception expected = new Exception();
		dropItemAction.setException(expected);
		assertTrue(dropItemAction.hasException(Exception.class));
		assertFalse(dropItemAction.hasException(IllegalStateException.class));

		expected = new IllegalStateException();
		dropItemAction.setException(expected);
		assertTrue(dropItemAction.hasException(IllegalStateException.class));
	}
}