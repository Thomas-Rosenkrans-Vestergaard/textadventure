package textadventure;

import textadventure.actions.Action;

public class UnknownActionException extends GameException
{

    private Property property;
    private Class<? extends Action> actionType;

    public UnknownActionException(Property property, Class<? extends Action> actionType) {
        this.property = property;
        this.actionType = actionType;
    }

    public Property getProperty() {
        return this.property;
    }

    public Class<? extends Action> getActionType() {
        return this.actionType;
    }
}
