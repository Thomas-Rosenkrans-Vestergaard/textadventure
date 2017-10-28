package textadventure.ui;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseOptionTest
{

	@Test
	public void getOptionIndex() throws Exception
	{
		int    expected = 10;
		Option option   = new BaseOption(expected, null, null, null);
		assertEquals(expected, (long) option.getOptionIndex());
	}

	@Test
	public void getOptionName() throws Exception
	{
		String expected = "OptionName";
		Option option   = new BaseOption(0, expected, null, null);
		assertSame(expected, option.getOptionName());
	}

	@Test
	public void getOptionDescription() throws Exception
	{
		String expected = "OptionDescription";
		Option option   = new BaseOption(0, null, expected, null);
		assertSame(expected, option.getOptionDescription());
	}

	@Test
	public void getT() throws Exception
	{
		String         expected = "T";
		Option<String> option   = new BaseOption<>(0, null, null, expected);
		assertSame(expected, option.getT());
	}

	@Test
	public void testHashCode() throws Exception
	{
		int    expected = 10;
		Option option   = new BaseOption(expected, null, null, null);
		assertEquals(expected, option.hashCode());
	}

	@Test
	public void testEquals() throws Exception
	{
		Option a = new BaseOption(10, null, null, null);
		Option b = new BaseOption(10, null, null, null);
		Option c = new BaseOption(20, null, null, null);

		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
		assertFalse(a.equals(c));
		assertFalse(b.equals(c));
	}
}