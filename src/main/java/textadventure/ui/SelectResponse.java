package textadventure.ui;

import textadventure.ActionException;

@FunctionalInterface
public interface SelectResponse<T>
{
	void select(T selection) throws ActionException;
}
