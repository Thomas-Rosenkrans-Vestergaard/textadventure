package textadventure.items.inventory;

import org.junit.Test;
import textadventure.items.BaseInventory;
import textadventure.items.Inventory;
import textadventure.items.Item;

import static org.junit.Assert.*;

public class BaseInventoryTest
{

	@Test
	public void getItem() throws Exception
	{
		Inventory inventory = new BaseInventory(10);
		Item item = new MockItem();
		inventory.addItem(0, item);
		assertSame(item, inventory.getSlot(0));
		assertNull(inventory.getSlot(9));
	}

	@Test
	public void addItem() throws Exception
	{
		Inventory inventory = new BaseInventory(10);
		Item item = new MockItem();
		assertFalse(inventory.hasItem(0));
		inventory.addItem(0, item);
		assertTrue(inventory.hasItem(0));
	}

	@Test
	public void hasItem() throws Exception
	{
		Inventory inventory = new BaseInventory(10);
		Item item = new MockItem();
		inventory.addItem(0, item);
		assertTrue(inventory.hasItem(0));
		assertFalse(inventory.hasItem(9));
	}

	@Test
	public void removeItem() throws Exception
	{
		Inventory inventory = new BaseInventory(10);
		Item item = new MockItem();
		inventory.addItem(0, item);
		assertTrue(inventory.hasItem(0));
		inventory.removeItem(0);
		assertFalse(inventory.hasItem(0));
	}

	@Test
	public void getItems() throws Exception
	{
		Inventory inventory = new BaseInventory(10);
		Item item = new MockItem();
		inventory.addItem(0, item);
		assertSame(item, inventory.getSlots().get(0));
		assertEquals(1, inventory.getSlots().size());
	}

	@Test
	public void countSlots() throws Exception
	{
		Inventory inventory = new BaseInventory(10);
		assertEquals(10, inventory.countSlots());
	}

	@Test
	public void countNonEmptySlots() throws Exception
	{
		Inventory inventory = new BaseInventory(10);
		assertEquals(0, inventory.countNonEmptySlots());
		Item item = new MockItem();
		inventory.addItem(0, item);
		assertEquals(1, inventory.countNonEmptySlots());
	}

	@Test
	public void countEmptySlots() throws Exception
	{
		Inventory inventory = new BaseInventory(10);
		assertEquals(10, inventory.countEmptySlots());
		Item item = new MockItem();
		inventory.addItem(0, item);
		assertEquals(9, inventory.countEmptySlots());
	}
}