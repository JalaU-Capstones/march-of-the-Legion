package university.jala.legion.sorting.placement;

/**
 * A factory for creating placement strategies based on an orientation code.
 * This class decouples the client from the concrete implementation of placement strategies,
 * allowing for flexible selection of the placement algorithm.
 */
public class PlacementStrategyFactory {
    /**
     * Creates a {@link PlacementStrategy} based on the given orientation code.
     *
     * @param orientation The orientation code ('n', 's', 'e', 'w').
     * @return The corresponding placement strategy.
     * @throws IllegalArgumentException if the orientation code is invalid.
     */
    public static PlacementStrategy create(char orientation) {
        return switch (orientation) {
            case 'n' -> new NorthPlacementStrategy();
            case 's' -> new SouthPlacementStrategy();
            case 'e' -> new EastPlacementStrategy();
            case 'w' -> new WestPlacementStrategy();
            default -> throw new IllegalArgumentException("Invalid orientation code: " + orientation);
        };
    }
}
