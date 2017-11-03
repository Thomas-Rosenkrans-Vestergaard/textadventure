package textadventure;

import com.google.common.collect.ImmutableList;
import textadventure.actions.Action;
import textadventure.actions.ActionRequestCallback;
import textadventure.characters.Character;
import textadventure.characters.*;
import textadventure.combat.AttackAction;
import textadventure.combat.Faction;
import textadventure.doors.*;
import textadventure.highscore.HighScoreResponse;
import textadventure.highscore.Outcome;
import textadventure.items.backpack.ExpandBackpackAction;
import textadventure.items.backpack.InspectBackpackAction;
import textadventure.items.chest.*;
import textadventure.lock.InspectLockAction;
import textadventure.lock.LockLockAction;
import textadventure.lock.UnlockLockAction;
import textadventure.rooms.Room;
import textadventure.ui.*;

/**
 * Human implementation of the {@link Player} interface. The
 */
public class HumanPlayer implements Player
{

	/**
	 * The {@link GameInterface} the {@link HumanPlayer} interacts with the human through.
	 */
	private GameInterface gameInterface;

	/**
	 * Creates a new {@link HumanPlayer}.
	 *
	 * @param gameInterface The {@link GameInterface} the {@link HumanPlayer} interacts with the human through.
	 */
	public HumanPlayer(GameInterface gameInterface)
	{
		this.gameInterface = gameInterface;
	}

	/**
	 * Event for when the {@link Game} starts.
	 *
	 * @param game     The {@link Game} instance.
	 * @param factions The list of {@link Faction}s competing in the {@link Game}.
	 * @param faction  The {@link Faction} the {@link Player} belongs to.
	 */
	@Override public void onGameStart(Game game, ImmutableList<Faction> factions, Faction faction)
	{
		gameInterface.onGameStart(game, factions, faction);
	}

	/**
	 * Event for when the {@link Game} ends.
	 *
	 * @param game   The {@link Game} instance.
	 * @param result The result of the {@link Game}.
	 */
	@Override public void onGameEnd(Game game, boolean result)
	{
		gameInterface.onGameEnd(game, result);
	}

	/**
	 * Event for when the {@link Player} needs to decide whether or not to save the {@link Player}s score to the
	 * high-score file.
	 *
	 * @param score    The score achieved by the {@link Player}.
	 * @param outcome  The {@link Outcome} of the {@link Game}.
	 * @param response The response of the {@link Player}.
	 */
	@Override public void onHighScoreRequest(int score, Outcome outcome, HighScoreResponse response)
	{
		gameInterface.onHighScoreRequest(score, outcome, response);
	}

	/**
	 * Event for when the {@link Player} must chose which {@link Character} to play next.
	 *
	 * @param characters The list of possible {@link Character}s left to chose.
	 * @param callback   The callback allowing the {@link Player} to chose which {@link Character} to play next.
	 */
	@Override public void onNextCharacter(ImmutableList<Character> characters, CharacterSelectionCallback callback)
	{
		gameInterface.onNextCharacter(characters, callback);
	}

	/**
	 * Events called when the {@link Player} should create their {@link Character}s.
	 *
	 * @param numberOfCharacters The  number of {@link Character}s to create.
	 * @param creationCallback   The callback to use to create {@link Character}s.
	 * @param finishCallback     The callback to use when finishing creating {@link Character}s.
	 */
	@Override
	public void onCharactersCreate(int numberOfCharacters, CharacterCreationCallback creationCallback, FinishCharacterCreationCallback finishCallback)
	{
		gameInterface.onCharactersCreate(numberOfCharacters, creationCallback, finishCallback);
	}

	/**
	 * Event called when the {@link Player} gets a new {@link Character} to control.
	 *
	 * @param character The new {@link Character}.
	 */
	@Override public void onCharacterCreation(Character character)
	{
		gameInterface.onCharacterCreation(character);
	}

