package textadventure.characters;

public interface CharacterSelectionCallback
{

	/**
	 * Callback for when a {@link textadventure.Player} must chose which {@link Character} to play next.
	 *
	 * @param character The {@link Character} the {@link textadventure.Player} wants to play next.
	 * @throws CharacterAlreadyPlayedException When the {@link Character} has already used their turn.
	 */
	void next(Character character) throws CharacterAlreadyPlayedException;

	/**
	 * Callback for when a {@link textadventure.Player} wants to skip the rest of their turn.
	 */
	void skip();
}
