package textadventure.ui;

@FunctionalInterface
public interface SelectResponse<T>
{
	void select(T selection);
}
