package textadventure.ui;

import org.junit.Test;

import static org.junit.Assert.*;

public class BaseOptionTest
{

	@Test
	public void getOptionIdentifier() throws Exception
	{
		int    identifier  = 10;
		String name        = "OptionName";
		String description = "OptionDescription";
		Option option      = new BaseOption(identifier, name, description);
		assertEquals(identifier, (long) option.getOptionIdentifier());
	}

	@Test
	public void getOptionName() throws Exception
	{
		int    identifier  = 10;
		String name        = "OptionName";
		String description = "OptionDescription";
		Option option      = new BaseOption(identifier, name, description);
		assertSame(name, option.getOptionName());
	}

	@Test
	public void getOptionDescription() throws Exception
	{
		int    identifier  = 10;
		String name        = "OptionName";
		String description = "OptionDescription";
		Option option      = new BaseOption(identifier, name, description);
		assertSame(description, option.getOptionDescription());
	}

	@Test
	public void testHashCode() throws Exception
	{
		int    identifier  = 10;
		String name        = "OptionName";
		String description = "OptionDescription";
		Option option      = new BaseOption(identifier, name, description);
		assertEquals(identifier, option.hashCode());
	}

	@Test
	public void testEquals() throws Exception
	{
		String name        = "OptionName";
		String description = "OptionDescription";

		Option a = new BaseOption(10, name, description);
		Option b = new BaseOption(10, name, description);
		Option c = new BaseOption(20, name, description);

		assertTrue(a.equals(b));
		assertTrue(b.equals(a));
		assertFalse(a.equals(c));
		assertFalse(b.equals(c));
	}
}