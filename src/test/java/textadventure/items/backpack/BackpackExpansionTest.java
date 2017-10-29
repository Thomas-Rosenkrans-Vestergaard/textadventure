package textadventure.items.backpack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BackpackExpansionTest
{
	@Test
	public void getUpgrade() throws Exception
	{
		BackpackExpansion backpackExpansion = new BackpackExpansion(1);
		assertEquals(1, backpackExpansion.getUpgrade());
	}
}