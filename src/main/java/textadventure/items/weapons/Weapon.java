package textadventure.items.weapons;

import textadventure.combat.DamageSource;
import textadventure.items.EquipableItem;

/**
 * Represents a type of {@link Weapon} damage
 */
public interface Weapon extends DamageSource, EquipableItem
{

	/**
	 * Returns the number of {@link textadventure.characters.Character}s the {@link Weapon} can be used on at once.
	 *
	 * @return The number of {@link textadventure.characters.Character}s the {@link Weapon} can be used on at once.
	 */
	int getNumberOfTargets();
}
