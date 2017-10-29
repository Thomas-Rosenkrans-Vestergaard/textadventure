package textadventure;

import com.google.common.collect.ImmutableMap;
import textadventure.actions.ActionRequestCallback;
import textadventure.combat.AttackAction;
import textadventure.combat.DamageSource;
import textadventure.doors.Door;
import textadventure.doors.UseDoorAction;
import textadventure.items.weapons.Shotgun;
import textadventure.rooms.Floor;
import textadventure.rooms.Room;
import textadventure.ui.GameInterface;
import textadventure.ui.characterSelection.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Implementation of the {@link Player} interface, controlled by an AI.
 */
public class ComputerPlayer implements Player
{

	/**
	 * The names given to {@link Character}s of the {@link ComputerPlayer}.
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
	 * The {@link Character}s controlled by the {@link ComputerPlayer}.
	 */
	private List<Character> characters = new ArrayList<>();

	/**
	 * The {@link Random}.
	 */
	private Random random = new Random();

	/**
	 * Events called when the {@link Player} should create their {@link java.lang.Character}s.
	 *
	 * @param gameInterface    The {@link GameInterface}.
	 * @param minimum          The minimum number of {@link java.lang.Character}s to create.
	 * @param maximum          The maximum number of {@link java.lang.Character}s to create.
	 * @param creationCallback The callback to use to create {@link java.lang.Character}s.
	 * @param finishCallback   The callback to use when finishing creating {@link java.lang.Character}s.
	 */
	@Override
	public void createCharacters(GameInterface gameInterface, int minimum, int maximum, CharacterCreationCallback creationCallback, FinishCharacterCreationCallback finishCallback)
	{
		try {
			for (int x = 0; x < maximum && x < names.length; x++) {
				CharacterCreationTemplate template = new CharacterCreationTemplate();
				template.setName(names[x]);
				creationCallback.respond(template);
			}
			finishCallback.respond();
		} catch (CharacterNameTakenException e) {
		} catch (IncompleteCharacterException | TooManyCharactersException | TooFewCharactersException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Event called when the {@link Player} gets a new {@link Character} to control.
	 *
	 * @param character The new {@link Character}.
	 */
	@Override public void onCharacterCreate(Character character)
	{
		this.characters.add(character);
	}

	/**
	 * Delegates a turn to the {@link Player}.
	 *
	 * @param gameInterface The {@link GameInterface} instance.
	 * @param character     The {@link Character} that the {@link Player} should chose an {@link textadventure.actions.Action} for.
	 * @param callback      The callback to use for returning an {@link textadventure.actions.Action}.
	 */
	@Override
	public void playCharacter(GameInterface gameInterface, Character character, ActionRequestCallback callback)
	{
		Room  currentLocation = character.getCurrentLocation();
		Floor floor           = currentLocation.getRoomFloor();

		try {
			Shotgun shotgun = floor.takeItem(0, Shotgun.class);
			character.setWeapon(shotgun);
			System.out.println(shotgun);
		} catch (Exception e) {

		}

		Character target = character.getCurrentLocation().getCharacters().stream().filter(c -> c != character).findFirst().orElse(null);
		System.out.println(target);
		if (target != null) {
			AttackAction action = new AttackAction(target, (characterResponse, actionResponse) -> {
				System.out.println("Attack");
			});

			callback.respond(action, new String[0]);
			return;
		}

		ImmutableMap<String, Property> roomProperties = character.getCurrentLocation().getProperties();
		List<Door> doors = roomProperties.values()
		                                 .stream()
		                                 .filter(property -> property instanceof Door)
		                                 .map(property -> (Door) property)
		                                 .collect(Collectors.toList());

		int  random = this.random.nextInt(doors.size());
		Door door   = doors.get(random);
		if (door == null)
			throw new IllegalStateException();

		UseDoorAction action = new UseDoorAction(door, (characterResponse, actionResponse) -> {
			System.out.println("MOVED TO " + characterResponse.getCurrentLocation().getRoomName());
		});

		callback.respond(action, new String[0]);
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

	}
}
