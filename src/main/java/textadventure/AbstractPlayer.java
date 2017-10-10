package textadventure;

/**
 * Abstract implementation of the {@link Player} interface.
 */
public abstract class AbstractPlayer extends AbstractLivingBeing implements Player {

    /**
     * The name of the {@link Player}.
     */
    private final String name;

    /**
     * The current location of the {@link Player}.
     */
    private Room currentLocation;

    /**
     * Creates a new {@link AbstractPlayer}.
     *
     * @param name The name of the {@link Player}.
     * @param currentLocation The current location of the {@link Player}.
     * @param maxHealth The maxHealt of the {@link Player}.
     */
    public AbstractPlayer(String name, Room currentLocation, int maxHealth) {
        super(maxHealth);
        this.currentLocation = currentLocation;
        this.name = name;
    }


    /**
     * Returns the name of the {@link Player}.
     *
     * @return The name of the {@link Player}.
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the {@link Player}'s current location.
     * @return currentLocation of the {@link Player}.
     */
    public Room getCurrentLocation() {
        return currentLocation;
    }

    /**
     * Sets the {@link Player's}'s current location.
     * @param currentLocation The current location of the {@link Player}.
     */
    public void setCurrentLocation(Room currentLocation) {
        this.currentLocation = currentLocation;
    }
}
