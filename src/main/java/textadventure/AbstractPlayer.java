package textadventure;

/**
 * Abstract implementation of the {@link Player} interface.
 */
public abstract class AbstractPlayer extends AbstractLivingBeing implements Player
{

	/**
	 * The name of the {@link Player}.
	 */
	private final String name;

	/**
	 * Creates a new {@link Player}.
	 *
	 * @param name The name of the {@link Player}.
	 */
	AbstractPlayer(int maxHealth, String name)
	{
		super(maxHealth);
		this.name = name;
	}

	/**
	 * Returns the name of the {@link Player}.
	 *
	 * @return The name of the {@link Player}.
	 */
	@Override public String getName()
	{
		return name;
	}
}
