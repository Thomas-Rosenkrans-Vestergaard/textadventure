package textadventure;

import com.google.common.collect.ImmutableList;
import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.actions.ActionResponses;
import textadventure.characters.Character;
import textadventure.characters.CharacterCreationCallback;
import textadventure.characters.CharacterSelectionCallback;
import textadventure.characters.FinishCharacterCreationCallback;
import textadventure.combat.Faction;
import textadventure.highscore.HighScoreResponse;
import textadventure.highscore.Outcome;
import textadventure.select.*;

/**
 * Object that can control {@link Character}s and perform {@link Action}s.
 */
public interface Player extends ActionResponses
{

	/**
	 * Event for when a {@link textadventure.Player} is required to {@link Select} between one or more
	 * {@link textadventure.select.Option}s.
	 *
	 * @param select The {@link Select}.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownIndexException    When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link textadventure.select.Select.SelectResponse}.
	 * @throws UnknownOptionException   When a selected element were not contained is the list of possibilities.
	 */
	void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException;

	/**
	 * Events called when the {@link Player} should create their {@link Character}s.
	 *
	 * @param numberOfCharacters The number of {@link Character}s to create.
	 * @param creationCallback   The callback to use to create {@link Character}s.
	 * @param finishCallback     The callback to use when finishing creating {@link Character}s.
	 */
	void onCharactersCreate(int numberOfCharacters, CharacterCreationCallback creationCallback, FinishCharacterCreationCallback finishCallback);

	/**
	 * Event called when the {@link Player} gets a new {@link Character} to control.
	 *
	 * @param character The new {@link Character}.
	 */
	void onCharacterCreation(Character character);

	/**
	 * Event for when the {@link Game} starts.
	 *
	 * @param game     The {@link Game} instance.
	 * @param factions The list of {@link Faction}s competing in the {@link Game}.
	 * @param faction  The {@link Faction} the {@link Player} belongs to.
	 */
	void onGameStart(Game game, ImmutableList<Faction> factions, Faction faction);

	/**
	 * Event for when the {@link Game} ends.
	 *
	 * @param game   The {@link Game} instance.
	 * @param result The result of the {@link Game}.
	 */
	void onGameEnd(Game game, boolean result);

	/**
	 * Event for when the {@link Player} needs to decide whether or not to save the {@link Player}s score to the
	 * high-score file.
	 *
	 * @param score    The score achieved by the {@link Player}.
	 * @param outcome  The {@link Outcome} of the {@link Game}.
	 * @param response The response of the {@link Player}.
	 */
	void onHighScoreRequest(int score, Outcome outcome, HighScoreResponse response);

	/**
	 * Event for when the {@link Player} must chose which {@link Character} to play next.
	 *
	 * @param characters The list of possible {@link Character}s left to chose.
	 * @param callback   The callback allowing the {@link Player} to chose which {@link Character} to play next.
	 */
	void onNextCharacter(ImmutableList<Character> characters, CharacterSelectionCallback callback);

	/**
	 * Event for when an {@link Action}
	 *
	 * @param character The {@link Character} that the {@link Player} should chose an {@link Action} for.
	 * @param callback  The callback to use for returning an {@link Action}.
	 */
	void onActionRequest(Character character, ActionRequestCallback callback);

	/**
	 * Returns the {@link ActionResponses} used when notifying {@link Player}s about {@link Action}s occurring within
	 * sight of the {@link Character}s controlled by the {@link Player}.
	 *
	 * @return The {@link ActionResponses} used when notifying {@link Player}s about {@link Action}s occurring within
	 * sight of the {@link Character}s controlled by the {@link Player}.
	 */
	ActionResponses getSecondaryActionResponses();
}
