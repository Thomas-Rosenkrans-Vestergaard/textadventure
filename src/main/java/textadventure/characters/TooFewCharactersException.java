package textadventure.characters;

import textadventure.GameException;

/**
 * Thrown when too few {@link Character}s were provided to the {@link CharacterCreationCallback} before
 * calling the {@link CharacterCreationCallback}.
 */
public class TooFewCharactersException extends GameException
{

	/**
	 * The minimum number of {@link Character}s that must be created.
	 */
	private int minimum;

	/**
	 * The actual number of {@link Character} that must be created.
	 */
	private int actual;

	/**
	 * Creates a new {@link TooFewCharactersException}.
	 *
	 * @param minimum The minimum number of {@link Character}s that must be created.
	 * @param actual  The actual number of {@link Character} that must be created.
	 */
	public TooFewCharactersException(int minimum, int actual)
	{
		if (actual >= minimum)
			throw new IllegalArgumentException("Actual must be smaller than minimum.");

		this.minimum = minimum;
		this.actual = actual;
	}

	/**
	 * Returns the minimum number of {@link Character}s that must be created.
	 *
	 * @return The minimum number of {@link Character}s that must be created.
	 */
	public int getMinimum()
	{
		return this.minimum;
	}

	/**
	 * Returns the actual number of {@link Character} that must be created.
	 *
	 * @return The actual number of {@link Character} that must be created.
	 */
	public int getActual()
	{
		return this.actual;
	}
}
