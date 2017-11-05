package textadventure.actions;

@FunctionalInterface
public interface ActionFactory
{

	/**
	 * Uses the {@link ActionFactory} to create a new {@link Action}.
	 *
	 * @return The created {@link textadventure.combat.Faction}.
	 */
	Action create();
}