	/**
	 * Event for when a {@link Player} is required to {@link Select} between one or more
	 * {@link Option}s.
	 *
	 * @param select The {@link Select}.
	 * @throws SelectionAmountException When too few or too many elements were selected.
	 * @throws UnknownIndexException    When a selected element were not contained is the list of possibilities.
	 * @throws UnknownOptionException   When a selected element were not contained is the list of possibilities.
	 * @throws SelectResponseException  When an exception occurs from the provided {@link SelectResponseException}.
	 */
	@Override
	public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
	{
		gameInterface.select(select);
	}

	/**
	 * Event for when an {@link Action}
	 *
	 * @param character The {@link Character} that the {@link Player} should chose an {@link Action} for.
	 * @param callback  The callback to use for returning an {@link Action}.
	 */
	@Override public void onActionRequest(Character character, ActionRequestCallback callback)
	{
		gameInterface.onActionRequest(character, callback);
	}

	/**
	 * Event for when a {@link Character} enter a {@link Room}.
	 *
	 * @param character The {@link Character} who entered the {@link Room}.
	 * @param room      The {@link Room} the {@link Character} entered.
	 * @param door      The {@link Door} the {@link Character} entered through.
	 */
	@Override public void onCharacterEntersRoom(Character character, Room room, Door door)
	{

	}

	/**
	 * Event for when a {@link Character} exits a {@link Room}.
	 *
	 * @param character The {@link Character} who exited the {@link Room}.
	 * @param room      The {@link Room} the {@link Character} exited.
	 * @param door      The {@link Door} the {@link Character} exited through.
	 */
	@Override public void onCharacterExistsRoom(Character character, Room room, Door door)
	{

	}

	/**
	 * Event for when the {@link Character} performs the {@link AttackAction}.
	 *
	 * @param character The {@link Character} who was attacked.
	 * @param action    The {@link AttackAction} instance.
	 */
	@Override public void onCharacterAttacked(Character character, AttackAction action)
	{

	}

	/**
	 * Event for when the {@link Character} performs the {@link EscapeAction}.
	 *
	 * @param character The {@link Character} who performed the {@link EscapeAction}.
	 * @param action    The {@link EscapeAction} instance.
	 */
	@Override public void onEscapeAction(Character character, EscapeAction action)
	{

	}

	/**
	 * Event for when the {@link Character} performs the {@link AttackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link AttackAction}.
	 * @param action    The {@link AttackAction} instance.
	 */
	@Override public void onAttackAction(Character character, AttackAction action)
	{
		gameInterface.onAttackAction(character, action);
	}

	/**
	 * Event for when the {@link Character} performs the {@link CharacterInformationAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CharacterInformationAction}.
	 * @param action    The {@link CharacterInformationAction} instance.
	 */
	@Override public void onCharacterInformationAction(Character character, CharacterInformationAction action)
	{
		gameInterface.onCharacterInformationAction(character, action);
	}

	/**
	 * Event for when the {@link Character} performs the {@link UseItemsAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseItemsAction}.
	 * @param action    The {@link UseItemsAction} instance.
	 */
	@Override public void onUseItemsAction(Character character, UseItemsAction action)
	{
		gameInterface.onUseItemsAction(character, action);
	}

	/**
	 * Event for when the {@link Character} performs the {@link UseItemsOnAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseItemsOnAction}.
	 * @param action    The {@link UseItemsOnAction} instance.
	 */
	@Override public void onUseItemsOnAction(Character character, UseItemsOnAction action)
	{
		gameInterface.onUseItemsOnAction(character, action);
	}

	/**
	 * Event for when the {@link Character} performs the {@link EquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link EquipAction}.
	 * @param action    The {@link EquipAction} instance.
	 */
	@Override public void onEquipAction(Character character, EquipAction action)
	{
		gameInterface.onEquipAction(character, action);
	}

