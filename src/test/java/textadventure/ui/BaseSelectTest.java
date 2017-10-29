package textadventure.ui;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;
import textadventure.items.Item;
import textadventure.items.SomeItem;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class BaseSelectTest
{

	private Option<Item>               one;
	private Option<Item>               two;
	private Option<Item>               three;
	private ImmutableSet<Option<Item>> options;

	@Before
	public void setUp() throws Exception
	{
		ImmutableSet.Builder<Option<Item>> builder = new ImmutableSet.Builder<>();

		this.one = new BaseOption<>(0, "One", null, new SomeItem());
		this.two = new BaseOption<>(1, "Two", null, new SomeItem());
		this.three = new BaseOption<>(2, "Three", null, new SomeItem());

		builder.add(this.one);
		builder.add(this.two);
		builder.add(this.three);

		this.options = builder.build();
	}

	@Test
	public void selectIndex() throws Exception
	{
		Select select = new BaseSelect<>(options, selection -> {
			assertEquals(1, selection.size());
			assertSame(this.two, selection.get(0));
		});

		select.selectIndex(1);
	}

	@Test(expected = SelectionAmountException.class)
	public void selectIndexThrowsSelectionAmountException() throws Exception
	{
		Select select = new BaseSelect<>(options, 2, selection -> {
			assertEquals(1, selection.size());
			assertSame(this.two, selection.get(0));
		});

		select.selectIndex(1);
	}

	@Test(expected = UnknownIndexException.class)
	public void selectIndexThrowsUnknownIndexException() throws Exception
	{
		Select select = new BaseSelect<>(options, 1, selection -> {
			assertEquals(1, selection.size());
			assertSame(this.two, selection.get(0));
		});

		select.selectIndex(10);
	}

	@Test
	public void selectIndices() throws Exception
	{
		Select select = new BaseSelect<>(options, 2, selection -> {
			assertEquals(2, selection.size());
			assertSame(this.two, selection.get(0));
			assertSame(this.three, selection.get(1));
		});

		ArrayList<Integer> selection = new ArrayList<>();
		selection.add(1);
		selection.add(2);
		select.selectIndices(selection);
	}

	@Test(expected = SelectionAmountException.class)
	public void selectIndicesThrowsSelectionAmountException() throws Exception
	{
		Select select = new BaseSelect<>(options, 1, selection -> {
			assertEquals(2, selection.size());
			assertSame(this.two, selection.get(0));
			assertSame(this.three, selection.get(1));
		});

		ArrayList<Integer> selection = new ArrayList<>();
		selection.add(1);
		selection.add(2);
		select.selectIndices(selection);
	}

	@Test(expected = UnknownIndexException.class)
	public void selectIndicesThrowsUnknownIndexException() throws Exception
	{
		Select select = new BaseSelect<>(options, 2, selection -> {
			assertEquals(2, selection.size());
			assertSame(this.two, selection.get(0));
			assertSame(this.three, selection.get(1));
		});

		ArrayList<Integer> selection = new ArrayList<>();
		selection.add(1);
		selection.add(20);
		select.selectIndices(selection);
	}

	@Test
	public void selectOption() throws Exception
	{
		Select select = new BaseSelect<>(options, selection -> {
			assertEquals(1, selection.size());
			assertSame(this.two, selection.get(0));
		});

		select.selectOption(this.two);
	}

	@Test(expected = SelectionAmountException.class)
	public void selectOptionThrowsSelectionAmountException() throws Exception
	{
		Select select = new BaseSelect<>(options, 2, selection -> {
			assertEquals(2, selection.size());
			assertSame(this.two, selection.get(0));
		});

		select.selectOption(this.two);
	}

	@Test(expected = UnknownOptionException.class)
	public void selectOptionThrowsUnknownOptionException() throws Exception
	{
		Select select = new BaseSelect<>(options, selection -> {
			assertEquals(0, selection.size());
		});

		select.selectOption(new BaseOption(5, null, null, null));
	}

	@Test
	public void selectOptions() throws Exception
	{
		Select select = new BaseSelect<>(options, 2, selection -> {
			assertEquals(2, selection.size());
			assertSame(this.two, selection.get(0));
			assertSame(this.three, selection.get(1));
		});

		ArrayList<Option<Item>> selection = new ArrayList<>();
		selection.add(this.two);
		selection.add(this.three);
		select.selectOptions(selection);
	}

	@Test(expected = SelectionAmountException.class)
	public void selectOptionsThrowsSelectionAmountException() throws Exception
	{
		Select select = new BaseSelect<>(options, 3, selection -> {
			assertEquals(2, selection.size());
			assertSame(this.two, selection.get(0));
			assertSame(this.three, selection.get(1));
		});

		ArrayList<Option<Item>> selection = new ArrayList<>();
		selection.add(this.two);
		selection.add(this.three);
		select.selectOptions(selection);
	}

	@Test(expected = UnknownOptionException.class)
	public void selectOptionsThrowsUnknownOptionException() throws Exception
	{
		Select select = new BaseSelect<>(options, 2, selection -> {
			assertEquals(2, selection.size());
			assertSame(this.two, selection.get(0));
			assertSame(this.three, selection.get(1));
		});

		ArrayList<Option<Item>> selection = new ArrayList<>();
		selection.add(new BaseOption(5, null, null, null));
		selection.add(new BaseOption(10, null, null, null));
		select.selectOptions(selection);
	}

	@Test
	public void getMinimumNumberOfOptions() throws Exception
	{
		Select select;

		select = new BaseSelect<>(options, 2, null);
		assertEquals(2, select.getMinimumNumberOfOptions());

		select = new BaseSelect<>(options, null);
		assertEquals(1, select.getMinimumNumberOfOptions());

		select = new BaseSelect<>(options, 1, 2, null);
		assertEquals(1, select.getMinimumNumberOfOptions());
	}

	@Test
	public void getMaximumNumberOfOptions() throws Exception
	{
		Select select;

		select = new BaseSelect<>(options, 2, null);
		assertEquals(2, select.getMaximumNumberOfOptions());

		select = new BaseSelect<>(options, null);
		assertEquals(3, select.getMaximumNumberOfOptions());

		select = new BaseSelect<>(options, 1, 2, null);
		assertEquals(2, select.getMaximumNumberOfOptions());
	}

	@Test
	public void getOptions() throws Exception
	{
		Select select = new BaseSelect<>(options, 2, selection -> {
			assertEquals(2, selection.size());
			assertSame(this.two, selection.get(0));
			assertSame(this.three, selection.get(1));
		});

		ImmutableMap<Integer, Option<Item>> optionImmutableMap = select.getOptions();
		assertEquals(3, optionImmutableMap.size());
		assertSame(this.one, optionImmutableMap.get(0));
		assertSame(this.two, optionImmutableMap.get(1));
		assertSame(this.three, optionImmutableMap.get(2));
	}
}