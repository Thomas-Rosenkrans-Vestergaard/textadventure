package textadventure;

import com.google.common.collect.ImmutableList;
import textadventure.actions.Action;
import textadventure.characters.*;
import textadventure.characters.Character;
import textadventure.combat.Faction;
import textadventure.rooms.Room;
import textadventure.rooms.RoomController;
import textadventure.ui.GameInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Game
{

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
	 * @param roomController     The {@link Room}s in the {@link Game}.
	 * @param numberOfCharacters The number of {@link Character}s controlled by a {@link Player}.
	 */
	public Game(RoomController roomController, int numberOfCharacters)
	{
		if (numberOfCharacters < 1)
			throw new IllegalArgumentException("numberOfCharacters must be positive.");

		this.roomController = roomController;
		this.numberOfCharacters = numberOfCharacters;
		this.factions = new ArrayList<>();
		this.characterNames = new HashSet<>();
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
		List<Character> characters = new ArrayList<>();

		CharacterCreationCallback characterCreationCallback = (characterCreationTemplate) -> {
			if (characters.size() == numberOfCharacters)
				throw new TooManyCharactersException(numberOfCharacters);
			approveCharacterCreationTemplate(characterCreationTemplate);
			Character character = BaseCharacter.factory(faction, characterCreationTemplate);
			faction.addCharacter(character);
			characters.add(character);
		};

		FinishCharacterCreationCallback finishCharacterCreationCallback = () -> {
			if (characters.size() < numberOfCharacters)
				throw new TooFewCharactersException(numberOfCharacters, characters.size());
			characters.forEach(character -> faction.getLeader().onCharacterCreation(character));
		};

		faction.getLeader().onCharactersCreate(numberOfCharacters, characterCreationCallback, finishCharacterCreationCallback);
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
		ImmutableList<Faction> factions = ImmutableList.copyOf(this.factions);
		for (Faction faction : factions)
			faction.getLeader().onGameStart(this, factions, faction);

		handleTurn(factions.get(0));
	}

	/**
	 * Handles the turn of the provided {@link Player}.
	 *
	 * @param faction The {@link Faction}
	 */
	private void handleTurn(Faction faction)
	{
		handleCharacterTurn(faction, faction.getCharacters().get(0));
	}

	/**
	 * Plays out the turn of the next {@link Player}.
	 */
	private void handleNext(Faction faction)
	{
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
		faction.getLeader().onActionRequest(character, (action -> {
			action.perform(character, faction.getLeader());
			List<Character> characters   = faction.getCharacters();
			int             currentIndex = characters.indexOf(character);
			if (currentIndex == characters.size() - 1)
				handleNext(faction);
			else
				handleCharacterTurn(faction, characters.get(currentIndex + 1));
		}));
	}
}
