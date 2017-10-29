package textadventure.items;

public class SomeTypedItem extends AbstractItem
{

	private int type;

	public SomeTypedItem(int type)
	{
		super(null, null);

		this.type = type;
	}

	@Override public int hashCode()
	{
		return type;
	}
}
