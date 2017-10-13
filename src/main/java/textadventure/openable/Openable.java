package textadventure.openable;

public interface Openable
{

    /**
     * Represents the {@link State} of the {@link Openable} object.
     */
    enum State
    {
        OPEN,
        CLOSED
    }

    /**
     * Returns the {@link State} of the {@link Openable} object.
     *
     * @return The {@link State} of the {@link Openable} object.
     */
    State getOpenableState();

    /**
     * Opens the {@link Openable} object.
     *
     * @throws OpenableAlreadyOpenException When the {@link Openable} object is already open.
     * @throws CannotOpenException          When the {@link Openable} object cannot open.
     */
    void open() throws OpenableAlreadyOpenException, CannotOpenException;

    /**
     * Closes the {@link Openable} object.
     *
     * @throws OpenableAlreadyClosedException When the {@link Openable} object is already closed.
     * @throws CannotCloseException           When the {@link Openable} object cannot close.
     */
    void close() throws OpenableAlreadyClosedException, CannotCloseException;
}