	/**
	 * Event for when the {@link Character} performs the {@link UnEquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnEquipAction}.
	 * @param action    The {@link UnEquipAction} instance.
	 */
	@Override public void onUnEquipAction(Character character, UnEquipAction action)
	{
		gameInterface.onUnEquipAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectRoomAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectRoomAction}.
	 * @param action    The {@link InspectRoomAction} instance.
	 */
	@Override public void onInspectRoomAction(Character character, InspectRoomAction action)
	{
		gameInterface.onInspectRoomAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectFloorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectFloorAction}.
	 * @param action    The {@link InspectFloorAction} instance.
	 */
	@Override public void onInspectFloorAction(Character character, InspectFloorAction action)
	{
		gameInterface.onInspectFloorAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link OpenDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenDoorAction}.
	 * @param action    The {@link OpenDoorAction} instance.
	 */
	@Override public void onOpenDoorAction(Character character, OpenDoorAction action)
	{
		gameInterface.onOpenDoorAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link CloseDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CloseDoorAction}.
	 * @param action    The {@link CloseDoorAction} instance.
	 */
	@Override public void onCloseDoorAction(Character character, CloseDoorAction action)
	{
		gameInterface.onCloseDoorAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link UseDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseDoorAction}.
	 * @param action    The {@link UseDoorAction} instance.
	 */
	@Override public void onUseDoorAction(Character character, UseDoorAction action)
	{
		gameInterface.onUseDoorAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectDoorAction}.
	 * @param action    The {@link InspectDoorAction} instance.
	 */
	@Override public void onInspectDoorAction(Character character, InspectDoorAction action)
	{
		gameInterface.onInspectDoorAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link LockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link LockLockAction}.
	 * @param action    The {@link LockLockAction} instance.
	 */
	@Override public void onLockLockAction(Character character, LockLockAction action)
	{
		gameInterface.onLockLockAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link UnlockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnlockLockAction}.
	 * @param action    The {@link UnlockLockAction} instance.
	 */
	@Override public void onUnlockLockAction(Character character, UnlockLockAction action)
	{
		gameInterface.onUnlockLockAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectLockAction}.
	 * @param action    The {@link InspectLockAction} instance.
	 */
	@Override public void onInspectLockAction(Character character, InspectLockAction action)
	{
		gameInterface.onInspectLockAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link OpenChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenChestAction}.
	 * @param action    The {@link OpenChestAction} instance.
	 */
	@Override public void onOpenChestAction(Character character, OpenChestAction action)
	{
		gameInterface.onOpenChestAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link CloseChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CloseChestAction}.
	 * @param action    The {@link CloseChestAction} instance.
	 */
	@Override public void onCloseChestAction(Character character, CloseChestAction action)
	{
		gameInterface.onCloseChestAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectChestAction}.
	 * @param action    The {@link InspectChestAction} instance.
	 */
	@Override public void onInspectChestAction(Character character, InspectChestAction action)
	{
		gameInterface.onInspectChestAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link TakeItemFromChestAction}.
	 * @param action    The {@link TakeItemFromChestAction} instance.
	 */
	@Override public void onTakeItemFromChestAction(Character character, TakeItemFromChestAction action)
	{
		gameInterface.onTakeItemFromChestAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
	{
		gameInterface.onDepositItemsIntoChestAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link InspectBackpackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectBackpackAction}.
	 * @param action    The {@link InspectBackpackAction} instance.
	 */
	@Override public void onInspectBackpackAction(Character character, InspectBackpackAction action)
	{
		gameInterface.onInspectBackpackAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link ExpandBackpackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link ExpandBackpackAction}.
	 * @param action    The {@link ExpandBackpackAction} instance.
	 */
	@Override public void onExpandBackpackAction(Character character, ExpandBackpackAction action)
	{
		gameInterface.onExpandBackpackAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override public void onDropItemAction(Character character, DropItemAction action)
	{
		gameInterface.onDropItemAction(character, action);
	}

	/**
	 * Event when a {@link Character} performs the {@link PickUpItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link PickUpItemAction}.
	 * @param action    The {@link PickUpItemAction} instance.
	 */
	@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
	{
		gameInterface.onPickUpItemAction(character, action);
	}

	@Override public void onTransferItemAction(Character character, TransferItemsAction action)
	{
		gameInterface.onTransferItemAction(character, action);
	}
}
