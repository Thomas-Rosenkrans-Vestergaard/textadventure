package textadventure;

public class UnknownPropertyException extends GameException
{

	private Class<? extends Property> propertyContainer;
	private Class<? extends Property> propertyType;

	public UnknownPropertyException(Class<? extends Property> propertyContainer, Class<? extends Property> propertyType)
	{
		this.propertyContainer = propertyContainer;
		this.propertyType = propertyType;
	}

	public Class<? extends Property> getPropertyContainer()
	{
		return this.propertyContainer;
	}

	public Class<? extends Property> getPropertyType()
	{
		return this.propertyType;
	}
}
