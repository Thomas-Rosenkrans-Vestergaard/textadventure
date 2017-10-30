package textadventure;

import textadventure.actions.Action;
import textadventure.combat.Faction;
import textadventure.rooms.*;
import textadventure.ui.GameInterface;
import textadventure.ui.characterSelection.*;

import java.util.*;

public class Game
{

	/**
	 * The {@link GameInterface} used for input-output.
	 */
	private GameInterface gameInterface;

	/**
	 * The {@link Room}s in the {@link Game}.
	 */
	private RoomController roomController;

	/**
	 * The minimum amount of {@link Character}s controlled by a {@link Player} in the {@link Game}.
	 */
	private final int numberOfCharacters;

	/**
	 * The {@link Faction}s in the {@link Game}.
	 */
	private final List<Faction> factions;

	/**
	 * Returns the {@link Set} of the names in the {@link Game}.
	 */
	private final Set<String> characterNames;

	/**
	 * The {@link GameInterface} to use for input-output.
	 *
	 * @param gameInterface      The {@link GameInterface} to use for input-output.
	 * @param roomController     The {@link Room}s in the {@link Game}.
	 * @param numberOfCharacters The number of {@link Character}s controlled by a {@link Player}.
	 */
	public Game(GameInterface gameInterface, RoomController roomController, int numberOfCharacters)
	{
		if (numberOfCharacters < 1)
			throw new IllegalArgumentException("numberOfCharacters must be positive.");

		this.gameInterface = gameInterface;
		this.roomController = roomController;
		this.numberOfCharacters = numberOfCharacters;
		this.factions = new ArrayList<>();
		this.characterNames = new HashSet<>();
		gameInterface.onInit(this, roomController, numberOfCharacters);
	}

	/**
	 * Adds the provided {@link Player} to the {@link Game}.
	 *
	 * @param faction The {@link Faction} the {@link Player} plays for.
	 */
	public void addFaction(Faction faction) throws FactionAlreadyTakenException
	{
		if (factions.contains(faction))
			throw new FactionAlreadyTakenException(faction);

		factions.add(faction);
		gameInterface.onFactionJoin(this, faction);
		List<Character> characters = new ArrayList<>();

		CharacterCreationCallback characterCreationCallback = (characterCreationTemplate) -> {
			if (characters.size() == numberOfCharacters)
				throw new TooManyCharactersException(numberOfCharacters);
			approveCharacterCreationTemplate(characterCreationTemplate);
			Character character = BaseCharacter.factory(gameInterface, faction, characterCreationTemplate);
			faction.addCharacter(character);
			characters.add(character);
		};

		FinishCharacterCreationCallback finishCharacterCreationCallback = () -> {
			if (characters.size() < numberOfCharacters)
				throw new TooFewCharactersException(numberOfCharacters, characters.size());
			characters.forEach(character -> faction.getLeader().onCharacterCreate(character));
		};

		faction.getLeader().createCharacters(gameInterface, numberOfCharacters, characterCreationCallback,
				finishCharacterCreationCallback);
	}

	/**
	 * Throws an exception if the provided {@link CharacterCreationCallback} is illegal.
	 *
	 * @param characterCreationTemplate The {@link CharacterCreationTemplate} to approve.
	 * @throws CharacterNameTakenException  When the name of the {@link CharacterCreationTemplate} is taken.
	 * @throws IncompleteCharacterException When information is missing from the {@link CharacterCreationTemplate}.
	 */
	private void approveCharacterCreationTemplate(CharacterCreationTemplate characterCreationTemplate) throws CharacterNameTakenException, IncompleteCharacterException
	{
		if (characterNames.contains(characterCreationTemplate.getName()))
			throw new CharacterNameTakenException(characterCreationTemplate);

		if (characterCreationTemplate.getName() == null)
			throw new IncompleteCharacterException(characterCreationTemplate);
	}

	/**
	 * Starts the {@link Game}.
	 */
	public void start()
	{
		for (Faction faction : factions)
			gameInterface.onGameStart(this, faction);
		handleTurn(factions.get(0));
	}

	/**
	 * Handles the turn of the provided {@link Player}.
	 *
	 * @param faction The {@link Faction}
	 */
	private void handleTurn(Faction faction)
	{
		gameInterface.onTurnStart(this, faction);
		handleCharacterTurn(faction, faction.getCharacters().get(0));
	}

	/**
	 * Plays out the turn of the next {@link Player}.
	 */
	private void handleNext(Faction faction)
	{
		gameInterface.onTurnEnd(this, faction);
		int index = factions.indexOf(faction);
		if (index + 1 == factions.size()) {
			handleTurn(factions.get(0));
			return;
		}

		handleTurn(factions.get(index + 1));
	}

	/**
	 * Requests an {@link Action} from the provided {@link Player}.
	 */
	private void handleCharacterTurn(Faction faction, Character character)
	{
		faction.getLeader().playCharacter(gameInterface, character, ((action, arguments) -> {
			action.perform(gameInterface, character, arguments);
			List<Character> characters   = faction.getCharacters();
			int             currentIndex = characters.indexOf(character);
			if (currentIndex == characters.size() - 1)
				handleNext(faction);
			else
				handleCharacterTurn(faction, characters.get(currentIndex + 1));
		}));
	}

	/**
	 * Returns the {@link GameInterface} used as input-output for the game.
	 *
	 * @return The {@link GameInterface} used as input-output for the game.
	 */
	public GameInterface getGameInterface()
	{
		return this.gameInterface;
	}

	/**
	 * Returns the number of {@link Character} in each {@link Faction}.
	 *
	 * @return The number of {@link Character} in each {@link Faction}.
	 */
	public int getNumberOfCharacters()
	{
		return this.numberOfCharacters;
	}
}
