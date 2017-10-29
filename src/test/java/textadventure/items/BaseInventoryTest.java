package textadventure.items;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import textadventure.items.inventory.MockItem;
import textadventure.ui.Option;

import java.util.Stack;

import static org.junit.Assert.*;

public class BaseInventoryTest
{

	private class ItemA extends AbstractItem
	{
		public ItemA()
		{
			super(null, null);
		}

		@Override public String getItemName()
		{
			return null;
		}

		@Override public String getItemDescription()
		{
			return null;
		}
	}

	private class ItemB extends AbstractItem
	{
		public ItemB()
		{
			super(null, null);
		}

		@Override public String getItemName()
		{
			return null;
		}

		@Override public String getItemDescription()
		{
			return null;
		}
	}

	@Test
	public void addItem() throws Exception
	{
		BaseInventory inventory = new BaseInventory(5);
		assertEquals(0, inventory.getNumberOfItems());
		inventory.addItem(new MockItem());
		assertEquals(1, inventory.getNumberOfItems());
	}

	@Test(expected = InventoryFullException.class)
	public void addItemThrowsInventoryFullException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.addItem(new ItemA());
		inventory.addItem(new ItemB());
	}

	@Test
	public void addItemPosition() throws Exception
	{
		BaseInventory inventory = new BaseInventory(5);
		assertEquals(0, inventory.getNumberOfItems(0));
		inventory.addItem(new MockItem(), 0);
		assertEquals(1, inventory.getNumberOfItems(0));
	}


	@Test(expected = PositionRangeException.class)
	public void addItemPositionThrowsPositionRangeException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.addItem(new MockItem(), 5);
	}

	@Test(expected = TypeMismatchException.class)
	public void addItemTypeMismatchException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.addItem(new ItemA(), 0);
		inventory.addItem(new ItemB(), 0);
	}

	@Test
	public void takeItem() throws Exception
	{
		Item          a         = new MockItem();
		Item          b         = new MockItem();
		BaseInventory inventory = new BaseInventory(5);
		inventory.addItem(a, 0);
		inventory.addItem(b, 0);
		assertSame(b, inventory.takeItem(0));
		assertSame(a, inventory.takeItem(0));
	}

	@Test(expected = PositionRangeException.class)
	public void takeItemThrowsPositionRangeException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.takeItem(5);
	}

	@Test(expected = EmptyPositionException.class)
	public void takeItemThrowsEmptyPositionException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.takeItem(0);
	}

	@Test
	public void takeItemType() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		ItemB         input     = new ItemB();
		inventory.addItem(input, 0);
		ItemB output = inventory.takeItem(0, ItemB.class);
		assertTrue(ItemB.class.isInstance(output));
		assertSame(input, output);
	}

	@Test(expected = PositionRangeException.class)
	public void takeItemTypeThrowsPositionRangeException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.takeItem(5, ItemB.class);
	}

	@Test(expected = EmptyPositionException.class)
	public void takeItemTypeThrowsEmptyPositionException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.takeItem(0, ItemA.class);
	}

	@Test(expected = TypeMismatchException.class)
	public void testItemTypeTypeMismatchException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.addItem(new ItemA(), 0);
		inventory.takeItem(0, ItemB.class);
	}

	@Test
	public void takeItems() throws Exception
	{
		Item a = new MockItem();
		Item b = new MockItem();
		Item c = new MockItem();

		BaseInventory inventory = new BaseInventory(1);

		inventory.addItem(a, 0);
		inventory.addItem(b, 0);
		inventory.addItem(c, 0);

		assertEquals(3, inventory.getNumberOfItems());

		Stack<Item> output = inventory.takeItems(0, 3);

		assertEquals(0, inventory.getNumberOfItems());

		assertSame(a, output.get(2));
		assertSame(b, output.get(1));
		assertSame(c, output.get(0));
	}

	@Test(expected = PositionRangeException.class)
	public void takeItemsThrowsPositionRangeException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.takeItems(5, 10);
	}

	@Test(expected = EmptyPositionException.class)
	public void takeItemsThrowsEmptyPositionException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.takeItems(0, 10);
	}

	@Test(expected = NotEnoughItemsException.class)
	public void takeItemsThrowsNotEnoughItemsException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.addItem(new MockItem(), 0);
		inventory.takeItems(0, 10);
	}

	@Test
	public void takeItemsType() throws Exception
	{
		Item a = new ItemA();
		Item b = new ItemA();
		Item c = new ItemA();

		BaseInventory inventory = new BaseInventory(1);

		inventory.addItem(a, 0);
		inventory.addItem(b, 0);
		inventory.addItem(c, 0);

		assertEquals(3, inventory.getNumberOfItems());

		Stack<ItemA> output = inventory.takeItems(0, 3, ItemA.class);

		assertEquals(0, inventory.getNumberOfItems());

		assertSame(a, output.get(2));
		assertSame(b, output.get(1));
		assertSame(c, output.get(0));
	}

	@Test(expected = PositionRangeException.class)
	public void takeItemsTypeThrowsPositionRangeException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.takeItems(5, 10, ItemA.class);
	}

	@Test(expected = EmptyPositionException.class)
	public void takeItemsTypeThrowsEmptyPositionException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.takeItems(0, 10, Item.class);
	}

	@Test(expected = NotEnoughItemsException.class)
	public void takeItemsTypeThrowsNotEnoughItemsException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.addItem(new ItemA(), 0);
		inventory.takeItems(0, 10, ItemA.class);
	}

	@Test
	public void getItem() throws Exception
	{
		Inventory inventory = new BaseInventory(1);
		Item      input     = new MockItem();
		assertEquals(0, inventory.getNumberOfItems());
		inventory.addItem(input, 0);
		assertEquals(1, inventory.getNumberOfItems());
		Item output = inventory.getItem(0);
		assertEquals(1, inventory.getNumberOfItems());
		assertSame(input, output);
	}

	@Test(expected = PositionRangeException.class)
	public void getItemThrowsPositionRangeException() throws Exception
	{
		Inventory inventory = new BaseInventory(1);
		inventory.getItem(2);
	}

	@Test(expected = EmptyPositionException.class)
	public void getItemThrowsEmptyPositionException() throws Exception
	{
		Inventory inventory = new BaseInventory(3);
		inventory.getItem(0);
	}

	@Test
	public void getItemType() throws Exception
	{
		Inventory inventory = new BaseInventory(1);
		ItemA     input     = new ItemA();
		inventory.addItem(input, 0);
		assertEquals(1, inventory.getNumberOfItems());
		ItemA output = inventory.getItem(0, ItemA.class);
		assertEquals(1, inventory.getNumberOfItems());
		assertTrue(output instanceof ItemA);
		assertSame(input, output);
	}

	@Test(expected = PositionRangeException.class)
	public void getItemTypeThrowsPositionRangeException() throws Exception
	{
		Inventory inventory = new BaseInventory(1);
		inventory.getItem(2, ItemA.class);
	}

	@Test(expected = EmptyPositionException.class)
	public void getItemTypeThrowsEmptyPositionException() throws Exception
	{
		Inventory inventory = new BaseInventory(3);
		inventory.getItem(0, ItemA.class);
	}

	@Test(expected = TypeMismatchException.class)
	public void getItemTypeThrowsTypeMismatchException() throws Exception
	{
		Inventory inventory = new BaseInventory(1);
		inventory.addItem(new ItemA(), 0);
		inventory.getItem(0, ItemB.class);
	}

	@Test
	public void getItems() throws Exception
	{
		Item a = new MockItem();
		Item b = new MockItem();
		Item c = new MockItem();

		BaseInventory inventory = new BaseInventory(1);

		inventory.addItem(a, 0);
		inventory.addItem(b, 0);
		inventory.addItem(c, 0);

		assertEquals(3, inventory.getNumberOfItems());

		Stack<Item> output = inventory.getItems(0, 3);

		assertEquals(3, inventory.getNumberOfItems());

		assertSame(a, output.get(2));
		assertSame(b, output.get(1));
		assertSame(c, output.get(0));
	}

	@Test(expected = PositionRangeException.class)
	public void getItemsThrowsPositionRangeException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.getItems(5, 10);
	}

	@Test(expected = EmptyPositionException.class)
	public void getItemsThrowsEmptyPositionException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.getItems(0, 10);
	}

	@Test(expected = NotEnoughItemsException.class)
	public void getItemsThrowsNotEnoughItemsException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.addItem(new MockItem(), 0);
		inventory.getItems(0, 10);
	}

	@Test
	public void getItemsType() throws Exception
	{
		Item a = new ItemA();
		Item b = new ItemA();
		Item c = new ItemA();

		BaseInventory inventory = new BaseInventory(1);

		inventory.addItem(a, 0);
		inventory.addItem(b, 0);
		inventory.addItem(c, 0);

		assertEquals(3, inventory.getNumberOfItems());

		Stack<ItemA> output = inventory.getItems(0, 3, ItemA.class);

		assertEquals(3, inventory.getNumberOfItems());

		assertSame(a, output.get(2));
		assertSame(b, output.get(1));
		assertSame(c, output.get(0));
	}

	@Test(expected = PositionRangeException.class)
	public void getItemsTypeThrowsPositionRangeException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.getItems(5, 10, ItemA.class);
	}

	@Test(expected = EmptyPositionException.class)
	public void getItemsTypeThrowsEmptyPositionException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.getItems(0, 10, Item.class);
	}

	@Test(expected = NotEnoughItemsException.class)
	public void getItemsTypeThrowsNotEnoughItemsException() throws Exception
	{
		BaseInventory inventory = new BaseInventory(1);
		inventory.addItem(new ItemA(), 0);
		inventory.getItems(0, 10, ItemA.class);
	}

	@Test
	public void getNumberOfItems() throws Exception
	{
		Inventory inventory = new BaseInventory(3);
		assertEquals(0, inventory.getNumberOfItems());
		inventory.addItem(new MockItem());
		assertEquals(1, inventory.getNumberOfItems());
		inventory.addItem(new MockItem(), 1);
		assertEquals(2, inventory.getNumberOfItems());
	}

	@Test
	public void getNumberOfItemsPosition() throws Exception
	{
		Inventory inventory = new BaseInventory(2);

		assertEquals(0, inventory.getNumberOfItems(0));
		assertEquals(0, inventory.getNumberOfItems(1));

		inventory.addItem(new MockItem(), 0);
		inventory.addItem(new MockItem(), 1);

		assertEquals(1, inventory.getNumberOfItems(0));
		assertEquals(1, inventory.getNumberOfItems(1));
	}

	@Test(expected = PositionRangeException.class)
	public void getNumberOfItemsPositionThrowsPositionRangeException() throws Exception
	{
		Inventory inventory = new BaseInventory(1);
		inventory.getNumberOfItems(5);
	}

	@Test
	public void getPositionAmounts() throws Exception
	{
		Inventory inventory = new BaseInventory(2);

		inventory.addItem(new MockItem(), 0);
		inventory.addItem(new MockItem(), 0);
		inventory.addItem(new MockItem(), 1);

		ImmutableMap<Integer, Integer> output = inventory.getPositionAmounts();

		assertEquals(2, output.size());
		assertEquals(2, (long) output.get(0));
		assertEquals(1, (long) output.get(1));
	}

	@Test
	public void getNumberOfPositions() throws Exception
	{
		Inventory inventory = new BaseInventory(5);
		assertEquals(5, inventory.getNumberOfPositions());
	}

	@Test
	public void getNumberOfEmptyPositions() throws Exception
	{
		Inventory inventory = new BaseInventory(5);
		assertEquals(5, inventory.getNumberOfEmptyPositions());
		inventory.addItem(new MockItem());
		assertEquals(4, inventory.getNumberOfEmptyPositions());
	}

	@Test
	public void getNumberOfNonEmptyPositions() throws Exception
	{
		Inventory inventory = new BaseInventory(5);
		assertEquals(0, inventory.getNumberOfNonEmptyPositions());
		inventory.addItem(new MockItem());
		assertEquals(1, inventory.getNumberOfNonEmptyPositions());
	}

	@Test
	public void getPositions() throws Exception
	{
		Inventory inventory = new BaseInventory(5);

		MockItem a = new MockItem();
		MockItem b = new MockItem();

		inventory.addItem(a, 0);
		inventory.addItem(b, 1);

		ImmutableMap<Integer, ItemType> positions = inventory.getPositions();

		assertEquals(2, positions.size());
		assertTrue(a.equals(positions.get(0)));
		assertTrue(b.equals(positions.get(1)));
	}

	@Test
	public void expand() throws Exception
	{
		BaseInventory inventory = new BaseInventory(2);
		assertEquals(2, inventory.getNumberOfPositions());
		inventory.expand(2);
		assertEquals(4, inventory.getNumberOfPositions());
	}

	@Test
	public void asOptions() throws Exception
	{
		Inventory inventory = new BaseInventory(5);

		Item a = new MockItem();
		Item b = new MockItem();

		inventory.addItem(a, 0);
		inventory.addItem(b, 1);

		ImmutableSet<Option<Item>> options = inventory.asOptions();
		assertEquals(2, options.size());
		options.removeIf(option -> option.getT() != a && option.getT() != b);
		assertEquals(2, options.size());
	}
}