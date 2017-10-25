package textadventure.items;

import org.junit.Test;
import textadventure.items.inventory.MockItem;

import java.util.Stack;

import static org.junit.Assert.*;

public class BaseInventoryTest
{

	@Test
	public void addItem() throws Exception
	{
		BaseInventory inventory = new BaseInventory(5);
		assertEquals(0, inventory.getNumberOfItems());
		inventory.addItem(new MockItem());
		assertEquals(1, inventory.getNumberOfItems());
	}

	@Test
	public void addItem1() throws Exception
	{
		BaseInventory inventory = new BaseInventory(5);
		assertEquals(0, inventory.getNumberOfItems());
		inventory.addItem(new MockItem(), 0);
		assertEquals(1, inventory.getNumberOfItems());
	}

	@Test
	public void takeItem() throws Exception
	{
		MockItem      mockItem  = new MockItem();
		BaseInventory inventory = new BaseInventory(5);
		inventory.addItem(mockItem, 0);
		assertSame(mockItem, inventory.takeItem(0));
	}

	@Test
	public void takeItem1() throws Exception
	{
		MockItem mockItemOne = new MockItem();
		MockItem mockItemTwo = new MockItem();

		BaseInventory inventory = new BaseInventory(2);
		inventory.addItem(mockItemOne, 0);
		inventory.addItem(mockItemTwo, 0);

		Stack<Item> result = inventory.takeItem(0, 2);
		assertSame(mockItemOne, result.pop());
		assertSame(mockItemTwo, result.pop());
	}

	@Test
	public void getNumberOfItems() throws Exception
	{
		BaseInventory inventory = new BaseInventory(3);
		assertEquals(0, inventory.getNumberOfItems());
		inventory.addItem(new MockItem());
		assertEquals(1, inventory.getNumberOfItems());
	}

	@Test
	public void getNumberOfItems1() throws Exception
	{
		BaseInventory inventory = new BaseInventory(2);

		assertEquals(0, inventory.getNumberOfItems(0));
		assertEquals(0, inventory.getNumberOfItems(1));

		inventory.addItem(new MockItem(), 0);
		inventory.addItem(new MockItem(), 1);

		assertEquals(1, inventory.getNumberOfItems(0));
		assertEquals(1, inventory.getNumberOfItems(1));
	}

	@Test
	public void getNumberOfSlots() throws Exception
	{
		BaseInventory inventory = new BaseInventory(2);
		assertEquals(2, inventory.getNumberOfSlots());
		inventory.expand(2);
		assertEquals(4, inventory.getNumberOfSlots());
	}

	@Test
	public void expand() throws Exception
	{
		BaseInventory inventory = new BaseInventory(2);
		assertEquals(2, inventory.getNumberOfSlots());
		inventory.expand(2);
		assertEquals(4, inventory.getNumberOfSlots());
	}
}