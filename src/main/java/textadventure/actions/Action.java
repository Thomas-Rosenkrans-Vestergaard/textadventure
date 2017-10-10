package textadventure.actions;

import textadventure.GameController;
import textadventure.Player;

public interface Action {

    /**
     * Returns the name of the {@link Action}.
     *
     * @return The name of the {@link Action}.
     */
    String getName();

    /**
     * Returns the description of the {@link Action}.
     *
     * @return The description of the {@link Action}.
     */
    String getDescription();

    /**
     * Performs the {@link Action} using the provided parameters.
     *
     * @param controller The {@link GameController}.
     * @param player     The {@link Player} performing the {@link Action}.
     */
    void perform(GameController controller, Player player) throws ActionException;
}
