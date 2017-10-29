package textadventure.items;

/**
 * An {@link ItemType} implementation based of a given {@link Class}.
 */
public class ClassTypeItem implements ItemType
{

	/**
	 * The type of the {@link ClassTypeItem}.
	 */
	private Class<?> type;

	/**
	 * Creates a new {@link ClassTypeItem}.
	 *
	 * @param type The {@link Class} type fo the {@link ClassTypeItem}.
	 */
	public ClassTypeItem(Class<?> type)
	{
		this.type = type;
	}

	/**
	 * Creates a new {@link ClassTypeItem}.
	 *
	 * @param type The {@link Class} type fo the {@link ClassTypeItem}.
	 * @return The newly created {@link ClassTypeItem}.
	 */
	public static ClassTypeItem of(Class<?> type)
	{
		return new ClassTypeItem(type);
	}

	/**
	 * Returns the type of {@link Class} of the {@link ItemType}.
	 *
	 * @return The type of {@link Class} of the {@link ItemType}.
	 */
	@Override public Class<?> getItemTypeClass()
	{
		return type;
	}

	/**
	 * Returns the name of the {@link ItemType}.
	 *
	 * @return The name of the {@link ItemType}.
	 */
	@Override public String getItemTypeName()
	{
		return type.getName();
	}

	/**
	 * Returns the description of the {@link ItemType}.
	 *
	 * @return The description of the {@link ItemType}.
	 */
	@Override public String getItemTypeDescription()
	{
		return String.format("An item of type %s.", type.getName());
	}
}
