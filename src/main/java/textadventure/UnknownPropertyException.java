package textadventure;

public class UnknownPropertyException extends GameException {

    private PropertyContainer propertyContainer;
    private Class<? extends Property> propertyType;

    public UnknownPropertyException(PropertyContainer propertyContainer, Class<? extends Property> propertyType) {
        this.propertyContainer = propertyContainer;
        this.propertyType = propertyType;
    }

    public PropertyContainer getPropertyContainer() {
        return this.propertyContainer;
    }

    public Class<? extends Property> getPropertyType() {
        return this.propertyType;
    }
}
