package textadventure;

import com.google.common.collect.ImmutableList;
import textadventure.actions.ActionRequestCallback;
import textadventure.characters.Character;
import textadventure.characters.*;
import textadventure.combat.AttackAction;
import textadventure.combat.Escapees;
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
import textadventure.ui.Option;
import textadventure.ui.Select;

import java.util.ArrayList;

/**
 * Implementation of the {@link Player} interface, controlled by an AI.
 */
public class SecretPoliceComputerPlayer implements Player
{

	/**
	 * The names given to {@link Character}s of the {@link SecretPoliceComputerPlayer}.
	 */
	private static String[] names = new String[]{
			"Hyeon-Ju Sung-Soo",
			"Yeong-Su Ji",
			"Hyeon-Jeong Yeong",
			"Duri Seok",
			"Seung Jun-Seo",
			"Gyeong Sung-Hoon",
			"Hyeon-U Jeong-Ho",
			"Soo-Jin Ji-Min",
			"Jun Eun-Jung",
			"Jung-Hee Haneul",
			"Joon-Ho Hyeon-Jeong",
			"Ji-Hoon Jun-Ho",
			"Ji-U Sung-Min",
			"Joon Jun",
			"Su-Bin Il-Seong",
	};

	/**
	 * Events called when the {@link Player} should create their {@link Character}s.
	 *
	 * @param numberOfCharacters The number of {@link Character}s to create.
	 * @param creationCallback   The callback to use to create {@link Character}s.
	 * @param finishCallback     The callback to use when finishing creating {@link Character}s.
	 */
	@Override
	public void onCharactersCreate(int numberOfCharacters, CharacterCreationCallback creationCallback, FinishCharacterCreationCallback finishCallback)
	{
		for (int x = 0; x < numberOfCharacters && x < names.length; x++) {
			try {
				CharacterCreationTemplate template = new CharacterCreationTemplate();
				template.setName(names[x]);
				creationCallback.respond(template);
			} catch (CharacterNameTakenException e) {
			} catch (IncompleteCharacterException | TooManyCharactersException e) {
				throw new IllegalStateException(e);
			}
		}

		try {
			finishCallback.respond();
		} catch (TooFewCharactersException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Event called when the {@link Player} gets a new {@link Character} to control.
	 *
	 * @param character The new {@link Character}.
	 */
	@Override public void onCharacterCreation(Character character)
	{

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
		if (faction instanceof Escapees)
			throw new UnsupportedOperationException("SecretPoliceComputerPlayer can only play for the SecretPolice " +
					"faction.");


	}

	/**
	 * Event for when the {@link Game} ends.
	 *
	 * @param game   The {@link Game} instance.
	 * @param result The result of the {@link Game}.
	 */
	@Override public void onGameEnd(Game game, boolean result)
	{

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
		response.respond(true, "Computer");
	}

	/**
	 * Event for when an {@link textadventure.actions.Action}
	 *
	 * @param character The {@link Character} that the {@link Player} should chose an {@link textadventure.actions.Action} for.
	 * @param relations The relations between {@link Property} instances and {@link textadventure.actions.Action} instances.
	 * @param callback  The callback to use for returning an {@link textadventure.actions.Action}.
	 */
	@Override public void onActionRequest(Character character, Relations relations, ActionRequestCallback callback)
	{
		try {
			callback.respond(new ArrayList<>(), NothingAction.class);
		} catch (Exception e) {
			throw new IllegalStateException();
		}
	}

	/**
	 * Event for when the {@link Player} must chose which {@link Character} to play next.
	 *
	 * @param characters The list of possible {@link Character}s left to chose.
	 * @param callback   The callback allowing the {@link Player} to chose which {@link Character} to play next.
	 */
	@Override public void onNextCharacter(ImmutableList<Character> characters, CharacterSelectionCallback callback)
	{
		try {
			callback.next(characters.get(0));
		} catch (CharacterAlreadyPlayedException e) {
			throw new IllegalStateException(e);
		}
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
	 * Event for when a {@link Character} is attacked using an {@link AttackAction}.
	 *
	 * @param character The {@link Character} who was attacked.
	 * @param action    The {@link AttackAction} instance.
	 */
	@Override public void onCharacterAttacked(Character character, AttackAction action)
	{

	}

	/**
	 * Event for when a {@link Player} is required to {@link Select} between one or more
	 * {@link Option}s.
	 *
	 * @param select The {@link Select}.
	 */
	@Override public void select(Select select)
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

	}

	/**
	 * Event for when the {@link Character} performs the {@link CharacterInformationAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CharacterInformationAction}.
	 * @param action    The {@link CharacterInformationAction} instance.
	 */
	@Override public void onCharacterInformationAction(Character character, CharacterInformationAction action)
	{

	}

	/**
	 * Event for when the {@link Character} performs the {@link UseItemsAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseItemsAction}.
	 * @param action    The {@link UseItemsAction} instance.
	 */
	@Override public void onUseItemsAction(Character character, UseItemsAction action)
	{

	}

	/**
	 * Event for when the {@link Character} performs the {@link EquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link EquipAction}.
	 * @param action    The {@link EquipAction} instance.
	 */
	@Override public void onEquipAction(Character character, EquipAction action)
	{

	}

	/**
	 * Event for when the {@link Character} performs the {@link UnEquipAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnEquipAction}.
	 * @param action    The {@link UnEquipAction} instance.
	 */
	@Override public void onUnEquipAction(Character character, UnEquipAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link InspectRoomAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectRoomAction}.
	 * @param action    The {@link InspectRoomAction} instance.
	 */
	@Override public void onInspectRoomAction(Character character, InspectRoomAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link InspectFloorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectFloorAction}.
	 * @param action    The {@link InspectFloorAction} instance.
	 */
	@Override public void onInspectFloorAction(Character character, InspectFloorAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link OpenDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenDoorAction}.
	 * @param action    The {@link OpenDoorAction} instance.
	 */
	@Override public void onOpenDoorAction(Character character, OpenDoorAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link CloseDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CloseDoorAction}.
	 * @param action    The {@link CloseDoorAction} instance.
	 */
	@Override public void onCloseDoorAction(Character character, CloseDoorAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link UseDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseDoorAction}.
	 * @param action    The {@link UseDoorAction} instance.
	 */
	@Override public void onUseDoorAction(Character character, UseDoorAction action)
	{

	}

	/**
	 * Event for when the {@link Character} performs the {@link UseItemsOnAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UseItemsOnAction}.
	 * @param action    The {@link UseItemsOnAction} instance.
	 */
	@Override public void onUseItemsOnAction(Character character, UseItemsOnAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link InspectDoorAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectDoorAction}.
	 * @param action    The {@link InspectDoorAction} instance.
	 */
	@Override public void onInspectDoorAction(Character character, InspectDoorAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link LockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link LockLockAction}.
	 * @param action    The {@link LockLockAction} instance.
	 */
	@Override public void onLockLockAction(Character character, LockLockAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link UnlockLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link UnlockLockAction}.
	 * @param action    The {@link UnlockLockAction} instance.
	 */
	@Override public void onUnlockLockAction(Character character, UnlockLockAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link InspectLockAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectLockAction}.
	 * @param action    The {@link InspectLockAction} instance.
	 */
	@Override public void onInspectLockAction(Character character, InspectLockAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link OpenChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link OpenChestAction}.
	 * @param action    The {@link OpenChestAction} instance.
	 */
	@Override public void onOpenChestAction(Character character, OpenChestAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link CloseChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link CloseChestAction}.
	 * @param action    The {@link CloseChestAction} instance.
	 */
	@Override public void onCloseChestAction(Character character, CloseChestAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link InspectChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectChestAction}.
	 * @param action    The {@link InspectChestAction} instance.
	 */
	@Override public void onInspectChestAction(Character character, InspectChestAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link TakeItemFromChestAction}.
	 *
	 * @param character The {@link Character} who performed the {@link TakeItemFromChestAction}.
	 * @param action    The {@link TakeItemFromChestAction} instance.
	 */
	@Override public void onTakeItemFromChestAction(Character character, TakeItemFromChestAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override public void onDepositItemsIntoChestAction(Character character, DepositItemsIntoChestAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link InspectBackpackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link InspectBackpackAction}.
	 * @param action    The {@link InspectBackpackAction} instance.
	 */
	@Override public void onInspectBackpackAction(Character character, InspectBackpackAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link ExpandBackpackAction}.
	 *
	 * @param character The {@link Character} who performed the {@link ExpandBackpackAction}.
	 * @param action    The {@link ExpandBackpackAction} instance.
	 */
	@Override public void onExpandBackpackAction(Character character, ExpandBackpackAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link DropItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link DropItemAction}.
	 * @param action    The {@link DropItemAction} instance.
	 */
	@Override public void onDropItemAction(Character character, DropItemAction action)
	{

	}

	/**
	 * Event when a {@link Character} performs the {@link PickUpItemAction}.
	 *
	 * @param character The {@link Character} who performed the {@link PickUpItemAction}.
	 * @param action    The {@link PickUpItemAction} instance.
	 */
	@Override public void onPickUpItemAction(Character character, PickUpItemAction action)
	{

	}

	@Override public void onTransferItemAction(Character character, TransferItemsAction action)
	{

	}
}