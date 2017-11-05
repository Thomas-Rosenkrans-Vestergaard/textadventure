package textadventure.combat;

import org.junit.Test;
import org.mockito.Mockito;
import textadventure.actions.ActionResponses;
import textadventure.actions.SomeActionResponses;
import textadventure.characters.Character;
import textadventure.characters.SomeCharacter;
import textadventure.items.weapons.Weapon;
import textadventure.rooms.Room;
import textadventure.rooms.SomeRoom;
import textadventure.select.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Matchers.same;

public class AttackActionTest
{

	@Test
	public void perform() throws Exception
	{
		SomeCharacter character = new SomeCharacter();
		character.setFaction(new SomeFaction());

		Weapon weapon = new Weapon()
		{
			@Override public int getNumberOfTargets()
			{
				return 1;
			}

			@Override public int getDamage()
			{
				return 20;
			}

			@Override public String getWeaponName()
			{
				return null;
			}

			@Override public String getWeaponDescription()
			{
				return null;
			}
		};

		character.setWeapon(weapon);

		SomeCharacter target = Mockito.spy(new SomeCharacter());
		target.setFaction(new SomeFaction());
		target.setCurrentHP(50);
		target.setMaxHP(100);
		Room room = new SomeRoom();
		room.addCharacter(character);
		room.addCharacter(target);
		character.setCurrentLocation(room);

		AttackAction action = new AttackAction();

		ActionResponses actionResponses = new SomeActionResponses()
		{
			@Override
			public void select(Select select) throws SelectionAmountException, UnknownIndexException, UnknownOptionException, SelectResponseException
			{
				select.selectIndex(0);
			}

			@Override public void onAttackAction(Character character, AttackAction action)
			{
				assertFalse(action.hasException());
				assertEquals(1, action.getTargets().size());
				assertEquals(1, action.getDamageDone().size());
			}
		};

		ActionResponses mock = Mockito.spy(actionResponses);
		action.perform(character, mock);
		Mockito.verify(mock).onAttackAction(same(character), same(action));
		Mockito.verify(target).takeDamage(same(weapon));
	}
}