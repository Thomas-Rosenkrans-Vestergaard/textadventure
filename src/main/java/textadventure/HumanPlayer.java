package textadventure;

import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.characters.Character;
import textadventure.combat.DamageSource;
import textadventure.ui.GameInterface;
import textadventure.ui.characterSelection.CharacterCreationCallback;
import textadventure.ui.characterSelection.FinishCharacterCreationCallback;

/**
 * Human implementation of the {@link Player} interface. The
 */
public class HumanPlayer implements Player
{

	/**
	 * Events called when the {@link Player} should create their {@link Character}s.
	 *
	 * @param gameInterface      The {@link GameInterface}.
	 * @param numberOfCharacters The  number of {@link Character}s to create.
	 * @param creationCallback   The callback to use to create {@link Character}s.
	 * @param finishCallback     The callback to use when finishing creating {@link Character}s.
	 */
	@Override
	public void createCharacters(GameInterface gameInterface, int numberOfCharacters,
	                             CharacterCreationCallback creationCallback, FinishCharacterCreationCallback finishCallback)
	{
		gameInterface.onCharacterCreation(this, numberOfCharacters, creationCallback, finishCallback);
	}

	/**
	 * Event called when the {@link Player} gets a new {@link Character} to control.
	 *
	 * @param character The new {@link Character}.
	 */
	@Override public void onCharacterCreate(Character character)
	{

	}

	/**
	 * Delegates a turn to the {@link Player}.
	 *
	 * @param gameInterface The {@link GameInterface} instance.
	 * @param character     The {@link Character} that the {@link Player} should chose an {@link Action} for.
	 * @param callback      The callback to use for returning an {@link Action}.
	 */
	@Override
	public void playCharacter(GameInterface gameInterface, Character character, ActionRequestCallback callback)
	{
		gameInterface.onActionRequest(character, callback::respond);
	}

	/**
	 * Event called when a {@link Character} controlled by the {@link Player} dies.
	 *
	 * @param gameInterface The {@link GameInterface}.
	 * @param character     The {@link Character} who died.
	 * @param damageSource  The {@link DamageSource} that killed the {@link Character}.
	 */
	@Override public void onCharacterDies(GameInterface gameInterface, Character character, DamageSource damageSource)
	{
		gameInterface.onCharacterDies(this, character, damageSource);
	}
}
