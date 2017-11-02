package textadventure;

import com.google.common.collect.ImmutableMap;
import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

public class AbstractPropertyContainerTest
{

	private class PropertyContainerImplementation extends AbstractPropertyContainer
	{

	}

	private class PropertyImplementation extends AbstractProperty
	{

	}

	@Test
	public void addProperty() throws Exception
	{
		PropertyContainer container = new PropertyContainerImplementation();
		Property          property  = new PropertyImplementation();
		assertNull(container.getProperty("property"));
		container.putProperty("property", property);
		assertSame(property, container.getProperty("property"));
	}

	@Test
	public void getProperty() throws Exception
	{
		PropertyContainer container = new PropertyContainerImplementation();
		Property          property  = new PropertyImplementation();
		assertNull(container.getProperty("property"));
		container.putProperty("property", property);
		assertSame(property, container.getProperty("property"));
	}

	@Test
	public void getProperties() throws Exception
	{
		PropertyContainer container = new PropertyContainerImplementation();
		Property          a         = new PropertyImplementation();
		Property          b         = new PropertyImplementation();
		Property          c         = new PropertyImplementation();
		container.putProperty("a", a);
		container.putProperty("b", b);
		container.putProperty("c", c);
		ImmutableMap<String, Property> properties = container.getProperties();
		assertSame(a, properties.get("a"));
		assertSame(b, properties.get("b"));
		assertSame(c, properties.get("c"));
	}
}