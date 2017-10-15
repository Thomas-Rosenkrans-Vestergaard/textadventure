package textadventure;

public class BaseProperty extends AbstractProperty
{

	private String name;
	private String description;

	public BaseProperty(String name, String description)
	{
		this.name = name;
		this.description = description;
	}

	public String getPropertyName()
	{
		return this.name;
	}

	public String getPropertyDescription()
	{
		return this.description;
	}
}
