package textadventure.characters;

import com.google.common.collect.ImmutableList;
import textadventure.GameException;

/**
 * Thrown when a {@link textadventure.Player} selects a {@link Character} who has already played this turn.
 */
public class CharacterAlreadyPlayedException extends GameException
{

	/**
	 * The {@link Character} who could not be played.
	 */
	private Character character;

	/**
	 * The list of {@link Character}s left to play in this round.
	 */
	private ImmutableList<Character> charactersLeft;

	/**
	 * Creates a new {@link CharacterAlreadyPlayedException}.
	 *
	 * @param character      The {@link Character} who could not be played.
	 * @param charactersLeft The list of {@link Character}s left to play in this round.
	 */
	public CharacterAlreadyPlayedException(Character character, ImmutableList<Character> charactersLeft)
	{
		this.character = character;
		this.charactersLeft = charactersLeft;
	}

	/**
	 * Returns the {@link Character} who could not be played.
	 *
	 * @return The {@link Character} who could not be played.
	 */
	public Character getCharacter()
	{
		return this.character;
	}

	/**
	 * Returns the list of {@link Character}s left to play in this round.
	 *
	 * @return The list of {@link Character}s left to play in this round.
	 */
	public ImmutableList<Character> getCharactersLeft()
	{
		return this.charactersLeft;
	}
}
