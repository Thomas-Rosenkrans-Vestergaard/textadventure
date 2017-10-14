package textadventure.ui;

import textadventure.*;

public class SelectException extends ActionException
{
	public SelectException(Property property, Action action, Player player)
	{
		super(property, action, player);
	}
}
