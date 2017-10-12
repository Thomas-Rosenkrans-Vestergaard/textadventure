package textadventure;

/**
 * Abstract implementation of the {@link Player} interface.
 */
public abstract class AbstractPlayer implements Player
{

	/**
	 * The {@link Character} the {@link Player} controls.
	 */
	private Character character;

	/**
	 * Creates a new {@link AbstractPlayer}.
	 *
	 * @param character The {@link Character} the {@link Player} controls.
	 */
	public AbstractPlayer(Character character)
	{
		this.character = character;
	}

	/**
	 * Returns the {@link Character} being played by the {@link Player}.
	 *
	 * @return The {@link Character} being played by the {@link Player}.
	 */
	@Override public Character getCharacter()
	{
		return character;
	}
}

