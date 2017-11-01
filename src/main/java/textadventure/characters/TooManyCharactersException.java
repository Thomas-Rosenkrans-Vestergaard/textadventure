package textadventure.characters;

import textadventure.GameException;

/**
 * Thrown when too many {@link Character}s were provided to the {@link CharacterCreationCallback} before
 * calling the {@link CharacterCreationCallback}.
 */
public class TooManyCharactersException extends GameException
{

	/**
	 * The maximum number of {@link Character}s that must be created.
	 */
	private int maximum;

	/**
	 * Creates a new {@link TooFewCharactersException}.
	 *
	 * @param maximum The maximum number of {@link Character}s that must be created.
	 */
	public TooManyCharactersException(int maximum)
	{
		this.maximum = maximum;
	}

	/**
	 * Returns the maximum number of {@link Character}s that must be created.
	 *
	 * @return The maximum number of {@link Character}s that must be created.
	 */
	public int getMaximum()
	{
		return this.maximum;
	}
}