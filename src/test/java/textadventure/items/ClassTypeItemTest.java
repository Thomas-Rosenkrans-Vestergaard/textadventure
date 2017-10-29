package textadventure.items;

import org.junit.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;

public class ClassTypeItemTest
{
	@Test
	public void of() throws Exception
	{
		Class<?>      expected = Integer.class;
		ClassTypeItem typeItem = ClassTypeItem.of(expected);
		assertSame(expected, typeItem.getItemTypeClass());
	}

	@Test
	public void getItemTypeClass() throws Exception
	{
		Class<?>      expected = Integer.class;
		ClassTypeItem typeItem = ClassTypeItem.of(expected);
		assertSame(expected, typeItem.getItemTypeClass());
	}

	@Test
	public void getItemTypeName() throws Exception
	{
		Class<?>      expected = Integer.class;
		ClassTypeItem typeItem = ClassTypeItem.of(expected);
		assertSame(expected, typeItem.getItemTypeClass());
		assertNotEquals(-1, typeItem.getItemTypeName().indexOf(expected.getSimpleName()));
	}

	@Test
	public void getItemTypeDescription() throws Exception
	{
		Class<?>      expected = Integer.class;
		ClassTypeItem typeItem = ClassTypeItem.of(expected);
		assertSame(expected, typeItem.getItemTypeClass());
		assertNotEquals(-1, typeItem.getItemTypeDescription().indexOf(expected.getSimpleName()));
	}
}