package textadventure;

import textadventure.actions.Action;

public class UnknownActionException extends GameException
{

	private Class<? extends Property> property;
	private Class<? extends Action>   actionType;

	public UnknownActionException(Class<? extends Property> property, Class<? extends Action> actionType)
	{
		this.property = property;
		this.actionType = actionType;
	}

	public Class<? extends Property> getProperty()
	{
		return this.property;
	}

	public Class<? extends Action> getActionType()
	{
		return this.actionType;
	}
}
