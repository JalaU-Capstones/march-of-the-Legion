package university.jala.legion.exception;

/**
 * Custom exception for handling user-facing errors that occur during the simulation runtime.
 * This allows for graceful error handling and user-friendly messages.
 */
public class SimulationException extends Exception {

    private final String hint;

    /**
     * Constructs a new SimulationException with a detail message and a hint.
     *
     * @param message The detail message for the error.
     * @param hint    A helpful tip for the user to resolve the error.
     */
    public SimulationException(String message, String hint) {
        super(message);
        this.hint = hint;
    }

    /**
     * Gets the hint associated with this exception.
     *
     * @return The hint message, or null if no hint is available.
     */
    public String getHint() {
        return hint;
    }
}
