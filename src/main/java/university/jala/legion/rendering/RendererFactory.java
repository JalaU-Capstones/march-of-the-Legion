package university.jala.legion.rendering;

/**
 * A factory for creating battlefield renderers based on a display type code.
 * This class decouples the client from the concrete implementation of renderers,
 * allowing for flexible selection of the rendering style.
 */
public class RendererFactory {
    /**
     * Creates a {@link BattlefieldRenderer} based on the given display type code.
     *
     * @param type The display type code ('c' for character, 'n' for numeric).
     * @return The corresponding battlefield renderer.
     * @throws IllegalArgumentException if the display type code is invalid.
     */
    public static BattlefieldRenderer create(String type) {
        return switch (type.toLowerCase()) {
            case "c" -> new CharacterRenderer();
            case "n" -> new NumericRenderer();
            default -> throw new IllegalArgumentException("Invalid display type code: " + type);
        };
    }
}
